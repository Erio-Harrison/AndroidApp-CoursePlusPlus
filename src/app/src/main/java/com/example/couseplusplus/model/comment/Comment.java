package com.example.couseplusplus.model.comment;

import com.google.firebase.database.Exclude;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public class Comment {
  String id;
  String courseCode;
  int year;
  int semester;
  String text;
  int helpfulness;
  String postedDateTime;
  String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS";
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

  public Comment() {
    // Default constructor required for Firebase
  }

  public Comment(
      String id,
      String courseCode,
      int year,
      int semester,
      String text,
      int helpfulness,
      String postedDateTime) {
    this.id = id;
    this.courseCode = courseCode;
    this.year = year;
    this.semester = semester;
    this.text = text;
    this.helpfulness = helpfulness;
    this.postedDateTime = postedDateTime;
  }

  public String getId() {
    return id;
  }

  public String getCourseCode() {
    return courseCode;
  }

  public int getYear() {
    return year;
  }

  public int getSemester() {
    return semester;
  }

  public String getText() {
    return text;
  }

  public int getHelpfulness() {
    return helpfulness;
  }

  public LocalDateTime getPostedDateTime() {
    return LocalDateTime.parse(postedDateTime, formatter);
  }

  // Exclude the 'id' field from Firebase serialization
  @Exclude
  public void setId(String id) {
    this.id = id;
  }

  public String enrolKey() {
    return String.format("%sS%s", year, semester);
  }

  public String date() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);

    // Format the LocalDateTime instance to a String
    String formattedString = getPostedDateTime().format(formatter);
    return formattedString;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
