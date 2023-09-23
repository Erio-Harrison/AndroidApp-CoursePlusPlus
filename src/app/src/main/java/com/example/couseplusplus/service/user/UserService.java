package com.example.couseplusplus.service.user;

import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.model.user.UserRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import java.util.Objects;

public class UserService {
  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void login(User user, OnCompleteListener<AuthResult> onCompleteListener) {
    if (Objects.isNull(user.userName()) || user.userName().isBlank()) {
      throw new IllegalArgumentException();
    }
    if (Objects.isNull(user.password()) || user.password().isBlank()) {
      throw new IllegalArgumentException();
    }

    userRepository.login(user, onCompleteListener);
  }

  public void logout(User user) {
    userRepository.logout(user);
  }

  public void register(User user, OnCompleteListener<AuthResult> onCompleteListener) {
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
}
