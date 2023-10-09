package com.example.couseplusplus.data.course;

import com.example.couseplusplus.model.course.NewCourse;
import java.util.Comparator;
import java.util.List;

public class CourseFinder {
  CourseCache courseCache;

  public CourseFinder(CourseCache courseCache) {
    this.courseCache = courseCache;
  }

  public List<NewCourse> findByCourseCode(String hint) {
    return courseCache.codeTree().collectEqualOrMoreThan(hint, stringComparator());
  }

  public List<NewCourse> findByCourseName(String hint) {
    return courseCache.nameTree().collectEqualOrMoreThan(hint, stringComparator());
  }

  Comparator<String> stringComparator() {
    return (key1, key2) -> {
      if (key1.equals(key2)) return 0;
      return key2.contains(key1) ? -1 : 1;
    };
  }
}
