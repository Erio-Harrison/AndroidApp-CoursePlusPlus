package com.example.couseplusplus.simulation;

import java.util.List;

// TODO change however you want to suite your implementation needs

/** Observer pattern! Responsible for notifying observers the event. */
public interface Subject {
  default void emit(ActionType actionType, List<String> arguments) {
    observers().forEach(observer -> observer.on(actionType, arguments));
  }

  void register(Observer observer);

  void remove(Observer observer);

  List<Observer> observers();
}
