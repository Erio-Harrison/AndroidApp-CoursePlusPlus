package com.example.couseplusplus.simulation;

import android.util.Log;

import com.example.couseplusplus.model.comment.Comment;

import java.util.List;

public class AddCommentHandler implements Observer {
  @Override
  public void on(ActionType actionType, List<String> arguments) {
    if (actionType != ActionType.AddComment) return;
    // Assuming arguments are in the order as in the CSV
    Comment comment = new Comment(
            "",
            arguments.get(0),
            Integer.parseInt(arguments.get(1)),
            Integer.parseInt(arguments.get(2)),
            arguments.get(3),
            Integer.parseInt(arguments.get(4)),
            arguments.get(5)
    );

    Log.i("Simulation", "Added Comment: " + comment.getText() + " for Course: " + comment.getCourseCode());
  }
}
