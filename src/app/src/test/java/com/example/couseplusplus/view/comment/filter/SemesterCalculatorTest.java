package com.example.couseplusplus.view.comment.filter;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class SemesterCalculatorTest {
  @Test
  public void testCalculate() {
    assertEquals(1, SemesterCalculator.calculate(LocalDate.of(2000, 1, 1)));
    assertEquals(1, SemesterCalculator.calculate(LocalDate.of(2000, 7, 31)));
    assertEquals(2, SemesterCalculator.calculate(LocalDate.of(2000, 8, 1)));
    assertEquals(2, SemesterCalculator.calculate(LocalDate.of(2000, 12, 31)));
  }
}
