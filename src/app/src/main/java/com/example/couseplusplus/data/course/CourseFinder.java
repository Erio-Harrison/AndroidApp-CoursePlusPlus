package com.example.couseplusplus.data.course;

import com.example.couseplusplus.model.course.Course;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseFinder {
  CourseCache courseCache;

  public CourseFinder(CourseCache courseCache) {
    this.courseCache = courseCache;
  }

  // FIXME avl tree didn't work quite well here
  public List<Course> findByCourseCode(String hint) {
    return courseCache.courses().stream()
        .filter(c -> c.courseCode().contains(hint))
        .collect(Collectors.toList());
  }

  public List<Course> findByCourseName(String hint) {
    return courseCache.courses().stream()
        .filter(c -> c.courseName().contains(hint))
        .collect(Collectors.toList());
  }
}
