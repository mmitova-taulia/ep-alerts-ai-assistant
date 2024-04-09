package com.taulia.hackathon24.epalertsaiassistant.service

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import groovy.util.logging.Slf4j

import java.util.function.Function

@Slf4j
class AlertsInformationService implements Function<Request, Response> {


  @Override
  Response apply(Request request) {
    log.info("resolve_funder_id function called with funder_name[${request.funderName()}]")

    //TODO: call PPM-s endpoint
    String funderId = "${request.funderName?.replaceAll("\\s", '_')}_id" as String
    log.info("resolve_funder_id function resolved funder_id[${funderId}]")

    new Response(funderId)
  }
}

/**
 * Find FunderId Function request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("EP Alerts Information API request")
record Request(@JsonProperty(required = true, value = "funderName") @JsonPropertyDescription("The name of the funder which id to find, e.g. Baiza LLC") String funderName) {
}

/**
 * Find FunderId Function response.
 */
record Response(String funderId) {

}
