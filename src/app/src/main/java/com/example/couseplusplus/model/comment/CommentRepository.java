package com.example.couseplusplus.model.comment;

import com.example.couseplusplus.data.comment.FirebaseComment;
import com.example.couseplusplus.model.query.parser.ParseTree;
import java.util.List;
import java.util.function.Consumer;

/**
 * Comment repository
 *
 * @apiNote {@link #listenChange(String, Consumer)} must be called first before calling {@link
 *     #getAll(String)}, {@link #findAll(String, ParseTree)}.
 */
public interface CommentRepository {

  /**
   * adds a listener to any data change in the future.
   *
   * @param courseCode
   * @param listener a function to be called when there's a change to the subject comment data
   */
  void listenChange(String courseCode, Consumer<List<Comment>> listener);

  List<Comment> getAll(String courseCode);

  /**
   * finds the comments that match with the given parse tree.
   *
   * @param courseCode
   * @param parseTree
   * @return
   */
  List<Comment> findAll(String courseCode, ParseTree parseTree);

  void addHelpfulness(String courseCode, String commentId, int helpfulness);

  /**
   * @author Min su Park
   *
   * This method adds the comment to firebase from the FirebaseCommentService
   *
   * @param courseCode The course for which users will comment on
   * @param comment The instance of FirebaseComment where relevant comment data is stored
   * @param onCompleteListener The completion of uploading to Firebase. This is separated as another parameter because
   *                           UI handling needs to be done in AddComment.java
   */
  void addComment(String courseCode, FirebaseComment comment, Consumer<Boolean> onCompleteListener);
}
