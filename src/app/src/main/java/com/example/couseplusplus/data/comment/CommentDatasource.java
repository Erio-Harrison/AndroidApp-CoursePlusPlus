package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.Query;
import java.util.List;

public class CommentDatasource implements CommentRepository {
  @Override
  public List<NewComment> getAll(String courseCode) {
    return null;
  }

  @Override
  public List<NewComment> findAll(String courseCode, Query query) {
    return null;
  }
}
