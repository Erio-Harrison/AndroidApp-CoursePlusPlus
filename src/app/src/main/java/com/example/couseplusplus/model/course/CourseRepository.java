package com.example.couseplusplus.model.course;

import java.util.List;

public interface CourseRepository {
  List<Course> getAll();

  List<Course> findAll(String hint);
}
