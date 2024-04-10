package com.taulia.hackathon24.epalertsaiassistant.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfiguration {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}