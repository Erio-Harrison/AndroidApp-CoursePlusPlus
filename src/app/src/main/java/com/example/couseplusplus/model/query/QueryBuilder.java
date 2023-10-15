package com.example.couseplusplus.model.query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Yuki Misumi (u7582380)
 */
public class QueryBuilder {
  StringBuilder buffer;

  public QueryBuilder() {
    buffer = new StringBuilder();
  }

  public QueryBuilder and() {
    buffer.append("&");
    return this;
  }

  public QueryBuilder or() {
    buffer.append("|");
    return this;
  }

  public QueryBuilder equalsOrMore() {
    buffer.append(">=");
    return this;
  }

  public QueryBuilder equalsOrLess() {
    buffer.append("<=");
    return this;
  }

  public QueryBuilder like() {
    buffer.append("~");
    return this;
  }

  public QueryBuilder value(Object value) {
    buffer.append(value);
    return this;
  }

  public QueryBuilder textValue(String value) {
    buffer.append(String.format("\"%s\"", value));
    return this;
  }

  public QueryBuilder localDateTimeValue(LocalDateTime value) {
    buffer.append(value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    return this;
  }

  public QueryBuilder helpful() {
    buffer.append("helpful");
    return this;
  }

  public QueryBuilder enrol() {
    buffer.append("enrol");
    return this;
  }

  public QueryBuilder posted() {
    buffer.append("posted");
    return this;
  }

  public QueryBuilder text() {
    buffer.append("text");
    return this;
  }

  public QueryBuilder ifThen(Supplier<Boolean> condition, Consumer<QueryBuilder> then) {
    if (condition.get()) then.accept(this);
    return this;
  }

  public Query build() {
    return new Query(buffer.toString());
  }

  public boolean isEmpty() {
    return buffer.length() == 0;
  }
}
