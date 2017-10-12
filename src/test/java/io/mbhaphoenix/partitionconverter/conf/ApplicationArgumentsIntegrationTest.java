package io.mbhaphoenix.partitionconverter.conf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestPropertySource(locations = "classpath:test-application.properties")
public class ApplicationArgumentsIntegrationTest {

    @Autowired
    ApplicationArguments applicationArguments;

    @Test
    public void testValidApplicationArgumentsBean() throws Exception {
        assertThat(applicationArguments.getSrcDbName()).isEqualTo("convergence_isoprod_public_db");
        assertThat(applicationArguments.getSrcTableName()).isEqualTo("sr_xiidm_network_isoprod_public_csv");
        assertThat(applicationArguments.getTargetDbName()).isEqualTo("phoenix_dev00_prive_db");
        assertThat(applicationArguments.getTargetTableName()).isEqualTo("sr_xiidm_network_parquet");
        assertThat(applicationArguments.getPartitionDefinition()).isEqualTo("caseyear='2017', casemonth='05', caseday='27'");
        assertThat(applicationArguments.getHiveExecStagingdir()).isEqualTo("/tmp/mehdi");
        assertThat(applicationArguments.getPartitionFilterExpression()).isEqualTo("caseyear='2017' AND casemonth='05' AND caseday='27'");
        assertThat(applicationArguments.getPartitionColumns()).isEqualTo("caseyear, casemonth, caseday");
    }

}