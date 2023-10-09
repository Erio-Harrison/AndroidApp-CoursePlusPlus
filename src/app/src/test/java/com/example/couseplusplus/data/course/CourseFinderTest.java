package com.example.couseplusplus.data.course;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.course.NewCourse;
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
                new NewCourse("COMP1110", ""),
                new NewCourse("COMP6310", ""),
                new NewCourse("DAWS3110", ""),
                new NewCourse("DAWS4110", ""),
                new NewCourse("DAWS5110", ""),
                new NewCourse("LAWS5110", "")));
    CourseFinder uut = new CourseFinder(courseCache);
    List<NewCourse> result = uut.findByCourseCode("COMP");
    assertEquals(2, result.size());
  }
}
