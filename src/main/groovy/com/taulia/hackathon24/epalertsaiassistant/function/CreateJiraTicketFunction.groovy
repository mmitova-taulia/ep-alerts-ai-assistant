package com.taulia.hackathon24.epalertsaiassistant.function

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import groovy.util.logging.Slf4j

import java.util.function.Function

@Slf4j
class CreateJiraTicketFunction  implements Function<CreateJiraTicketRequest, CreateJiraTicketResponse> {


  @Override
  CreateJiraTicketResponse apply(CreateJiraTicketRequest request) {
    log.info("create_jira_ticket function called with sqls[${request.sqls()}]")

    //TODO: call PPM-s endpoint
    String jiraTicketId = "OKB-1234"
    log.info("create_jira_ticket_with_sqls function returns [${jiraTicketId}]")

    new CreateJiraTicketResponse(jiraTicketId)
  }
}

/**
 * Find FunderId Function request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Create Jira Ticket API request")
record CreateJiraTicketRequest(@JsonProperty(required = true, value = "sqls") @JsonPropertyDescription("The sqls which to be added to the jira ticket") String sqls) {
}

/**
 * Find FunderId Function response.
 */
record CreateJiraTicketResponse(String jiraTicketId) {

}