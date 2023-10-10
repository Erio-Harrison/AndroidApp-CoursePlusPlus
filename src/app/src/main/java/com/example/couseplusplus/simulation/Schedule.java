package com.example.couseplusplus.simulation;

import java.util.List;

// TODO change/remove however you want to suite your implementation
public class Schedule {
  ActionType actionType;
  List<String> arguments;

  public Schedule(ActionType actionType, List<String> arguments) {
    this.actionType = actionType;
    this.arguments = arguments;
  }
}
