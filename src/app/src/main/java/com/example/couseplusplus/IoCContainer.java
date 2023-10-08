package com.example.couseplusplus;

import com.example.couseplusplus.data.user.UserDatasource;
import com.example.couseplusplus.model.user.UserRepository;
import com.example.couseplusplus.service.user.UserService;

/**
 * @author Yuki Misumi (u7582380)
 */
public class IoCContainer {
  private static final UserRepository userRepository = new UserDatasource();
  private static final UserService userService = new UserService(userRepository);

  public static UserService userService() {
    return userService;
  }
}
