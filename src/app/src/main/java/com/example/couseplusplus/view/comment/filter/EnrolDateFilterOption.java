package com.example.couseplusplus.view.comment.filter;

import com.example.couseplusplus.view.comment.query.Pair;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Yuki Misumi (u7582380)
 */
public enum EnrolDateFilterOption {
  None("None", () -> new Pair<>(null, null), () -> new Pair<>(null, null)),
  Small(
      "This Semester",
      () -> {
        LocalDate now = LocalDate.now();
        return new Pair<>(now.getYear(), now.getYear());
      },
      () -> {
        LocalDate now = LocalDate.now();
        int semester = SemesterCalculator.calculate(now);
        return new Pair<>(semester, semester);
      }),
  Medium(
      "This Year",
      () -> {
        LocalDate now = LocalDate.now();
        return new Pair<>(now.getYear(), now.getYear());
      },
      () -> new Pair<>(1, 2)),
  Large(
      "These 2 Years",
      () -> {
        LocalDate now = LocalDate.now();
        return new Pair<>(now.getYear() - 1, now.getYear());
      },
      () -> new Pair<>(1, 2)),
  ;

  final String title;
  final Supplier<Pair<Integer, Integer>> yearMinMax;
  final Supplier<Pair<Integer, Integer>> semesterMinMax;

  EnrolDateFilterOption(
      String title,
      Supplier<Pair<Integer, Integer>> yearMinMax,
      Supplier<Pair<Integer, Integer>> semesterMinMax) {
    this.title = title;
    this.yearMinMax = yearMinMax;
    this.semesterMinMax = semesterMinMax;
  }

  public static EnrolDateFilterOption getBy(String title) {
    Optional<EnrolDateFilterOption> result =
        Arrays.stream(values()).filter(option -> title.equals(option.title)).findFirst();
    if (result.isPresent()) return result.get();
    throw new IllegalArgumentException("option not found");
  }

  public static List<String> titles() {
    return List.of(None.title, Small.title, Medium.title, Large.title);
  }

  public Pair<Integer, Integer> yearMinMax() {
    return yearMinMax.get();
  }

  public Pair<Integer, Integer> semesterMinMax() {
    return semesterMinMax.get();
  }
}
