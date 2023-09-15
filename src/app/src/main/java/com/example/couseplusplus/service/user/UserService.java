package com.example.couseplusplus.service.user;

import com.example.couseplusplus.model.user.UserRepository;

public class UserService {
  UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
