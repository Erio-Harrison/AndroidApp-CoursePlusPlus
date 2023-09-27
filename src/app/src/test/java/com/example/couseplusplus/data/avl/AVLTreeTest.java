package com.example.couseplusplus.data.avl;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.Test;

public class AVLTreeTest {
  @Test
  public void testInsert() {
    AVLTree<Integer, Integer> uut = new AVLTree<>();
    uut.insert(1, 1);
    uut.insert(5, 5);
    uut.insert(10, 10);
    assertEquals(
        "AVLTree{ root=Node{ value=5, left=Node{ value=1, left=null, right=null }, right=Node{ value=10, left=null, right=null } } }",
        uut.toString());
  }

  @Test
  public void testFind() {
    AVLTree<Integer, Integer> uut = new AVLTree<>();
    uut.insert(1, 1);

    Optional<Integer> result = uut.find(1);
    assertTrue(result.isPresent());
    assertEquals(1, (int) result.get());
  }

  @Test
  public void testFindMoreThan() {
    AVLTree<Integer, Integer> uut = new AVLTree<>();
    uut.insert(1, 1);
    uut.insert(2, 2);
    uut.insert(3, 3);

    List<Integer> result = uut.collectMoreThan(1);
    assertEquals(2, result.size());
    assertEquals(Set.of(2, 3), new HashSet<>(result));
  }

  @Test
  public void testFindEqualOrMoreThan() {
    AVLTree<Integer, Integer> uut = new AVLTree<>();
    uut.insert(1, 1);
    uut.insert(2, 2);
    uut.insert(3, 3);

    List<Integer> result = uut.collectEqualOrMoreThan(1);
    assertEquals(3, result.size());
    assertEquals(Set.of(1, 2, 3), new HashSet<>(result));
  }

  @Test
  public void testFindLessThan() {
    AVLTree<Integer, Integer> uut = new AVLTree<>();
    uut.insert(1, 1);
    uut.insert(2, 2);
    uut.insert(3, 3);

    List<Integer> result = uut.collectLessThan(3);
    assertEquals(2, result.size());
    assertEquals(Set.of(1, 2), new HashSet<>(result));
  }

  @Test
  public void testFindEqualOrLessThan() {
    AVLTree<Integer, Integer> uut = new AVLTree<>();
    uut.insert(1, 1);
    uut.insert(2, 2);
    uut.insert(3, 3);

    List<Integer> result = uut.collectEqualOrLessThan(3);
    assertEquals(3, result.size());
    assertEquals(Set.of(1, 2, 3), new HashSet<>(result));
  }

  private static class Data {
    int integerValue;
    String stringValue;

    public Data(int integerValue, String stringValue) {
      this.integerValue = integerValue;
      this.stringValue = stringValue;
    }

    @Override
    public String toString() {
      return "[" + integerValue + ", '" + stringValue + '\'' + ']';
    }
  }

  @Test
  public void testPojoAVLTree() {
    AVLTree<Integer, Data> uut = new AVLTree<>();
    uut.insert(1, new Data(1, "one"));
    uut.insert(2, new Data(2, "two"));
    uut.insert(3, new Data(3, "three"));
    assertEquals(
        "AVLTree{ root=Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } } }",
        uut.toString());

    AVLTree<Integer, Data> uut2 = new AVLTree<>();
    uut2.insert(1, new Data(1, "one"));
    uut2.insert(2, new Data(2, "two"));
    uut2.insert(3, new Data(3, "three"));
    assertEquals(
        "AVLTree{ root=Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } } }",
        uut2.toString());

    AVLTree<Integer, Data> uut3 = new AVLTree<>();
    uut3.insert(1, new Data(1, "one"));
    uut3.insert(1, new Data(1, "another one"));
    assertEquals(2, uut3.root.values.size());
  }
}
