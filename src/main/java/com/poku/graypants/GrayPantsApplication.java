package com.poku.graypants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ConfigurationPropertiesScan
@SpringBootApplication
@EnableJpaAuditing
@EnableRedisHttpSession
public class GrayPantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrayPantsApplication.class, args);
    }

}
