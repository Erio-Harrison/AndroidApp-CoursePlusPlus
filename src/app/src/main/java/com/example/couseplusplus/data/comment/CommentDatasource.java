package com.example.couseplusplus.data.comment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.query.parser.ParseTree;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CommentDatasource implements CommentRepository {
  private static CommentDatasource instance;

  public static CommentDatasource getInstance() {
    if (Objects.isNull(instance)) instance = new CommentDatasource();
    return instance;
  }

  String code;
  CommentCache cache;
  CommentFinder commentFinder;

  @Override
  public void listenChange(String courseCode, Consumer<List<Comment>> listener) {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase
        .child("comment")
        .child(courseCode)
        .addValueEventListener(
            new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Comment> comments = new ArrayList<>();
                snapshot
                    .getChildren()
                    .forEach(
                        commentSnapshot ->
                            comments.add(CommentCreator.create(commentSnapshot, courseCode)));
                cache = new CommentCache(comments);
                commentFinder = new CommentFinder(cache);
                code = courseCode;
                listener.accept(comments);
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
              }
            });
  }

  @Override
  public List<Comment> getAll(String courseCode) {
    if (isNotReady()) return List.of();
    validate(courseCode);
    return cache.comments();
  }

  @Override
  public List<Comment> findAll(String courseCode, ParseTree parseTree) {
    if (isNotReady()) return List.of();
    validate(courseCode);
    return Objects.requireNonNullElse(commentFinder.walk(parseTree), List.of());
  }

  @Override
  public void addHelpfulness(String courseCode, String commentId, int helpfulness) {}

  /**
   * @author Min su Park
   * This is where firebase logic will be handled so that the service side does not have to deal with it.
   *
   * @param courseCode The course for which users will comment on
   * @param comment The instance of FirebaseComment where relevant comment data is stored
   * @param onCompleteListener The completion of uploading to Firebase. This is separated as another parameter because
   *                           UI handling needs to be done in AddComment.java
   */
  @Override
  public void addComment(String courseCode, FirebaseComment comment, Consumer<Boolean> onCompleteListener) {
    DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference();
    mDatabase
            .child("comment")
            .child(courseCode)
            .push()
            .setValue(comment)
            .addOnCompleteListener(
                    task -> {
                      boolean result = task.isSuccessful();
                      onCompleteListener.accept(result);
                    });
  }

  boolean isNotReady() {
    return Objects.isNull(cache);
  }

  void validate(String courseCode) {
    if (courseCode.equals(code)) return;
    throw new IllegalArgumentException(
        String.format("courseCode(%s) does not match with %s", courseCode, code));
  }
}
