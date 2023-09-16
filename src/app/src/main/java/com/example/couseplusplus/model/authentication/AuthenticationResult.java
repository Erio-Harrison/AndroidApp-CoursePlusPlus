package com.example.couseplusplus.model.authentication;

public class AuthenticationResult {
  boolean authenticated;
  String reason;

  public AuthenticationResult(boolean authenticated, String reason) {
    this.authenticated = authenticated;
    this.reason = reason;
  }

  public boolean authenticated() {
    return authenticated;
  }

  public String reason() {
    return reason;
  }
}
