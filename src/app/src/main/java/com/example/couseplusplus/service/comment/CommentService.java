package com.example.couseplusplus.service.comment;

import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.Query;
import java.util.List;
import java.util.function.Consumer;

public class CommentService {
  CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public void listenChange(String courseCode, Consumer<List<NewComment>> listener) {
    commentRepository.listenChange(courseCode, listener);
  }

  public List<NewComment> getAll(String courseCode) {
    return commentRepository.getAll(courseCode);
  }

  public List<NewComment> findAll(String courseCode, Query query) {
    return commentRepository.findAll(courseCode, query);
  }
}
