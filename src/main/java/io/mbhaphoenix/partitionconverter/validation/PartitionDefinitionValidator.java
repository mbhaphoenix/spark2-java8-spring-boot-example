package io.mbhaphoenix.partitionconverter.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.countMatches;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class PartitionDefinitionValidator implements ConstraintValidator<ValidPartitionDefinition, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartitionDefinitionValidator.class);

    @Override
    public void initialize(ValidPartitionDefinition validPartitionDefinition) {

    }

    @Override
    public boolean isValid(String partitionDefinitionString, ConstraintValidatorContext constraintValidatorContext) {
        LOGGER.debug("############## Validating partition definition");
        return isValidPartitionDefinitionString(partitionDefinitionString);
    }

    static boolean isValidPartitionDefinitionString(String partitionDefinitionString) {
        if (isBlank(partitionDefinitionString)) {
            return false;
        }
        String partitionDefinitionWithoutWhiteSpaces = partitionDefinitionString.trim();
        if (partitionDefinitionWithoutWhiteSpaces.endsWith(",")) {
            return false;
        }
        return Arrays.stream(partitionDefinitionWithoutWhiteSpaces.split(","))
                .allMatch(colDef -> isValidColumnDefinition(colDef.replaceAll("\\s+", "")));
    }

    static boolean isValidColumnDefinition(String colDef) {
        String[] colDefSplits = colDef.split("=");
        if (colDefSplits.length != 2) {
            return false;
        }
        String columnName = colDefSplits[0];
        String columnValue = colDefSplits[1];

        return isValidColumnValue(columnValue) && isValidColumnName(columnName);

    }

    static boolean isValidColumnValue(String columnValue) {
        if (countMatches(columnValue, "'") != 0 && countMatches(columnValue, "'") != 2) {
            return false;
        }
        if (columnValue.startsWith("'")) {
            if (!columnValue.endsWith("'")) {
                return false;
            }
            columnValue = columnValue.replace("'", "");
        }
        return Pattern.matches("\\d+", columnValue);
    }

    static boolean isValidColumnName(String columnName) {
        return Pattern.matches("[\\w_]+", columnName);
    }
}
