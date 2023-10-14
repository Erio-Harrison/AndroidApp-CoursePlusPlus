package com.example.couseplusplus.view;

import android.content.Intent;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.service.user.UserService;
import java.util.Optional;

public class LogoutLabeler {
  public static void label(int id, Menu menu, UserService userService, AppCompatActivity activity) {
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
}
