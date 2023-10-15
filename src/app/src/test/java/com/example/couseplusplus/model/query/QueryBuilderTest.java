package com.example.couseplusplus.model.query;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import org.junit.Test;

public class QueryBuilderTest {
  @Test
  public void testBuild() {
    assertTrue(new QueryBuilder().build().isBlank());
  }

  @Test
  public void testValue() {
    Query result = new QueryBuilder().value("2020S1").build();
    assertEquals("2020S1", result.value);
  }

  @Test
  public void testTextValue() {
    Query result = new QueryBuilder().textValue("a").build();
    assertEquals("\"a\"", result.value);
  }

  @Test
  public void testLocalDateTimeValue() {
    Query result =
        new QueryBuilder().localDateTimeValue(LocalDateTime.of(2000, 1, 1, 0, 0)).build();
    assertEquals("2000-01-01T00:00:00", result.value);
  }

  @Test
  public void testHelpfulEqualsOrMore10() {
    Query result = new QueryBuilder().helpful().equalsOrMore().value(10).build();
    assertEquals("helpful>=10", result.value);
  }

  @Test
  public void testHelpfulEqualsOrMore10AndTestLikeHello() {
    Query result =
        new QueryBuilder()
            .helpful()
            .equalsOrMore()
            .value(10)
            .and()
            .text()
            .like()
            .textValue("hello")
            .build();
    assertEquals("helpful>=10&text~\"hello\"", result.value);
  }

  @Test
  public void testIfThen() {
    Query result =
        new QueryBuilder()
            .ifThen(() -> true, builder -> builder.value(true))
            .ifThen(() -> false, builder -> builder.value(false))
            .build();
    assertEquals("true", result.value);
  }
}
