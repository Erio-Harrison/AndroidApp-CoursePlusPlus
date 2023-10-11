package com.example.couseplusplus.model.comment;

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
  void listenChange(String courseCode, Consumer<List<NewComment>> listener);

  List<NewComment> getAll(String courseCode);

  /**
   * finds the comments that match with the given parse tree.
   *
   * @param courseCode
   * @param parseTree
   * @return
   */
  List<NewComment> findAll(String courseCode, ParseTree parseTree);
  void addHelpfulness(String courseCode, String commentId, int helpfulness);
  void addComment(String courseCode, Comment comment);
}
