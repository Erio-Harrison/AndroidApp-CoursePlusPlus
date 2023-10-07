package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.data.avl.AVLTree;
import com.example.couseplusplus.model.comment.Comment;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CommentCache {
  List<Comment> comments;
  AVLTree<Integer, Comment> helpfulTree;
  AVLTree<String, Comment> enrolTree;
  AVLTree<String, Comment> postedTree;
  AVLTree<String, Comment> textTree;

  public CommentCache(List<Comment> comments) {
    this.comments = comments;
    helpfulTree = new AVLTree<>();
    enrolTree = new AVLTree<>();
    postedTree = new AVLTree<>();
    textTree = new AVLTree<>();

    comments.forEach(
        comment -> {
          helpfulTree.insert(comment.getHelpfulness(), comment);
          enrolTree.insert(comment.enrolKey(), comment);
          postedTree.insert(comment.getPostedDateTime().toString(), comment);
          textTree.insert(comment.getText(), comment);
        });
  }
}
