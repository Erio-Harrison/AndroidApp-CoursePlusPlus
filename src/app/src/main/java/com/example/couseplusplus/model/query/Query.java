package com.example.couseplusplus.model.query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Yuki Misumi (u7582380)
 */
public class Query {
  String value;
  int length;

  public Query(String value) {
    this.value = value;
    length = value.length();
  }

  public Optional<Character> find(int index) {
    if (isOutOfRange(index)) return Optional.empty();
    return Optional.of(value.charAt(index));
  }

  public boolean isSpace(int index) {
    return !isOutOfRange(index) && value.charAt(index) == ' ';
  }

  public boolean isDoubleQuote(int index) {
    return !isOutOfRange(index) && value.charAt(index) == '"';
  }

  public boolean isOutOfRange(int index) {
    return index > length - 1 || index < 0;
  }

  public boolean isDigit(int index) {
    if (isOutOfRange(index)) return false;
    return Character.isDigit(value.charAt(index));
  }

  public boolean isHyphen(int index) {
    return is(index, '-');
  }

  public boolean isS(int index) {
    return is(index, 'S');
  }

  public boolean is(int index, char subject) {
    if (isOutOfRange(index)) return false;
    return value.charAt(index) == subject;
  }

  public Integer getInteger(int start, int end) {
    return Integer.parseInt(value.substring(start, end));
  }

  public LocalDateTime getDateTime(int start, int end) {
    String datetimeString = value.substring(start, end);
    try {
      return LocalDateTime.parse(datetimeString);
    } catch (DateTimeParseException e) {
      return LocalDate.parse(datetimeString).atStartOfDay();
    }
  }

  public String getSubstring(int start, int end) {
    return value.substring(start, end);
  }

  public boolean isBlank() {
    return Objects.isNull(value) || value.isBlank();
  }
}
