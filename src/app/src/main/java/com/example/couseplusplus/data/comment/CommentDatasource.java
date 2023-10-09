package com.example.couseplusplus.data.comment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.comment.NewComment;
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
    if (Objects.isNull(instance)) return new CommentDatasource();
    return instance;
  }

  String code;
  CommentCache cache;
  CommentFinder commentFinder;

  @Override
  public void listenChange(String courseCode, Consumer<List<NewComment>> listener) {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    mDatabase
        .child("comment")
        .child(courseCode)
        .addValueEventListener(
            new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<NewComment> comments = new ArrayList<>();
                snapshot
                    .getChildren()
                    .forEach(
                        commentSnapshot -> comments.add(CommentCreator.create(commentSnapshot)));
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
  public List<NewComment> getAll(String courseCode) {
    if (isNotReady()) return List.of();
    validate(courseCode);
    return cache.comments();
  }

  @Override
  public List<NewComment> findAll(String courseCode, ParseTree parseTree) {
    if (isNotReady()) return List.of();
    validate(courseCode);
    return Objects.requireNonNullElse(commentFinder.walk(parseTree), List.of());
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
