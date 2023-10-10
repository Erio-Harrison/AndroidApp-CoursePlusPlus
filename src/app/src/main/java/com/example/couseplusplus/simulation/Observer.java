package com.example.couseplusplus.simulation;

import java.util.List;

// TODO change however you want to suite your implementation needs

/** Observer pattern! Responsible for reacting to the notification from {@link Subject} */
public interface Observer {
  void on(ActionType actionType, List<String> arguments);
}
