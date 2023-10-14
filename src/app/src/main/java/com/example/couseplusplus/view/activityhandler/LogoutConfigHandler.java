package com.example.couseplusplus.view.activityhandler;

import android.content.Intent;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.service.user.UserService;
import com.example.couseplusplus.view.Login;
import java.util.Optional;

public class LogoutConfigHandler {
  int id;
  Menu menu;
  UserService userService;
  AppCompatActivity activity;

  public LogoutConfigHandler(
      int id, Menu menu, UserService userService, AppCompatActivity activity) {
    this.id = id;
    this.menu = menu;
    this.userService = userService;
    this.activity = activity;
  }

  public void configure() {
    label();
    setListener();
  }

  void label() {
    Optional<User> optionalUser = userService.findCurrentUser();
    if (optionalUser.isPresent()) {
      String text = String.format("Logout %s", optionalUser.get().userName());
      menu.findItem(id).setTitle(text);
    } else {
      Intent intent = new Intent(activity.getApplicationContext(), Login.class);
      activity.startActivity(intent);
      activity.finish();
    }
  }

  public void setListener() {
    menu.findItem(id)
        .setOnMenuItemClickListener(
            item -> {
              userService.logout();
              Intent logoutIntent = new Intent(activity.getApplicationContext(), Login.class);
              activity.startActivity(logoutIntent);
              activity.finish();
              return true;
            });
  }
}
