package com.example.couseplusplus.view.query;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.QueryBuilder;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class QueryElements {
  Pair<Integer, Integer> helpfulFilter;
  Pair<Integer, Integer> enrolYearFilter;
  Pair<Integer, Integer> enrolSemesterFilter;
  Pair<LocalDateTime, LocalDateTime> postedDateTimeFilter;
  String textHint;

  public QueryElements(
      Pair<Integer, Integer> helpfulFilter,
      Pair<Integer, Integer> enrolYearFilter,
      Pair<Integer, Integer> enrolSemesterFilter,
      Pair<LocalDateTime, LocalDateTime> postedDateTimeFilter,
      String textHint) {
    this.helpfulFilter = helpfulFilter;
    this.enrolYearFilter = enrolYearFilter;
    this.enrolSemesterFilter = enrolSemesterFilter;
    this.postedDateTimeFilter = postedDateTimeFilter;
    this.textHint = textHint;
  }

  public Query toQuery() {
    return new QueryBuilder()
        .ifThen(
            () -> Objects.nonNull(helpfulFilter.first),
            builder -> builder.helpful().equalsOrMore().value(helpfulFilter.first))
        .ifThen(
            () -> Objects.nonNull(helpfulFilter.second),
            builder -> {
              addAndIfEmptyBuilder(builder);
              builder.helpful().equalsOrLess().value(helpfulFilter.second);
            })
        .ifThen(
            () ->
                Objects.nonNull(enrolYearFilter.first)
                    && Objects.nonNull(enrolSemesterFilter.first),
            builder -> {
              addAndIfEmptyBuilder(builder);
              builder
                  .enrol()
                  .equalsOrMore()
                  .value(enrolValue(enrolYearFilter.first, enrolSemesterFilter.first));
            })
        .ifThen(
            () ->
                Objects.nonNull(enrolYearFilter.second)
                    && Objects.nonNull(enrolSemesterFilter.second),
            builder -> {
              addAndIfEmptyBuilder(builder);
              builder
                  .enrol()
                  .equalsOrLess()
                  .value(enrolValue(enrolYearFilter.second, enrolSemesterFilter.second));
            })
        .ifThen(
            () -> Objects.nonNull(postedDateTimeFilter.first),
            builder -> {
              addAndIfEmptyBuilder(builder);
              builder.posted().equalsOrMore().localDateTimeValue(postedDateTimeFilter.first);
            })
        .ifThen(
            () -> Objects.nonNull(postedDateTimeFilter.second),
            builder -> {
              addAndIfEmptyBuilder(builder);
              builder.posted().equalsOrLess().localDateTimeValue(postedDateTimeFilter.second);
            })
        .ifThen(
            () -> Objects.nonNull(textHint),
            builder -> {
              addAndIfEmptyBuilder(builder);
              builder.text().like().textValue(textHint);
            })
        .build();
  }

  void addAndIfEmptyBuilder(QueryBuilder builder) {
    if (builder.isEmpty()) return;
    builder.and();
  }

  String enrolValue(Integer year, Integer semester) {
    return String.format("%sS%s", year, semester);
  }
}
