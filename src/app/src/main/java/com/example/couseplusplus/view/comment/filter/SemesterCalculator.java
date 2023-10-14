package com.example.couseplusplus.view.comment.filter;

import java.time.LocalDate;
import java.time.Month;

public class SemesterCalculator {
  public static int calculate(LocalDate localDate) {
    if (localDate.isBefore(LocalDate.of(localDate.getYear(), Month.JANUARY, 1))
        || localDate.isAfter(
            LocalDate.of(localDate.getYear(), Month.JULY, Month.JULY.length(false)))) return 2;
    return 1;
  }
}
