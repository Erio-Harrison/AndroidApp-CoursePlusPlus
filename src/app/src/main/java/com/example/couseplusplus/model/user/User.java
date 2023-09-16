package com.example.couseplusplus.model.user;

public class User {
  String userName;
  String password;

  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String userName() {
    return userName;
  }

  public String password() {
    return password;
  }
}
