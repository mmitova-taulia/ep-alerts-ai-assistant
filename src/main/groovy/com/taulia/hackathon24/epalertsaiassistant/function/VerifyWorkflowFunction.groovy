package com.taulia.hackathon24.epalertsaiassistant.function

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import com.taulia.hackathon24.epalertsaiassistant.service.ppm.PPMService
import groovy.util.logging.Slf4j
import java.util.function.Function

@Slf4j
class VerifyWorkflowFunction implements Function<VerifyWorkflowRequest, VerifyWorkflowResponse> {

  PPMService ppmService

  @Override
  VerifyWorkflowResponse apply(VerifyWorkflowRequest request) {
    String workflow = request.workflow()
    log.info("verify_workflow function called for workflow [${workflow}]")

    List<String> workflows = ppmService.getWorkflows()

    String result =  "Workflow ${workflow} ${workflows.contains(workflow) ? 'exists' : "does not exist. Valid workflows are ${workflows}"}"
    log.info("verify_workflow function returns [${result}]")

    new VerifyWorkflowResponse(result)
  }
}
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Verifies if provided by the user workflow is an existing one")
record VerifyWorkflowRequest(@JsonProperty(required = true, value = "workflow") @JsonPropertyDescription("The workflow to be verified, e.g. SCF") String workflow) {
}

record VerifyWorkflowResponse(String result) {
}