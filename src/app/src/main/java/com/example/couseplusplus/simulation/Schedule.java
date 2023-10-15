package com.example.couseplusplus.simulation;

import java.util.List;

public class Schedule {
  ActionType actionType;
  List<String> arguments;

  public Schedule(ActionType actionType, List<String> arguments) {
    this.actionType = actionType;
    this.arguments = arguments;
  }
}
