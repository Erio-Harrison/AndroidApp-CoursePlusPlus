package com.example.couseplusplus.service.course;

import com.example.couseplusplus.model.course.CourseRepository;
import com.example.couseplusplus.model.course.NewCourse;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseService {
  CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public void listenChange(Consumer<List<NewCourse>> listener) {
    courseRepository.listenChange(listener);
  }

  public List<NewCourse> findAll(String hint) {
    return Stream.of(
            courseRepository.findByCourseCode(hint), courseRepository.findByCourseName(hint))
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }
}
