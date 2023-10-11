package com.example.couseplusplus.model.course;

import java.util.List;
import java.util.function.Consumer;

public interface CourseRepository {
  void listenChange(Consumer<List<Course>> listener);

  List<Course> getAll();

  List<Course> findByCourseCode(String hint);

  List<Course> findByCourseName(String hint);
}
