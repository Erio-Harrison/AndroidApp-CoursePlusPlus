package com.example.couseplusplus.view.addcomment;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class YearGenerator {
  public static List<String> generateAsString(Year from, Year until) {
    ArrayList<String> result = new ArrayList<>();
    if (from.isAfter(until)) return List.of();

    while (from.isBefore(until)) {
      result.add(from.toString());
      from = from.plusYears(1);
    }
    result.add(from.toString());

    return result;
  }
}
