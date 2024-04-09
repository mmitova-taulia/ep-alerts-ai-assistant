package com.taulia.hackathon24.epalertsaiassistant.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class InternalUserAuthentication implements Authentication {

  String userId

  InternalUserAuthentication(String userId) {
    this.userId = userId
  }

  @Override
  Collection<? extends GrantedAuthority> getAuthorities() {
    []
  }

  @Override
  Object getCredentials() {
    ''
  }

  @Override
  Object getDetails() {
    ''
  }

  @Override
  Object getPrincipal() {
    userId
  }

  @Override
  boolean isAuthenticated() {
    userId
  }

  @Override
  void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    //no implementation
  }

  @Override
  String getName() {
    ''
  }

}
