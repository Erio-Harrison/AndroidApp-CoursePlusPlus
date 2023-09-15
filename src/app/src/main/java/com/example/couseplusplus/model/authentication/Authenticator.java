package com.example.couseplusplus.model.authentication;

import com.example.couseplusplus.model.user.User;

public interface Authenticator {
  AuthenticationResult authenticate(User user);
}
