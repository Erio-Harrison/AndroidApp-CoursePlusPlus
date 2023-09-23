package com.example.couseplusplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.service.user.UserService;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  FirebaseAuth auth;
  Button logoutButton;
  TextView textView;
  User user;
  UserService userService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    userService = IoCContainer.userService;
    logoutButton = findViewById(R.id.logout);
    textView = findViewById(R.id.user_details);
    user = userService.getCurrentUser();
    if (Objects.isNull(user)) {
      Intent intent = new Intent(getApplicationContext(), Login.class);
      startActivity(intent);
      finish();
    } else {
      textView.setText(user.userName());
    }

    logoutButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
          }
        });
  }
}
