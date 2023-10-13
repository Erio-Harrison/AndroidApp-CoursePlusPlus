package com.example.couseplusplus.service.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.CommentRepository;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Min su Park
 *     <p>This is the service side class to validate comment content, then pass it to the data
 *     handling class.
 */
public class FireBaseCommentService {
  CommentRepository commentRepository;

  public FireBaseCommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public void addComment(
          String courseCode, Comment comment, Consumer<Boolean> onCompleteListener) {
    if (Objects.isNull(courseCode) || courseCode.isBlank()) {
      throw new IllegalArgumentException("courseCode could not be identified");
    }
    if (Objects.isNull(comment)) {
      throw new IllegalArgumentException("comment could not be identified");
    }

    commentRepository.addComment(courseCode, comment, onCompleteListener);
  }
}
