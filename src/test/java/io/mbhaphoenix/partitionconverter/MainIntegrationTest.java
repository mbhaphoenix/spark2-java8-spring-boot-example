package io.mbhaphoenix.partitionconverter;

import io.mbhaphoenix.partitionconverter.job.SparkJobRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(locations = "classpath:test-application.properties")
public class MainIntegrationTest {

    @MockBean
    private SparkJobRunner sparkJobRunnerMock;

    @Test
    public void testSpringBootApplicationRun() throws Exception {
        verify(sparkJobRunnerMock, times(1)).run();
    }

}