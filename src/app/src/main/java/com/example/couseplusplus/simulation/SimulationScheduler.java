package com.example.couseplusplus.simulation;

import java.util.ArrayList;
import java.util.List;

// TODO change/remove however you want to suite your implementation
public class SimulationScheduler implements Subject {
  List<Schedule> schedules;
  List<Observer> observers;

  public SimulationScheduler() {
    schedules = new ArrayList<>();
    observers = new ArrayList<>();
  }

  public void readSchedules(String filepath) {
    // TODO read file contents (e.g. assets/action_schedule.csv), parse each line to create Schedule
    //        object, add it to schedules
  }

  public void start() {
    // TODO we could periodically call each scheduled action in schedules in every T time or any
    //     other factors you can think of
  }

  @Override
  public void register(Observer observer) {}

  @Override
  public void remove(Observer observer) {}

  @Override
  public List<Observer> observers() {
    return null;
  }
}
