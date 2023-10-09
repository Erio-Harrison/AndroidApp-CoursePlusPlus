package com.example.couseplusplus.service.comment;

import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.Query;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

  public List<NewComment> sort(List<NewComment> comments, boolean ascending, SortingAspect aspect) {
    Comparator<NewComment> comparator = getComparator(aspect);
    return comments.stream()
        .sorted(ascending ? comparator : Collections.reverseOrder(comparator))
        .collect(Collectors.toList());
  }

  Comparator<NewComment> getComparator(SortingAspect aspect) {
    switch (aspect) {
      case Helpfulness:
        return Comparator.comparing(NewComment::helpfulness);
      case EnrolDate:
        return Comparator.comparing(NewComment::enrolKey);
      case PostedDateTime:
        return Comparator.comparing(NewComment::postedDateTime);
      default:
        throw new IllegalArgumentException(String.format("%s is not supported", aspect));
    }
  }
}
