package com.poku.graypants.global.config.payment;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Scope("singleton")
@Configuration
public class IamportConfig {

  private final String apiKey;
  private final String apiSecret;
  private final String impUid;

  public IamportConfig(
          @Value("${spring.application.portone.api-key}") String apiKey,
          @Value("${spring.application.portone.api-secret}") String apiSecret,
          @Value("${spring.application.portone.user-code}") String impUid) {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    this.impUid = impUid;
  }

  @Bean
  public IamportClient iamportClient() {
    return new IamportClient(apiKey, apiSecret);
  }

  @Bean
  public String getImpUid() {
    return impUid;
  }
}
