package com.example.couseplusplus.simulation;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationScheduler implements Subject {
  List<Schedule> schedules;
  List<Observer> observers;

  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  private int currentScheduleIndex = 0;

  public SimulationScheduler() {
    schedules = new ArrayList<>();
    observers = new ArrayList<>();

    register(new AddCommentHandler());
    register(new ReadCommentsHandler());
  }

  public void readSchedules(String filepath) {

    try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        ActionType actionType = ActionType.valueOf(values[0]); // Convert the action type string to an ActionType enum
        List<String> arguments = Arrays.asList(values).subList(1, values.length);
        schedules.add(new Schedule(actionType, arguments));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() {

    final Runnable task = () -> {
      if (currentScheduleIndex < schedules.size()) {
        Schedule currentSchedule = schedules.get(currentScheduleIndex);
        emit(currentSchedule.actionType, currentSchedule.arguments);
        currentScheduleIndex++;
      }
    };

    scheduler.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);
  }
  public void stop() {
    scheduler.shutdownNow();
  }


  @Override
  public void register(Observer observer) {observers.add(observer);}

  @Override
  public void remove(Observer observer) {observers.remove(observer);}

  @Override
  public List<Observer> observers() {
    return observers;
  }
}
