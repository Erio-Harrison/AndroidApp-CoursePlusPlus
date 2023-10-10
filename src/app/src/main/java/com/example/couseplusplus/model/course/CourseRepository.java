package com.example.couseplusplus.model.course;

import java.util.List;
import java.util.function.Consumer;

public interface CourseRepository {
  void listenChange(Consumer<List<NewCourse>> listener);

  List<NewCourse> getAll();

  List<NewCourse> findByCourseCode(String hint);

  List<NewCourse> findByCourseName(String hint);
}
