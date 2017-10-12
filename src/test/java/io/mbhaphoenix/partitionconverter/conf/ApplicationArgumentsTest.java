package io.mbhaphoenix.partitionconverter.conf;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationArgumentsTest {

    private ApplicationArguments applicationArguments;

    @Before
    public void setUp() throws Exception {
        applicationArguments = new ApplicationArguments();
    }

    @Test
    public void buildPartitionsFilterExpression() throws Exception {
        applicationArguments.setPartitionDefinition("caseyear='2017', casemonth='05', caseday='28'");
        String partitionFilterExpression = applicationArguments.buildPartitionFilterExpression();
        assertThat(partitionFilterExpression).isEqualTo("caseyear='2017' AND casemonth='05' AND caseday='28'");
    }

    @Test
    public void buildPartitionColumnsStringValues() throws Exception {
        applicationArguments.setPartitionDefinition("caseyear='2017', casemonth='05', caseday='28'");
        String partitionColumns = applicationArguments.buildPartitionColumns();
        assertThat(partitionColumns).isEqualTo("caseyear, casemonth, caseday");
    }

    @Test
    public void buildPartitionColumnsIntValues() throws Exception {
        applicationArguments.setPartitionDefinition("caseyear=2017, casemonth=05, caseday=28");
        String partitionColumns = applicationArguments.buildPartitionColumns();
        assertThat(partitionColumns).isEqualTo("caseyear, casemonth, caseday");
    }

}