package com.taulia.hackathon24.epalertsaiassistant.function

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.taulia.hackathon24.epalertsaiassistant.model.Workflow
import com.taulia.hackathon24.epalertsaiassistant.service.ppm.PPMService
import groovy.util.logging.Slf4j
import java.util.function.Function

@Slf4j
class ResolveWorkflowIdByNameFunction implements Function<ResolveWorkflowIdByNameRequest, ResolveWorkflowIdByNameResponse> {

  PPMService ppmService

  @Override
  ResolveWorkflowIdByNameResponse apply(ResolveWorkflowIdByNameRequest request) {
    String workflow = request.workflow()
    log.info("resolve_workflow_id function called for workflow [${workflow}]")

    List<Workflow> workflows = ppmService.getWorkflows()

    String existingWorkflowId = workflows.find {it.name == workflow }

    String workflowId = existingWorkflowId ?: "Workflow ${workflow} does not exist. Valid workflows are ${workflows*.name}"
    log.info("resolve_workflow_id function returns [${workflowId}]")

    new ResolveWorkflowIdByNameResponse(workflowId)
  }
}
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Tries to resolve the workflow profile id for the provided workflow name")
record ResolveWorkflowIdByNameRequest(@JsonProperty(required = true, value = "workflow") @JsonPropertyDescription("The workflow which id to be obtained, e.g. SCF") String workflow) {
}

record ResolveWorkflowIdByNameResponse(String workflowId) {
}