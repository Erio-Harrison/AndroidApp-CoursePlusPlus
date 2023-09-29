package com.example.couseplusplus;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couseplusplus.model.course.Course;
import com.example.couseplusplus.model.user.User;
import com.example.couseplusplus.service.user.UserService;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  Button logoutButton;
  TextView textView;
  User user;
  UserService userService;
  DatabaseReference mDatabase;
  RecyclerView courseRecycleView;
  CourseAdapter courseAdapter;
  List<Course> courseList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    userService = IoCContainer.userService();
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
            userService.logout();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
          }
        });

    // LOAD SHOW DATA
    courseRecycleView = (RecyclerView) findViewById(R.id.course_rv);
    courseRecycleView.setLayoutManager(new LinearLayoutManager(this));
    mDatabase = FirebaseDatabase.getInstance().getReference();

    mDatabase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {  // Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
          if (snapshot.getKey().equals("course")) {
            int i = 0;
            for (DataSnapshot courseSnapshot : snapshot.getChildren()) {
              if (i < 5) {
                String courseCode = courseSnapshot.getKey();
                Map<String, Object> courseName = (Map<String, Object>) courseSnapshot.getValue();
                courseList.add(new Course(courseCode, courseName.toString()));
              }
              i++;
            }
          }
        }
        courseAdapter = new CourseAdapter(courseList);
        courseRecycleView.setAdapter(courseAdapter);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {  // Failed to read value
        Log.w(TAG, "Failed to read value.", error.toException());
      }
    });
    // LOAD SHOW DATA END

  }
}
