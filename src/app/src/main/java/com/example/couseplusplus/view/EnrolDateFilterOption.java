package com.example.couseplusplus.view;

import com.example.couseplusplus.view.query.Pair;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public enum EnrolDateFilterOption {
  Option1("None", () -> new Pair<>(null, null), () -> new Pair<>(null, null)),
  Option2(
      "This Semester",
      () -> {
        LocalDate now = LocalDate.now();
        return new Pair<>(now.getYear(), now.getYear());
      },
      () -> {
        LocalDate now = LocalDate.now();
        if (now.isBefore(LocalDate.of(now.getYear(), Month.FEBRUARY, 1))
            && now.isAfter(
                LocalDate.of(now.getYear(), Month.NOVEMBER, Month.NOVEMBER.length(false))))
          return new Pair<>(2, 2);
        return new Pair<>(1, 1);
      }),
  Option3(
      "This Year",
      () -> {
        LocalDate now = LocalDate.now();
        return new Pair<>(now.getYear(), now.getYear());
      },
      () -> new Pair<>(1, 2)),
  Option4(
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
    return List.of(Option1.title, Option2.title, Option3.title, Option4.title);
  }

  public Pair<Integer, Integer> yearMinMax() {
    return yearMinMax.get();
  }

  public Pair<Integer, Integer> semesterMinMax() {
    return semesterMinMax.get();
  }
}
