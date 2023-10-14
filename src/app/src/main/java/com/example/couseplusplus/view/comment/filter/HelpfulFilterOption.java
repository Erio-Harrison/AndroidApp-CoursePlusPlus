package com.example.couseplusplus.view.comment.filter;

import com.example.couseplusplus.view.comment.query.Pair;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum HelpfulFilterOption {
  Option1("None", new Pair<>(null, null)),
  Option2("1 Or More", new Pair<>(1, null)),
  Option3("10 Or More", new Pair<>(10, null)),
  Option4("50 Or More", new Pair<>(50, null)),
  ;

  final String title;
  final Pair<Integer, Integer> minMax;

  HelpfulFilterOption(String title, Pair<Integer, Integer> minMax) {
    this.title = title;
    this.minMax = minMax;
  }

  public static HelpfulFilterOption getBy(String title) {
    Optional<HelpfulFilterOption> result =
        Arrays.stream(values()).filter(option -> title.equals(option.title)).findFirst();
    if (result.isPresent()) return result.get();
    throw new IllegalArgumentException("option not found");
  }

  public static List<String> titles() {
    return List.of(Option1.title, Option2.title, Option3.title, Option4.title);
  }

  public Pair<Integer, Integer> minMax() {
    return minMax;
  }
}
