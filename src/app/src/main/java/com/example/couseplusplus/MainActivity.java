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
    // TODO: Make the load time shorter - not successful
    // TODO: Show #comments
    // TODO: Organize files
    courseRecycleView = (RecyclerView) findViewById(R.id.course_rv);
    courseRecycleView.setLayoutManager(new LinearLayoutManager(this));
    mDatabase = FirebaseDatabase.getInstance().getReference();

    mDatabase.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {  // Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
        courseList.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
          if (snapshot.getKey().equals("course")) {
            int i = 0;
            for (DataSnapshot courseSnapshot : snapshot.getChildren()) {
              Course courseData = courseSnapshot.getValue(Course.class);
              courseList.add(courseData);
              if (i > 50) break;
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

    // Set an OnClickListener for the RecyclerView items
    courseRecycleView.addOnItemTouchListener(
      new RecyclerViewClickListener(this, courseRecycleView, new RecyclerViewClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
          Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
          intent.putExtra("courseCode", courseList.get(position).getCourseCode());
          intent.putExtra("courseName", courseList.get(position).getCourseName());
          startActivity(intent);
        }

        @Override
        public void onLongItemClick(View view, int position) {
        }
      })
    );
    // LOAD SHOW DATA END

  }
}
