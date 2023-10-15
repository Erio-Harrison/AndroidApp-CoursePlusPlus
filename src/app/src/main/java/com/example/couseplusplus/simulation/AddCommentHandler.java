package com.example.couseplusplus.simulation;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.CommentRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AddCommentHandler implements Observer {

  private CommentRepository commentRepository;

  public AddCommentHandler(CommentRepository commentRepository){
    this.commentRepository=commentRepository;
  }
  @Override
  public void on(ActionType actionType, List<String> arguments) {
    if (actionType != ActionType.addComment) return;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    LocalDateTime time = LocalDateTime.parse(arguments.get(6), formatter);

    // Assuming arguments are in the order as in the CSV
    Comment comment =
        new Comment(
            arguments.get(0),
            arguments.get(1),
            Integer.parseInt(arguments.get(2)),
            Integer.parseInt(arguments.get(3)),
            arguments.get(4),
            Integer.parseInt(arguments.get(5)),
            time);
    commentRepository.addComment(comment.courseCode(), comment);
  }
}
