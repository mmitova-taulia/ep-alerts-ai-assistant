package com.taulia.hackathon24.epalertsaiassistant.configuration

import com.taulia.hackathon24.epalertsaiassistant.security.InternalUserIdFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfiguration {

  @Autowired
  InternalUserIdFilter internalUserIdFilter

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf {
      it.disable()
    }.authorizeHttpRequests {
      it.anyRequest().authenticated()
    }.addFilterBefore(internalUserIdFilter, BasicAuthenticationFilter.class)

    http.build()
  }

}
