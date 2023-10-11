package com.example.couseplusplus.data.course;

import com.example.couseplusplus.model.course.Course;
import com.google.firebase.database.DataSnapshot;
import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseCreator {
  public static Course create(DataSnapshot snapshot) {
    FirebaseCourse value =
        Objects.requireNonNull(
            snapshot.getValue(FirebaseCourse.class), "Failed to get course data");
    return new Course(value.courseCode, value.courseName);
  }
}
