package com.example.couseplusplus;

import com.example.couseplusplus.data.user.UserDatasource;
import com.example.couseplusplus.model.user.UserRepository;
import com.example.couseplusplus.service.user.UserService;

// Doing inversion of control.
// Why not instantiating in activity? - to ensure each service class is singleton.
// Why not inserting UserService#userRepository inside the constructor? - to avoid service layer
// knows about data layer.
// https://www.dotnettricks.com/learn/dependencyinjection/what-is-ioc-container-or-di-container
public class IoCContainer {
  private static final UserRepository userRepository = new UserDatasource();
  private static final UserService userService = new UserService(userRepository);

  public static UserService userService() {
    return userService;
  }
}
