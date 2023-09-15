package com.example.couseplusplus.data.comment.finder;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.ParseTree;
import java.util.List;

public interface CommentProcessor {
  List<Comment> findAll(ParseTree parseTree);
}
