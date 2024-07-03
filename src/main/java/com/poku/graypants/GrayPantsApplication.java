package com.poku.graypants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class GrayPantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrayPantsApplication.class, args);
    }

}
