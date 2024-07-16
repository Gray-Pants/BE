package com.poku.graypants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableJpaAuditing
public class GrayPantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrayPantsApplication.class, args);
    }

}
