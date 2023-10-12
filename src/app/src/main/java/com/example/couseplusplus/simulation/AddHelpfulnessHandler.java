package com.example.couseplusplus.simulation;

import com.example.couseplusplus.data.comment.CommentDatasource;
import com.example.couseplusplus.model.comment.CommentRepository;
import java.util.List;

public class AddHelpfulnessHandler implements Observer {
  private final CommentRepository commentRepository = new CommentDatasource();

  @Override
  public void on(ActionType actionType, List<String> arguments) {
    if (actionType != ActionType.addHelpfulness) return;
    commentRepository.addHelpfulness(
        arguments.get(0), arguments.get(1), Integer.parseInt(arguments.get(2)));
  }
}
