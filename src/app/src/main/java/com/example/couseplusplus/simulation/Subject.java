package com.example.couseplusplus.simulation;

import java.util.List;

/** Observer pattern! Responsible for notifying observers the event. */
public interface Subject {
  default void emit(ActionType actionType, List<String> arguments) {
    observers().forEach(observer -> observer.on(actionType, arguments));
  }

  void register(Observer observer);

  List<Observer> observers();
}
