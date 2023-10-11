package com.example.couseplusplus.data.invertedindex;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Test;

public class StringInvertedIndexTest {
  @Test
  public void testAdd() {
    InvertedIndex<String, Integer> uut = new StringInvertedIndex<>();
    uut.add("1", 1);
    uut.add("1", 2);
  }

  @Test
  public void testGet() {
    InvertedIndex<String, Integer> uut = new StringInvertedIndex<>();

    assertTrue(uut.get("key").isEmpty());

    uut.add("key", 1);
    uut.add("key", 2);
    uut.add("key", 2);
    Set<Integer> result = uut.get("key");
    assertEquals(2, result.size());
    assertEquals(Set.of(1, 2), result);
  }

  @Test
  public void testCaseDoesNotMatter() {
    StringInvertedIndex<Integer> uut = new StringInvertedIndex<>();
    uut.add("KEY", 1);
    assertEquals(1, uut.get("key").size());
    assertEquals(1, uut.get("KEY").size());
  }
}
