package io.mbhaphoenix.partitionconverter.validation;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PartitionDefinitionValidatorTest {

    @Test
    public void isValidPartitionDefinitionStringTest() throws Exception {

        Assertions.assertThat(PartitionDefinitionValidator.isValidPartitionDefinitionString("caseyear='2017', casemonth='05', caseday='27'")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidPartitionDefinitionString("  caseyear=2017,  casemonth=05,   caseday=27   ")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidPartitionDefinitionString(" caseyear='2017', casemonth='05',    caseday='27'  '")).isFalse();
        Assertions.assertThat(PartitionDefinitionValidator.isValidPartitionDefinitionString("   ")).isFalse();
        Assertions.assertThat(PartitionDefinitionValidator.isValidPartitionDefinitionString("year")).isFalse();
        Assertions.assertThat(PartitionDefinitionValidator.isValidPartitionDefinitionString("part=")).isFalse();
        Assertions.assertThat(PartitionDefinitionValidator.isValidPartitionDefinitionString("01")).isFalse();
    }

    @Test
    public void isValidColumnDefinitionTest() throws Exception {
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnDefinition("caseyear='2017'")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnDefinition("caseyear=2017")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnDefinition("caseyear='2017''")).isFalse();
    }

    @Test
    public void isValidColumnValueTest() throws Exception {
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnValue("'2017'")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnValue("'2017")).isFalse();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnValue("2017'")).isFalse();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnValue("11")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnValue("11w")).isFalse();

        assertThat(PartitionDefinitionValidator.isValidColumnValue("'2017''")).isFalse();
    }

    @Test
    public void isValidColumnNameTest() throws Exception {
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnName("caseyear")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnName("caseyear_")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnName("_caseyear")).isTrue();
        Assertions.assertThat(PartitionDefinitionValidator.isValidColumnName("case_year")).isTrue();

        assertThat(PartitionDefinitionValidator.isValidColumnName("case_year-")).isFalse();
    }

}