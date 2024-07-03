package com.poku.graypants.global.config.email;

import java.util.Properties;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties("spring.mail")
public class EmailProperties {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final boolean auth;
    private final boolean starttlsEnable;
    private final boolean starttlsRequired;
    private final int connectionTimeout;
    private final int timeout;
    private final int writeTimeout;
    @ConstructorBinding
    public EmailProperties(String host, int port, String username, String password, boolean auth,
                           boolean starttlsEnable,
                           boolean starttlsRequired, int connectionTimeout, int timeout, int writeTimeout) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.auth = auth;
        this.starttlsEnable = starttlsEnable;
        this.starttlsRequired = starttlsRequired;
        this.connectionTimeout = connectionTimeout;
        this.timeout = timeout;
        this.writeTimeout = writeTimeout;
    }
}
