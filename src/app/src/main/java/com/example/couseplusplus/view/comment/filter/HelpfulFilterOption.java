package com.example.couseplusplus.view.comment.filter;

import com.example.couseplusplus.view.comment.query.Pair;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Yuki Misumi (u7582380)
 */
public enum HelpfulFilterOption {
  None("None", new Pair<>(null, null)),
  Small("1 Or More", new Pair<>(1, null)),
  Medium("10 Or More", new Pair<>(10, null)),
  Large("50 Or More", new Pair<>(50, null)),
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
    return List.of(None.title, Small.title, Medium.title, Large.title);
  }

  public Pair<Integer, Integer> minMax() {
    return minMax;
  }
}
