package com.example.couseplusplus.simulation;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationScheduler implements Subject {
  List<Schedule> schedules;
  List<Observer> observers;
  private final Context context;

  private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
  private int currentScheduleIndex = 0;

  public SimulationScheduler(Context context) {
    this.context = context;
    schedules = new ArrayList<>();
    observers = new ArrayList<>();

    register(new AddCommentHandler());
    register(new AddHelpfulnessHandler());
  }

  public void readSchedules() {

    try (BufferedReader br =
        new BufferedReader(
            new InputStreamReader(
                context.getAssets().open("action_schedule.csv"), StandardCharsets.UTF_8)); ) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        ActionType actionType =
            ActionType.valueOf(values[0]); // Convert the action type string to an ActionType enum
        List<String> arguments = Arrays.asList(values).subList(1, values.length);
        schedules.add(new Schedule(actionType, arguments));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void start() {

    final Runnable task =
        () -> {
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
  public void register(Observer observer) {
    observers.add(observer);
  }

  @Override
  public List<Observer> observers() {
    return observers;
  }
}
