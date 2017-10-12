package io.mbhaphoenix.partitionconverter;

import io.mbhaphoenix.partitionconverter.job.SparkJobRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    @Autowired
    private SparkJobRunner sparkJobRunner;

    @Override
    public void run(String... args) throws Exception {
        this.sparkJobRunner.run();
    }

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


}
