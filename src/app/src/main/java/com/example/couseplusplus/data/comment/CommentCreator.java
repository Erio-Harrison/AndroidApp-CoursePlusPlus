package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.NewComment;
import com.google.firebase.database.DataSnapshot;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommentCreator {
  public static NewComment create(DataSnapshot snapshot) {
    FirebaseComment value =
        Objects.requireNonNull(
            snapshot.getValue(FirebaseComment.class), "Failed to get comment data");
    return new NewComment(
        value.id,
        value.courseCode,
        value.year,
        value.semester,
        value.text,
        value.helpfulness,
        LocalDateTime.parse(value.postedDateTime));
  }

  private static class FirebaseComment {
    String id;
    String courseCode;
    int year;
    int semester;
    String text;
    int helpfulness;
    String postedDateTime;

    public FirebaseComment() {}

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getCourseCode() {
      return courseCode;
    }

    public void setCourseCode(String courseCode) {
      this.courseCode = courseCode;
    }

    public int getYear() {
      return year;
    }

    public void setYear(int year) {
      this.year = year;
    }

    public int getSemester() {
      return semester;
    }

    public void setSemester(int semester) {
      this.semester = semester;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    public int getHelpfulness() {
      return helpfulness;
    }

    public void setHelpfulness(int helpfulness) {
      this.helpfulness = helpfulness;
    }

    public String getPostedDateTime() {
      return postedDateTime;
    }

    public void setPostedDateTime(String postedDateTime) {
      this.postedDateTime = postedDateTime;
    }
  }
}
