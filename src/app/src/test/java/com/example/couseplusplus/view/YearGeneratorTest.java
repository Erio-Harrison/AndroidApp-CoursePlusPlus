package com.example.couseplusplus.view;

import static org.junit.Assert.assertEquals;

import com.example.couseplusplus.view.addcomment.YearGenerator;
import java.time.Year;
import java.util.List;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class YearGeneratorTest {
  @Test
  public void testUntilThisYear() {
    List<String> result = YearGenerator.generateAsString(Year.of(2021), Year.of(2023));
    assertEquals(List.of("2021", "2022", "2023"), result);
  }
}
