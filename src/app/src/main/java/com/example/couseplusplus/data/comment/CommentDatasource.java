package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.query.Query;
import java.util.List;

public class CommentDatasource implements CommentRepository {
  @Override
  public List<Comment> getAll(String courseCode) {
    return null;
  }

  @Override
  public List<Comment> findAll(String courseCode, Query query) {
    return null;
  }
}
