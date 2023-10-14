package com.example.couseplusplus.view.comment.filter;

import com.example.couseplusplus.view.comment.query.Pair;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public enum PostedDateFilterOption {
  Option1("None", () -> new Pair<>(null, null)),
  Option2(
      "This Month",
      () -> {
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = now.withDayOfMonth(now.lengthOfMonth()).plusDays(1).atStartOfDay();
        return new Pair<>(start, end);
      }),
  Option3(
      "This Year",
      () -> {
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.withMonth(Month.JANUARY.getValue()).atStartOfDay();
        LocalDateTime end = now.withMonth(Month.DECEMBER.getValue()).atStartOfDay();
        return new Pair<>(start, end);
      }),
  Option4(
      "These 2 Years",
      () -> {
        LocalDate now = LocalDate.now();
        LocalDateTime start = now.minusYears(1).withMonth(Month.JANUARY.getValue()).atStartOfDay();
        LocalDateTime end = now.withMonth(Month.DECEMBER.getValue()).atStartOfDay();

        return new Pair<>(start, end);
      }),
  ;

  final String title;
  final Supplier<Pair<LocalDateTime, LocalDateTime>> datetimeMinMax;

  PostedDateFilterOption(
      String title, Supplier<Pair<LocalDateTime, LocalDateTime>> datetimeMinMax) {
    this.title = title;
    this.datetimeMinMax = datetimeMinMax;
  }

  public static PostedDateFilterOption getBy(String title) {
    Optional<PostedDateFilterOption> result =
        Arrays.stream(values()).filter(option -> title.equals(option.title)).findFirst();
    if (result.isPresent()) return result.get();
    throw new IllegalArgumentException("option not found");
  }

  public static List<String> titles() {
    return List.of(Option1.title, Option2.title, Option3.title, Option4.title);
  }

  public Pair<LocalDateTime, LocalDateTime> datetimeMinMax() {
    return datetimeMinMax.get();
  }
}
