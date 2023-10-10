package com.example.couseplusplus.simulation;

import java.util.List;


/** Observer pattern! Responsible for reacting to the notification from {@link Subject} */
public interface Observer {
  void on(ActionType actionType, List<String> arguments);
}
