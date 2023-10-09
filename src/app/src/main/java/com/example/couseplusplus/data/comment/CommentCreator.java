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
}
