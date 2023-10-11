package com.example.couseplusplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.couseplusplus.CourseAdapter;
import com.example.couseplusplus.IoCContainer;
import com.example.couseplusplus.R;
import com.example.couseplusplus.RecyclerViewClickListener;
import com.example.couseplusplus.model.course.Course;
import com.example.couseplusplus.service.course.CourseService;
import com.example.couseplusplus.service.user.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  Button logoutButton;
  TextView textView;
  UserService userService;
  RecyclerView courseRecycleView;
  CourseAdapter courseAdapter;
  List<Course> courseList = new ArrayList<>();
  CourseService courseService;
  EditText searchInput;
  Button searchButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    userService = IoCContainer.userService();
    courseService = IoCContainer.courseService();
    logoutButton = findViewById(R.id.logout);
    textView = findViewById(R.id.user_details);
    searchInput = findViewById(R.id.course_search_input);
    searchButton = findViewById(R.id.course_search_button);

    userService
        .findCurrentUser()
        .ifPresentOrElse(
            user -> textView.setText(user.userName()),
            () -> {
              Intent intent = new Intent(getApplicationContext(), Login.class);
              startActivity(intent);
              finish();
            });

    logoutButton.setOnClickListener(
        view -> {
          userService.logout();
          Intent intent = new Intent(getApplicationContext(), Login.class);
          startActivity(intent);
          finish();
        });

    searchButton.setOnClickListener(
        view -> {
          String input = searchInput.getText().toString();
          courseList = input.isBlank() ? courseService.getAll() : courseService.findAll(input);
          courseAdapter = new CourseAdapter(courseList);
          courseRecycleView.setAdapter(courseAdapter);
        });

    courseRecycleView = (RecyclerView) findViewById(R.id.course_rv);
    courseRecycleView.setLayoutManager(new LinearLayoutManager(this));
    courseService.listenChange(
        courses -> {
          courseList = courses;
          if (Objects.nonNull(courseAdapter)) return;
          courseAdapter = new CourseAdapter(courseList);
          courseRecycleView.setAdapter(courseAdapter);
        });

    // Set an OnClickListener for the RecyclerView items
    courseRecycleView.addOnItemTouchListener(
        new RecyclerViewClickListener(
            this,
            courseRecycleView,
            new RecyclerViewClickListener.OnItemClickListener() {
              @Override
              public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                intent.putExtra("courseCode", courseList.get(position).courseCode());
                intent.putExtra("courseName", courseList.get(position).courseName());
                startActivity(intent);
              }

              @Override
              public void onLongItemClick(View view, int position) {}
            }));
  }
}