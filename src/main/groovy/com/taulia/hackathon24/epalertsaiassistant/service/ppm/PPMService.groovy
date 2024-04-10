package com.taulia.hackathon24.epalertsaiassistant.service.ppm

import com.taulia.hackathon24.epalertsaiassistant.model.Workflow
import com.taulia.hackathon24.epalertsaiassistant.security.InternalUserIdFilter
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Slf4j
@Service
@CompileStatic
class PPMService {

  static final String CLIENT_TYPE_HEADER = 'x-tau-client-type'
  static final String REQUEST_ID_HEADER = 'x-tau-request-id'

  @Value('${PPM_URL}')
  String ppmUrl

  @Autowired
  RestTemplate restTemplate

  List<Workflow> getWorkflows() {
    final String workflowsEndpointURL = "${ppmUrl}/v2/workflow/list"


    log.info("Going to send getWorkflowsList request to PPM (${workflowsEndpointURL})")
    ResponseEntity<List<Workflow>> response = restTemplate.exchange(workflowsEndpointURL,
      HttpMethod.GET, new HttpEntity<>(getDefaultHeaders()), List<Workflow>.class)

    List<Workflow> workflows = response.getBody()
    log.info("PPM returned workflows [${workflows}]")
    workflows
  }

  String resolveFunderIdByName(String funderName) {
    final String funderIdEndpointUrl = "${ppmUrl}/v2/funder/id"

    String funderIdEndpointUri = UriComponentsBuilder.fromHttpUrl(funderIdEndpointUrl)
      .queryParam("funderName", funderName)
      .encode()
      .toUriString()

    log.info("Going to send resolveFunderIdByName request to PPM (${funderIdEndpointUri})")
    ResponseEntity<String> response = restTemplate.exchange(funderIdEndpointUri,
      HttpMethod.GET, new HttpEntity<>(getDefaultHeaders()), String.class)

    String funderId = response.getBody()
    log.info("PPM returned [${funderId}]")

    funderId
  }

  private HttpHeaders getDefaultHeaders(String threadId = null) {
    HttpHeaders headers = new HttpHeaders()
    headers.setContentType(MediaType.APPLICATION_JSON)
    headers.set(CLIENT_TYPE_HEADER, 'USER')
    headers.set(InternalUserIdFilter.USER_ID_HEADER, SecurityContextHolder.context.authentication.principal as String)
    headers.set(REQUEST_ID_HEADER, threadId ?: System.currentTimeMillis().toString())

  }
}
