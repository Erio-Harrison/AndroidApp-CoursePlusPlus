package com.example.couseplusplus.model.course;

import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class NewCourse {
  private String courseCode;
  private String courseName;

  public NewCourse(String courseCode, String courseName) {
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
    NewCourse newCourse = (NewCourse) o;
    return Objects.equals(courseCode, newCourse.courseCode)
        && Objects.equals(courseName, newCourse.courseName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseCode, courseName);
  }
}
