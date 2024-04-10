package com.taulia.hackathon24.epalertsaiassistant.configuration

import groovy.util.logging.Slf4j
import net.rcarz.jiraclient.BasicCredentials
import net.rcarz.jiraclient.JiraClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@Slf4j
class JiraConfiguration {

  @Value('${JIRA_URL}')
  String jiraUrl

  @Value('${JIRA_USERNAME}')
  String jiraUsername

  @Value('${JIRA_PASSWORD}')
  String jiraPassword

  @Bean
  JiraClient jiraClient() {
    log.info("Creating jira client for jiraUrl[${jiraUrl}]")
    new JiraClient(jiraUrl, new BasicCredentials(jiraUsername, jiraPassword))
  }

}
