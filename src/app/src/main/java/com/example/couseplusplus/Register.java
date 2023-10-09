package com.example.couseplusplus;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.service.user.UserService;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

/**
 * Author: Min su Park
 *
 * <p>Register adds users into the Authentification database in firebase. Once registered, user will
 * be taken to MainActivity
 */
public class Register extends AppCompatActivity {
  TextInputEditText editTextEmail, editTextPassword;
  Button buttonRegister;
  ProgressBar progressBar;
  TextView textView;
  UserService userService;

  @Override
  public void onStart() {
    super.onStart();
    User user = userService.getCurrentUser();
    if (Objects.nonNull(user)) {
      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
      startActivity(intent);
      finish();
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    userService = IoCContainer.userService();
    editTextEmail = findViewById(R.id.email);
    editTextPassword = findViewById(R.id.password);
    buttonRegister = findViewById(R.id.btn_register);
    progressBar = findViewById(R.id.progressbar);
    textView = findViewById(R.id.loginNow);

    textView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
          }
        });

    buttonRegister.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());

            if (TextUtils.isEmpty(email)) {
              Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
              return;
            }

            if (TextUtils.isEmpty(password)) {
              Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
              return;
            }

            User user = new User(email, password);
            userService.register(
                user,
                result -> {
                  progressBar.setVisibility(View.GONE);
                  if (result.isSuccessful()) {
                    Toast.makeText(Register.this, "Account created.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                  } else {
                    Toast.makeText(Register.this, "Account creation failed.", Toast.LENGTH_SHORT)
                        .show();
                  }
                });
          }
        });
  }
}
