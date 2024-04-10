package com.taulia.hackathon24.epalertsaiassistant.function

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.taulia.hackathon24.epalertsaiassistant.service.jira.JiraService
import groovy.util.logging.Slf4j

import java.util.function.Function

@Slf4j
class CreateJiraTicketFunction implements Function<CreateJiraTicketRequest, CreateJiraTicketResponse> {

  JiraService jiraService

  @Override
  CreateJiraTicketResponse apply(CreateJiraTicketRequest request) {
    log.info("create_jira_ticket function called with arguments[${request}]")

    String jiraTicketUrl = jiraService.createJiraTicket(
      request.alert_name,
      request.funder_name,
      request.workflow,
      request.sql
    )

    log.info("create_jira_ticket function returns [${jiraTicketUrl}]")

    new CreateJiraTicketResponse(jiraTicketUrl)
  }
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Create Jira Ticket API request")
class CreateJiraTicketRequest {

  @JsonProperty(required = true, value = "sql")
  @JsonPropertyDescription("The generated SQL statements")
  String sql

  @JsonProperty(required = true, value = "alert_name")
  @JsonPropertyDescription("The name of the alert")
  String alert_name

  @JsonProperty(required = true, value = "funder_name")
  @JsonPropertyDescription("The name of the funder")
  String funder_name

  @JsonProperty(required = true, value = "workflow")
  @JsonPropertyDescription("The workflow for the alert")
  String workflow
}


record CreateJiraTicketResponse(String jiraTicketUrl) {

}