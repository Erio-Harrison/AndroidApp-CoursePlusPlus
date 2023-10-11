package com.example.couseplusplus.model.course;

import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class Course {
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
