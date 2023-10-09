package com.example.couseplusplus.data.course;

import com.example.couseplusplus.model.course.NewCourse;
import com.google.firebase.database.DataSnapshot;
import java.util.Objects;

public class CourseCreator {
  public static NewCourse create(DataSnapshot snapshot) {
    FirebaseCourse firebaseCourse =
        Objects.requireNonNull(
            snapshot.getValue(FirebaseCourse.class), "Failed to get course data");
    return new NewCourse(firebaseCourse.courseCode, firebaseCourse.courseName);
  }

  private static class FirebaseCourse {
    String courseCode;

    String courseName;

    public FirebaseCourse() {}

    public String getCourseCode() {
      return courseCode;
    }

    public void setCourseCode(String courseCode) {
      this.courseCode = courseCode;
    }

    public String getCourseName() {
      return courseName;
    }

    public void setCourseName(String courseName) {
      this.courseName = courseName;
    }
  }
}
