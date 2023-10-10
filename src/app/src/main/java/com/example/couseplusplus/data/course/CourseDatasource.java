package com.example.couseplusplus.data.course;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.couseplusplus.model.course.CourseRepository;
import com.example.couseplusplus.model.course.NewCourse;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseDatasource implements CourseRepository {
  private static CourseDatasource instance;

  public static CourseDatasource getInstance() {
    if (Objects.isNull(instance)) instance = new CourseDatasource();
    return instance;
  }

  CourseCache cache;
  CourseFinder courseFinder;

  @Override
  public void listenChange(Consumer<List<NewCourse>> listener) {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    mDatabase
        .child("course")
        .addValueEventListener(
            new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<NewCourse> courses = new ArrayList<>();
                snapshot
                    .getChildren()
                    .forEach(courseSnapshot -> courses.add(CourseCreator.create(courseSnapshot)));
                cache = new CourseCache(courses);
                courseFinder = new CourseFinder(cache);
                listener.accept(cache.courses());
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
              }
            });
  }

  @Override
  public List<NewCourse> getAll() {
    if (Objects.isNull(cache)) return List.of();
    return cache.courses();
  }

  @Override
  public List<NewCourse> findByCourseCode(String hint) {
    if (Objects.isNull(cache)) return List.of();
    return courseFinder.findByCourseCode(hint);
  }

  @Override
  public List<NewCourse> findByCourseName(String hint) {
    if (Objects.isNull(cache)) return List.of();
    return courseFinder.findByCourseName(hint);
  }
}
