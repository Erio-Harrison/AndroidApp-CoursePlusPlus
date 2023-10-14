package com.example.couseplusplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

  UserService userService = IoCContainer.userService();
  RecyclerView courseRecycleView;
  CourseAdapter courseAdapter;
  List<Course> courseList = new ArrayList<>();
  CourseService courseService = IoCContainer.courseService();

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.courses_menu, menu);
    setListeners(menu);
    return true;
  }

  void setListeners(Menu menu) {
    MenuItem searchItem = menu.findItem(R.id.search_in_course);
    SearchView searchView = (SearchView) Objects.requireNonNull(searchItem.getActionView());
    searchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String text) {
            doSearch(text);
            return true;
          }

          @Override
          public boolean onQueryTextChange(String text) {
            if (!text.isBlank()) return false;
            doSearch(text);
            return true;
          }

          void doSearch(String text) {
            courseList = text.isBlank() ? courseService.getAll() : courseService.findAll(text);
            courseAdapter = new CourseAdapter(courseList);
            courseRecycleView.setAdapter(courseAdapter);
          }
        });

    var handler = new LogoutConfigHandler(R.id.logout_in_course, menu, userService, this);
    handler.configure();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SupportActionBarTitleSetter.set("Course", this);
    setContentView(R.layout.activity_main);

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
