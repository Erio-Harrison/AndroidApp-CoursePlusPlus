package com.example.couseplusplus.data.course;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.course.Course;
import java.util.List;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CourseFinderTest {
  @Test
  public void testFindByCourseCode() {
    CourseCache courseCache =
        new CourseCache(
            List.of(
                new Course("COMP1110", ""),
                new Course("COMP6310", ""),
                new Course("DAWS3110", ""),
                new Course("DAWS4110", ""),
                new Course("DAWS5110", ""),
                new Course("LAWS5110", "")));
    CourseFinder uut = new CourseFinder(courseCache);
    List<Course> result = uut.findByCourseCode("COMP");
    assertEquals(2, result.size());
  }
}
