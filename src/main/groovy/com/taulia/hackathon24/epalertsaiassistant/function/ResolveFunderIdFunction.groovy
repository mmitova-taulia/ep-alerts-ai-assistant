package com.taulia.hackathon24.epalertsaiassistant.function

import com.fasterxml.jackson.annotation.JsonClassDescription
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyDescription
import groovy.util.logging.Slf4j

import java.util.function.Function

@Slf4j
class ResolveFunderIdFunction implements Function<ResolveFunderIdRequest, ResolveFunderIdResponse> {


  @Override
  ResolveFunderIdResponse apply(ResolveFunderIdRequest request) {
    log.info("resolve_funder_id function called with funder_name[${request.funderName()}]")

    //TODO: call PPM-s endpoint
    String funderId = "${request.funderName?.replaceAll("\\s", '_')}_id" as String
    log.info("resolve_funder_id function resolved funder_id[${funderId}]")

    new ResolveFunderIdResponse(funderId)
  }
}

/**
 * Find FunderId Function request.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("EP Alerts Information API request")
record ResolveFunderIdRequest(@JsonProperty(required = true, value = "funderName") @JsonPropertyDescription("The name of the funder which id to find, e.g. Baiza LLC") String funderName) {
}

/**
 * Find FunderId Function response.
 */
record ResolveFunderIdResponse(String funderId) {

}
