package com.example.couseplusplus.simulation;

import android.util.Log;
import java.util.List;

public class AddCommentHandler implements Observer {
  @Override
  public void on(ActionType actionType, List<String> arguments) {
    if (actionType != ActionType.AddComment) return;
    Log.i(String.format("%s#on", this), "added a comment!");
  }
}
