package com.example.couseplusplus.model.comment;

import com.example.couseplusplus.model.query.Query;
import java.util.List;

public interface CommentRepository {
  List<NewComment> getAll(String courseCode);

  List<NewComment> findAll(String courseCode, Query query);
}
