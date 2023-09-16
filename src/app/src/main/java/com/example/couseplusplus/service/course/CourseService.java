package com.example.couseplusplus.service.course;

import com.example.couseplusplus.model.course.CourseRepository;

public class CourseService {
  CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }
}
