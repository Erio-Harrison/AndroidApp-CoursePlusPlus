package com.example.couseplusplus.model.user;

public class AuthenticationResult {
  boolean succeeded;
  User user;

  public AuthenticationResult(boolean succeeded, User user) {
    this.succeeded = succeeded;
    this.user = user;
  }

  public boolean isSuccessful() {
    return succeeded;
  }

  public User user() {
    return user;
  }
}
