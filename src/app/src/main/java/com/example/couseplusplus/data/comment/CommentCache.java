package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.data.avl.AVLTree;
import com.example.couseplusplus.model.comment.Comment;
import java.util.List;

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
          helpfulTree.insert(comment.helpfulness(), comment);
          enrolTree.insert(comment.enrolKey(), comment);
          postedTree.insert(comment.postedDateTime().toString(), comment);
          textTree.insert(comment.text(), comment);
        });
  }
}
