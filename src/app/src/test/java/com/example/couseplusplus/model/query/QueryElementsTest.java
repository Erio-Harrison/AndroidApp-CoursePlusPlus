package com.example.couseplusplus.model.query;

import static org.junit.Assert.*;

import com.example.couseplusplus.view.comment.query.Pair;
import com.example.couseplusplus.view.comment.query.QueryElements;
import java.time.LocalDateTime;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class QueryElementsTest {
  @Test
  public void testToQuery() {
    QueryElements uut =
        new QueryElements(
            new Pair<>(1, 2),
            new Pair<>(2000, 2001),
            new Pair<>(1, 1),
            new Pair<>(LocalDateTime.of(2000, 1, 1, 0, 0), LocalDateTime.of(2001, 1, 1, 0, 0)),
            "hello");
    Query result = uut.toQuery();
    assertEquals(
        "helpful>=1&helpful<=2&enrol>=2000S1&enrol<=2001S1&posted>=2000-01-01T00:00:00&posted<=2001-01-01T00:00:00&text~\"hello\"",
        result.value);
  }

  @Test
  public void testSmallQuery() {
    QueryElements uut =
        new QueryElements(
            new Pair<>(null, 2),
            new Pair<>(null, null),
            new Pair<>(null, null),
            new Pair<>(null, null),
            null);
    Query result = uut.toQuery();
    assertEquals("helpful<=2", result.value);
  }
}
