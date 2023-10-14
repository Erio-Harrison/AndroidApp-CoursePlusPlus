package com.example.couseplusplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.couseplusplus.IoCContainer;
import com.example.couseplusplus.R;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.service.user.UserService;
import com.example.couseplusplus.view.course.MainActivity;

/**
 * This Activity Class is the entry point to the App. Here users will either have to authenticate
 * themselves in logging-in via firebase authentication. Upon success login, users will be taken to
 * MainActivity
 *
 * <p>Additionally, users can register ID and Password to firebase.
 *
 * @author Min su Park
 */
public class Login extends AppCompatActivity {
  EditText editTextEmail, editTextPassword;
  Button buttonLogin;
  ProgressBar progressBar;
  TextView textView;

  UserService userService;

  @Override
  public void onStart() {
    super.onStart();
    User user = userService.getCurrentUser();
    if (user != null) {
      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
      startActivity(intent);
      finish();
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    userService = IoCContainer.userService();

    setContentView(R.layout.activity_login);
    editTextEmail = findViewById(R.id.email);
    editTextPassword = findViewById(R.id.password);
    buttonLogin = findViewById(R.id.btn_login);
    progressBar = findViewById(R.id.progressbar);
    textView = findViewById(R.id.RegisterNow);

    textView.setOnClickListener(
        view -> {
          Intent intent = new Intent(getApplicationContext(), Register.class);
          startActivity(intent);
          finish();
        });

    buttonLogin.setOnClickListener(
        view -> {
          progressBar.setVisibility(View.VISIBLE);
          String email, password;
          email = String.valueOf(editTextEmail.getText());
          password = String.valueOf(editTextPassword.getText());

          if (TextUtils.isEmpty(email)) {
            Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
          }

          if (TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
          }

          User user = new User(email, password);
          userService.login(
              user,
              result -> {
                progressBar.setVisibility(View.GONE);
                if (result.isSuccessful()) {
                  String text = String.format("Welcome %s!", result.user().userName());
                  Toast.makeText(Login.this, text, Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                  startActivity(intent);
                  finish();
                } else {
                  Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
              });
        });
  }
}
