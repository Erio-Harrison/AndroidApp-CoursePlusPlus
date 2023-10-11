package com.example.couseplusplus.data.invertedindex;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Test;

public class InvertedIndexTest {
  @Test
  public void testAdd() {
    InvertedIndex<Integer, Integer> uut = new InvertedIndex<>();
    uut.add(1, 1);
    uut.add(1, 2);
  }

  @Test
  public void testGet() {
    InvertedIndex<String, Integer> uut = new InvertedIndex<>();

    assertTrue(uut.get("key").isEmpty());

    uut.add("key", 1);
    uut.add("key", 2);
    uut.add("key", 2);
    Set<Integer> result = uut.get("key");
    assertEquals(2, result.size());
    assertEquals(Set.of(1, 2), result);
  }
}
