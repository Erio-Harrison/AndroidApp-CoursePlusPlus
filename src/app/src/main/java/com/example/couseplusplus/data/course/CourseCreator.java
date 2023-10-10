package com.example.couseplusplus.data.course;

import com.example.couseplusplus.model.course.NewCourse;
import com.google.firebase.database.DataSnapshot;
import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseCreator {
  public static NewCourse create(DataSnapshot snapshot) {
    FirebaseCourse value =
        Objects.requireNonNull(
            snapshot.getValue(FirebaseCourse.class), "Failed to get course data");
    return new NewCourse(value.courseCode, value.courseName);
  }
}
