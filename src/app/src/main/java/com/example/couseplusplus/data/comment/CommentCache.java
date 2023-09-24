package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.data.avl.AVLTree;
import com.example.couseplusplus.model.comment.Comment;
import java.util.Comparator;
import java.util.List;

public class CommentCache {
  List<Comment> comments;
  AVLTree<Comment> helpfulTree;
  AVLTree<Comment> enrolTree;
  AVLTree<Comment> postedTree;
  AVLTree<Comment> textTree;

  public CommentCache(List<Comment> comments) {
    this.comments = comments;
    helpfulTree = new AVLTree<>(Comparator.comparing(Comment::helpfulness));
    enrolTree =
        new AVLTree<>(Comparator.comparingInt(Comment::year).thenComparingInt(Comment::semester));
    postedTree = new AVLTree<>(Comparator.comparing(Comment::postedDateTime));
    textTree = new AVLTree<>(Comparator.comparing(Comment::text));

    comments.forEach(
        comment -> {
          helpfulTree.insert(comment);
          enrolTree.insert(comment);
          postedTree.insert(comment);
          textTree.insert(comment);
        });
  }
}
