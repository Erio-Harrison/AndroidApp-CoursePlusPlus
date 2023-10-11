package com.example.couseplusplus.service.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.QueryParseTreeCreator;
import com.example.couseplusplus.model.query.parser.ParseTree;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CommentService {
  CommentRepository commentRepository;
  QueryParseTreeCreator queryParseTreeCreator;

  public CommentService(
      CommentRepository commentRepository, QueryParseTreeCreator queryParseTreeCreator) {
    this.commentRepository = commentRepository;
    this.queryParseTreeCreator = queryParseTreeCreator;
  }

  public void listenChange(String courseCode, Consumer<List<Comment>> listener) {
    commentRepository.listenChange(courseCode, listener);
  }

  public List<Comment> getAll(String courseCode) {
    return commentRepository.getAll(courseCode);
  }

  public List<Comment> findAll(String courseCode, Query query) {
    ParseTree parseTree = queryParseTreeCreator.create(query);
    return commentRepository.findAll(courseCode, parseTree);
  }

  public List<Comment> sort(List<Comment> comments, boolean ascending, SortingAspect aspect) {
    Comparator<Comment> comparator = getComparator(aspect);
    return comments.stream()
        .sorted(ascending ? comparator : Collections.reverseOrder(comparator))
        .collect(Collectors.toList());
  }

  Comparator<Comment> getComparator(SortingAspect aspect) {
    switch (aspect) {
      case Helpfulness:
        return Comparator.comparing(Comment::helpfulness);
      case EnrolDate:
        return Comparator.comparing(Comment::enrolKey);
      case PostedDateTime:
        return Comparator.comparing(Comment::postedDateTime);
      default:
        throw new IllegalArgumentException(String.format("%s is not supported", aspect));
    }
  }
}
