package com.example.couseplusplus.simulation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class SimulationSchedulerTest {

  private SimulationScheduler simulationScheduler;
  private String simulatedCsv =
      "addComment,commentSimulation1,ACST3001,2021,1,This course is great!,3,15-07-2023 14:25:45\n" +
              "addComment,commentSimulation2,ACST3032,2022,1,The professor is helpful!,4,12-07-2023 13:25:45\n" +
              "addComment,commentSimulation3,COMP2100,2023,2,Loved the course materials!,5,20-07-2023 10:15:25\n" +
              "addComment,commentSimulation4,ACST3004,2022,2,Assignments were too tough!,2,22-07-2023 16:40:30\n" +
              "addComment,commentSimulation5,COMP2400,2022,2,Excellent course for beginners!,4,30-07-2023 12:25:55\n" +
              "addComment,commentSimulation6,COMP1100,2022,1,Too much theory!,3,18-07-2023 09:15:45\n" +
              "addComment,commentSimulation7,COMP2100,2023,1,Interactive and engaging sessions!,4,10-07-2023 15:05:25\n" +
              "addHelpfulness,ACST3001,comment3,3\n" +
              "addHelpfulness,ACST3002,comment4,6\n" +
              "addHelpfulness,ACST3032,commentSimulation2,5\n" +
              "addHelpfulness,COMP2100,commentSimulation3,4\n" +
              "addHelpfulness,ACST3004,commentSimulation4,2\n" +
              "addHelpfulness,COMP2400,commentSimulation5,5\n" +
              "addHelpfulness,COMP1100,commentSimulation6,4\n" +
              "addHelpfulness,COMP2100,commentSimulation7,5";

  @Before
  public void setUp() throws Exception {
    Context mockContext = mock(Context.class);
    AssetManager mockAssets = mock(AssetManager.class);

    when(mockContext.getAssets()).thenReturn(mockAssets);
    when(mockAssets.open("action_schedule.csv"))
        .thenReturn(new ByteArrayInputStream(simulatedCsv.getBytes()));

    simulationScheduler = new SimulationScheduler(mockContext);
  }

  @Test
  public void testReadingSchedules() {
    simulationScheduler.readSchedules();
    assertEquals(15, simulationScheduler.schedules.size());

    Schedule firstSchedule = simulationScheduler.schedules.get(0);
    assertEquals(ActionType.addComment, firstSchedule.actionType);
    assertEquals(
        Arrays.asList(
            "commentSimulation1",
            "ACST3001",
            "2021",
            "1",
            "This course is great!",
            "3",
            "15-07-2023 14:25:45"),
        firstSchedule.arguments);

    Schedule secondSchedule = simulationScheduler.schedules.get(1);
    assertEquals(ActionType.addComment, secondSchedule.actionType);
    assertEquals(
        Arrays.asList(
            "commentSimulation2",
            "ACST3032",
            "2022",
            "1",
            "The professor is helpful!",
            "4",
            "12-07-2023 13:25:45"),
        secondSchedule.arguments);
  }
}
