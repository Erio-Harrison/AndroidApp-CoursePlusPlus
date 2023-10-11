package com.example.couseplusplus.data.course;

import com.example.couseplusplus.data.invertedindex.StringInvertedIndex;
import com.example.couseplusplus.model.course.Course;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

  public List<Course> findByCourseCode(String hint) {
    return new ArrayList<>(courseCache.codeIndex().get(hint));
  }

  public List<Course> findByCourseName(String hint) {
    StringInvertedIndex<Course> nameIndex = courseCache.nameIndex();
    return splitCourseNameHint(hint).stream()
        .map(nameIndex::get)
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }

  List<String> splitCourseNameHint(String hint) {
    return Arrays.asList(hint.split(Course.CourseNameSplitRegex));
  }
}
