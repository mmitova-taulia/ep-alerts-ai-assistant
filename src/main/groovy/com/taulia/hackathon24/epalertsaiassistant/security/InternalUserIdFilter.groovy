package com.taulia.hackathon24.epalertsaiassistant.security

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Slf4j
@Component
class InternalUserIdFilter extends OncePerRequestFilter {

  static final String USER_ID_HEADER = 'x-tau-userid'

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      processUserContext(request)
    } catch (UserIdHeaderNotPresentException e) {
      Map<String, Object> errorDetails = [:]
      errorDetails.put("message", e.getMessage())
      response.setStatus(HttpStatus.BAD_REQUEST.value())
      response.setContentType(MediaType.APPLICATION_JSON_VALUE)
      new ObjectMapper().writeValue(response.getWriter(), errorDetails)
    }

    filterChain.doFilter(request, response)
  }

  private static void processUserContext(HttpServletRequest request) throws UserIdHeaderNotPresentException {
    Optional.ofNullable(request.getHeader(USER_ID_HEADER))
      .ifPresentOrElse({
        SecurityContextHolder.context.setAuthentication(new InternalUserAuthentication(it))
      }, { throw new UserIdHeaderNotPresentException('User ID header not present.') })
  }

}
