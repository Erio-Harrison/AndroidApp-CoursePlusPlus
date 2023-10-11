package com.example.couseplusplus.data.course;

import com.example.couseplusplus.data.invertedindex.StringInvertedIndex;
import com.example.couseplusplus.model.course.Course;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseCache {
  List<Course> courses;
  StringInvertedIndex<Course> codeIndex;
  StringInvertedIndex<Course> nameIndex;

  public CourseCache(List<Course> courses) {
    this.courses = courses;
    codeIndex = new StringInvertedIndex<>();
    nameIndex = new StringInvertedIndex<>();

    courses.forEach(
        course -> {
          course.splitCourseCode().forEach(chunk -> codeIndex.add(chunk, course));
          course.splitCourseName().forEach(chunk -> nameIndex.add(chunk, course));
        });
  }

  public List<Course> courses() {
    return courses;
  }

  public StringInvertedIndex<Course> codeIndex() {
    return codeIndex;
  }

  public StringInvertedIndex<Course> nameIndex() {
    return nameIndex;
  }
}
