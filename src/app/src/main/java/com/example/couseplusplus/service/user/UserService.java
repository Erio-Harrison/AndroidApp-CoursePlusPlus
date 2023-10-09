package com.example.couseplusplus.service.user;

import com.example.couseplusplus.model.user.AuthenticationResult;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.model.user.UserRepository;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class UserService {
  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void login(User user, Consumer<AuthenticationResult> onCompleteListener) {
    if (Objects.isNull(user.userName()) || user.userName().isBlank()) {
      throw new IllegalArgumentException();
    }
    if (Objects.isNull(user.password()) || user.password().isBlank()) {
      throw new IllegalArgumentException();
    }

    userRepository.login(user, onCompleteListener);
  }

  public void logout() {
    userRepository.logout();
  }

  public void register(User user, Consumer<AuthenticationResult> onCompleteListener) {
    if (Objects.isNull(user.userName()) || user.userName().isBlank()) {
      throw new IllegalArgumentException();
    }
    if (Objects.isNull(user.password()) || user.password().isBlank()) {
      throw new IllegalArgumentException();
    }

    userRepository.register(user, onCompleteListener);
  }

  public User getCurrentUser() {
    return userRepository.getCurrentUser();
  }

  public Optional<User> findCurrentUser() {
    return Optional.ofNullable(userRepository.getCurrentUser());
  }
}
