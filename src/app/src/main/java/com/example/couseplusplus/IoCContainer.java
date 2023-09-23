package com.example.couseplusplus;

import com.example.couseplusplus.data.user.UserDatasource;
import com.example.couseplusplus.model.user.UserRepository;
import com.example.couseplusplus.service.user.UserService;

public class IoCContainer {
  private static final UserRepository userRepository = new UserDatasource();
  public static final UserService userService = new UserService(userRepository);
}
