package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.data.avl.AVLTree;
import com.example.couseplusplus.model.comment.NewComment;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CommentCache {
  List<NewComment> comments;
  AVLTree<Integer, NewComment> helpfulTree;
  AVLTree<String, NewComment> enrolTree;
  AVLTree<String, NewComment> postedTree;
  AVLTree<String, NewComment> textTree;

  public CommentCache(List<NewComment> comments) {
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
