package com.example.couseplusplus.model.course;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;

public class CourseTest {
  @Test
  public void testSplitCourseCode() {
    Course uut = new Course("COMP6442", "Software Construction");
    List<String> result = uut.splitCourseCode();
    assertEquals(
        List.of("C", "CO", "COM", "COMP", "COMP6", "COMP64", "COMP644", "COMP6442"), result);
  }

  @Test
  public void testSplitCourseName() {
    Course uut = new Course("COMP6442", "Software Construction");
    List<String> result = uut.splitCourseName();
    assertEquals(List.of("Software", "Construction"), result);
  }
}
