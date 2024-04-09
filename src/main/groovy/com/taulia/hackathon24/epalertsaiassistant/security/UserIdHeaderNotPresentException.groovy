package com.taulia.hackathon24.epalertsaiassistant.security

class UserIdHeaderNotPresentException extends RuntimeException {

  UserIdHeaderNotPresentException(String message) {
    super(message)
  }

}
