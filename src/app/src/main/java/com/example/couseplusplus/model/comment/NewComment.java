package com.example.couseplusplus.model.comment;

import java.time.LocalDateTime;
import java.util.Objects;

public class NewComment {
  String id;
  String courseCode;
  int year;
  int semester;
  String text;
  int helpfulness;
  LocalDateTime postedDateTime;

  public NewComment(
      String id,
      String courseCode,
      int year,
      int semester,
      String text,
      int helpfulness,
      LocalDateTime postedDateTime) {
    this.id = id;
    this.courseCode = courseCode;
    this.year = year;
    this.semester = semester;
    this.text = text;
    this.helpfulness = helpfulness;
    this.postedDateTime = postedDateTime;
  }

  public String id() {
    return id;
  }

  public String courseCode() {
    return courseCode;
  }

  public int year() {
    return year;
  }

  public int semester() {
    return semester;
  }

  public String text() {
    return text;
  }

  public int helpfulness() {
    return helpfulness;
  }

  public LocalDateTime postedDateTime() {
    return postedDateTime;
  }

  public String enrolKey() {
    return String.format("%sS%s", year, semester);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewComment comment = (NewComment) o;
    return Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
