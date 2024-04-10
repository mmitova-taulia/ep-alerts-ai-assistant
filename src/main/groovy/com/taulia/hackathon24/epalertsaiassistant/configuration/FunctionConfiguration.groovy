package com.taulia.hackathon24.epalertsaiassistant.configuration

import com.taulia.hackathon24.epalertsaiassistant.function.CreateJiraTicketFunction
import com.taulia.hackathon24.epalertsaiassistant.function.CreateJiraTicketResponse
import com.taulia.hackathon24.epalertsaiassistant.function.VerifyWorkflowFunction
import com.taulia.hackathon24.epalertsaiassistant.function.ResolveFunderIdFunction
import com.taulia.hackathon24.epalertsaiassistant.function.ResolveFunderIdResponse
import com.taulia.hackathon24.epalertsaiassistant.function.VerifyWorkflowResponse
import com.taulia.hackathon24.epalertsaiassistant.service.jira.JiraService
import com.taulia.hackathon24.epalertsaiassistant.service.ppm.PPMService
import org.springframework.ai.model.function.FunctionCallback
import org.springframework.ai.model.function.FunctionCallbackWrapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FunctionConfiguration {

  @Bean
  FunctionCallback verifyWorkflowFunction(PPMService ppmService) {

    FunctionCallbackWrapper.builder(new VerifyWorkflowFunction(ppmService: ppmService))
      .withName("verify_workflow")
      .withDescription("Obtains all registered workflows and checks if the provided workflow is one of the registered")
      .withResponseConverter((VerifyWorkflowResponse response) -> response.result())
      .build()
  }

  @Bean
  FunctionCallback resolveFunderIdFunctionInfo(PPMService ppmService) {

    FunctionCallbackWrapper.builder(new ResolveFunderIdFunction(ppmService: ppmService))
      .withName("resolve_funder_id")
      .withDescription("Resolves funderId by funderName")
      .withResponseConverter((ResolveFunderIdResponse response) -> response.funderId())
      .build()
  }

  @Bean
  FunctionCallback creatJiraTicketFunctionInfo(JiraService jiraService) {

    FunctionCallbackWrapper.builder(new CreateJiraTicketFunction(jiraService: jiraService))
      .withName("create_jira_ticket")
      .withDescription("Creates Jira Ticket. The function will return an url to the ticket")
      .withResponseConverter((CreateJiraTicketResponse response) -> response.jiraTicketUrl())
      .build()
  }

}
