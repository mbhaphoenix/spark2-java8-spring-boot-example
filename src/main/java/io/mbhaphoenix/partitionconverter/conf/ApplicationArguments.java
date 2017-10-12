package io.mbhaphoenix.partitionconverter.conf;

import io.mbhaphoenix.partitionconverter.validation.ValidPartitionDefinition;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Data
@Component
@ConfigurationProperties
public class ApplicationArguments {

    @NotNull
    private String srcDbName;

    @NotNull
    private String srcTableName;

    @NotNull
    private String targetDbName;

    @NotNull
    private String targetTableName;

    @NotNull
    private String hiveExecStagingdir = "/tmp/parquet-partition-converter/hive";

    @ValidPartitionDefinition
    private String partitionDefinition;

    private String partitionFilterExpression;

    private String partitionColumns;

    @PostConstruct
    public void init() {
        this.partitionFilterExpression = buildPartitionFilterExpression();
        this.partitionColumns = buildPartitionColumns();
    }

    String buildPartitionFilterExpression() {
        return this.partitionDefinition.replaceAll(",", " AND");
    }

    String buildPartitionColumns() {
        return this.partitionDefinition.replaceAll("=.*?,", ",").replaceFirst("=.*$", "");
    }
}
