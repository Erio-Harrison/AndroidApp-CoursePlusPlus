package com.example.couseplusplus.service.course;

import com.example.couseplusplus.model.course.Course;
import com.example.couseplusplus.model.course.CourseRepository;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseService {
  CourseRepository courseRepository;

  public CourseService(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  public void listenChange(Consumer<List<Course>> listener) {
    courseRepository.listenChange(listener);
  }

  public List<Course> findAll(String hint) {
    return Stream.of(
            courseRepository.findByCourseCode(hint), courseRepository.findByCourseName(hint))
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }

  public List<Course> getAll() {
    return courseRepository.getAll();
  }
}
