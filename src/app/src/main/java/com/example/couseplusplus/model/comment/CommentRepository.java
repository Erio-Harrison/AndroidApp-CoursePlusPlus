package com.example.couseplusplus.model.comment;

import com.example.couseplusplus.model.query.Query;
import java.util.List;
import java.util.function.Consumer;

public interface CommentRepository {

  void listenChange(String courseCode, Consumer<List<NewComment>> listener);

  List<NewComment> getAll(String courseCode);

  List<NewComment> findAll(String courseCode, Query query);
}
