package com.example.couseplusplus.model.course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class Course {
  public static final String CourseNameSplitRegex = " ";
  private String courseCode;
  private String courseName;

  public Course(String courseCode, String courseName) {
    this.courseCode = courseCode;
    this.courseName = courseName;
  }

  public String courseCode() {
    return courseCode;
  }

  public String courseName() {
    return courseName;
  }

  public List<String> splitCourseCode() {
    List<String> result = new ArrayList<>();
    int length = courseCode.length();
    for (int i = 0; i < length; i++) {
      result.add(courseCode.substring(0, i + 1));
    }
    return result;
  }

  public List<String> splitCourseName() {
    return Arrays.asList(courseName.split(CourseNameSplitRegex));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Course course = (Course) o;
    return Objects.equals(courseCode, course.courseCode)
        && Objects.equals(courseName, course.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseCode, courseName);
  }
}
