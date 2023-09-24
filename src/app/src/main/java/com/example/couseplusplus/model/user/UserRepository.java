package com.example.couseplusplus.model.user;

import java.util.function.Consumer;

public interface UserRepository {
  void login(User user, Consumer<AuthenticationResult> onCompleteListener);

  void logout();

  void register(User user, Consumer<AuthenticationResult> onCompleteListener);

  User getCurrentUser();
}
