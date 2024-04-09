package com.taulia.hackathon24.epalertsaiassistant.configuration

import com.taulia.hackathon24.epalertsaiassistant.function.CreateJiraTicketFunction
import com.taulia.hackathon24.epalertsaiassistant.function.CreateJiraTicketResponse
import com.taulia.hackathon24.epalertsaiassistant.function.ResolveFunderIdFunction
import com.taulia.hackathon24.epalertsaiassistant.function.ResolveFunderIdResponse
import org.springframework.ai.model.function.FunctionCallback
import org.springframework.ai.model.function.FunctionCallbackWrapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FunctionConfiguration {

  @Bean
  FunctionCallback resolveFunderIdFunctionInfo() {

    FunctionCallbackWrapper.builder(new ResolveFunderIdFunction())
      .withName("resolve_funder_id")
      .withDescription("Resolves funderId by funderName")
      .withResponseConverter((ResolveFunderIdResponse response) -> response.funderId())
      .build()
  }

  @Bean
  FunctionCallback creatJiraTicketFunctionInfo() {

    FunctionCallbackWrapper.builder(new CreateJiraTicketFunction())
      .withName("create_jira_ticket")
      .withDescription("Creates Jira Ticket with generated sqls")
      .withResponseConverter((CreateJiraTicketResponse response) -> response.jiraTicketId())
      .build()
  }

}
