package com.example.couseplusplus.model.comment;

import java.time.LocalDateTime;
import java.util.Objects;

// FIXME consolidate with Comment in the future.
//    the sole reason of this class is to avoid merge conflict
//    with Comment class.
/**
 * Represents a comment of a course.
 *
 * @author Yuki Misumi (u7582380)
 */
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
    NewComment that = (NewComment) o;
    return year == that.year
        && semester == that.semester
        && helpfulness == that.helpfulness
        && Objects.equals(id, that.id)
        && Objects.equals(courseCode, that.courseCode)
        && Objects.equals(text, that.text)
        && Objects.equals(postedDateTime, that.postedDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, courseCode, year, semester, text, helpfulness, postedDateTime);
  }
}