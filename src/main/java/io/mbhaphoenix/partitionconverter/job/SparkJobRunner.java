package io.mbhaphoenix.partitionconverter.job;

import io.mbhaphoenix.partitionconverter.conf.ApplicationArguments;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SparkJobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkJobRunner.class);

    @Autowired
    private ApplicationArguments applicationArguments;

    public void run() throws Exception {

        SparkSession spark = SparkSession
                .builder()
                .appName("Converting partition " +
                        applicationArguments.getPartitionDefinition() + "from CSV in " +
                        applicationArguments.getSrcDbName() + "." +
                        applicationArguments.getSrcTableName() + " to Parquet in " +
                        applicationArguments.getTargetDbName() + "." +
                        applicationArguments.getTargetTableName()
                )
                .config("hive.exec.stagingdir", applicationArguments.getHiveExecStagingdir())
                .config("hive.exec.dynamic.partition.mode", "nonstrict")
                .getOrCreate();

        LOGGER.debug("----------Application configuration properties: [{}]", applicationArguments);

        LOGGER.debug("----------About to run the partition insertion");
        final String insertPartitionSqlString = "INSERT INTO TABLE " + applicationArguments.getTargetDbName() + "." + applicationArguments.getTargetTableName() +
                " PARTITION (" + applicationArguments.getPartitionColumns() + ")" +
                " SELECT * FROM " + applicationArguments.getSrcDbName() + "." + applicationArguments.getSrcTableName() +
                " WHERE " + applicationArguments.getPartitionFilterExpression() +
                " DISTRIBUTE BY " + applicationArguments.getPartitionColumns();

        LOGGER.info("----------SQL String used to create the new Parquet Partiton : [{}]", insertPartitionSqlString);

        spark.sql(insertPartitionSqlString);


    }

}
