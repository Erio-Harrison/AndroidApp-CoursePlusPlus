package com.example.couseplusplus.model.comment;

import com.example.couseplusplus.model.query.Query;
import java.util.List;

public interface CommentRepository {
  List<Comment> getAll(String courseCode);

  List<Comment> findAll(String courseCode, Query query);
}
