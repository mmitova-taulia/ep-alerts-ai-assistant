package com.taulia.hackathon24.epalertsaiassistant.service.jira

import groovy.util.logging.Slf4j
import net.rcarz.jiraclient.Field
import net.rcarz.jiraclient.Issue
import net.rcarz.jiraclient.JiraClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@Slf4j
class JiraService {

  @Autowired
  JiraClient jiraClient

  @Value('${JIRA_PROJECT}')
  String jiraProject

  @Value('${JIRA_URL}')
  String jiraUrl

  @Value('${USE_JIRA_MOCK}')
  Boolean useMock

  String jiraIssueType = 'Task'

  String createJiraTicket(String alertName, String funderName, String workflow, String sql) {
    String summary = "[EP ALERTS] Insert EP alert(s) $alertName for funder $funderName and $workflow"
    String description = """
Please, check and execute the following SQL statements

$sql

"""
    log.info("Ticket summary: $summary")
    log.info("Ticket description: $description")

    if(useMock) {
      return createTicketMock(summary, description)
    }
    createTicket(summary, description)
  }

  private String createTicket(String summary, String description) {
    Issue issue = jiraClient.createIssue(jiraProject, jiraIssueType)
      .field(Field.SUMMARY, summary)
      .field(Field.DESCRIPTION, description)
      .execute()

    String jiraTicketKey = issue.key

    String url = "${jiraUrl}/browse/${jiraTicketKey}"
    url
  }

  private String createTicketMock(String summary, String description) {
    log.info("Using mock JIRA")
    "https://taulia.atlassian.net/browse/MATEST-11260"
  }

}
