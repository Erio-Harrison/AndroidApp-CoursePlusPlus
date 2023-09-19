package com.example.couseplusplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

  FirebaseAuth auth;
  Button logoutButton;
  TextView textView;
  FirebaseUser user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    auth = FirebaseAuth.getInstance();
    logoutButton = findViewById(R.id.logout);
    textView = findViewById(R.id.user_details);
    user = auth.getCurrentUser();
    if (user == null) {
      Intent intent = new Intent(getApplicationContext(), Login.class);
      startActivity(intent);
      finish();
    } else {
      textView.setText(user.getEmail());
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
