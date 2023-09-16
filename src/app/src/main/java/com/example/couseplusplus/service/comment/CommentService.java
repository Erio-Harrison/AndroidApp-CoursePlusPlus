package com.example.couseplusplus.service.comment;

import com.example.couseplusplus.model.comment.CommentRepository;

public class CommentService {
  CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }
}
