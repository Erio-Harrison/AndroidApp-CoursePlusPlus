package com.example.couseplusplus.data.avl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class NodeTest {
  @Test
  public void testInsert() {
    assertEquals(
        "Node{ value=1, left=null, right=Node{ value=2, left=null, right=null } }",
        new Node<>(1).insert(2, Comparator.comparingInt(i -> i)).toString());

    assertThrows(IllegalArgumentException.class, () -> new Node<>(1).insert(null, null));
    assertThrows(IllegalArgumentException.class, () -> new Node<>(1).insert(1, null));
    assertThrows(
        IllegalArgumentException.class,
        () -> new Node<>(1).insert(null, Comparator.comparingInt(i -> i)));
  }

  @Test
  public void testSingleLeft() {
    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(1)
            .insert(2, Comparator.comparingInt(i -> i))
            .insert(3, Comparator.comparingInt(i -> i))
            .toString());

    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(3)
            .insert(2, Comparator.comparingInt(i -> i))
            .insert(1, Comparator.comparingInt(i -> i))
            .toString());
  }

  @Test
  public void testDoubleRotate() {
    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(1)
            .insert(3, Comparator.comparingInt(i -> i))
            .insert(2, Comparator.comparingInt(i -> i))
            .toString());

    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(3)
            .insert(1, Comparator.comparingInt(i -> i))
            .insert(2, Comparator.comparingInt(i -> i))
            .toString());
  }

  @Test
  public void testFind() {
    Node<Integer> node =
        new Node<>(1)
            .insert(2, Comparator.comparingInt(i -> i))
            .insert(3, Comparator.comparingInt(i -> i));
    assertTrue(node.find(1, Comparator.comparingInt(i -> i)).isPresent());
    assertTrue(node.find(2, Comparator.comparingInt(i -> i)).isPresent());
    assertTrue(node.find(3, Comparator.comparingInt(i -> i)).isPresent());
    assertTrue(node.find(4, Comparator.comparingInt(i -> i)).isEmpty());

    assertThrows(IllegalArgumentException.class, () -> node.find(null, null));
    assertThrows(IllegalArgumentException.class, () -> node.find(1, null));
    assertThrows(
        IllegalArgumentException.class, () -> node.find(null, Comparator.comparingInt(i -> i)));
  }

  @Test
  public void testCollectMoreThan() {
    Node<Integer> node =
        new Node<>(1)
            .insert(2, Comparator.comparingInt(i -> i))
            .insert(3, Comparator.comparingInt(i -> i));

    ArrayList<Integer> list = new ArrayList<>();
    node.collectMoreThan(1, Comparator.comparingInt(i -> i), list, false);
    assertEquals(2, list.size());
    assertEquals(Set.of(2, 3), new HashSet<>(list));

    ArrayList<Integer> list2 = new ArrayList<>();
    node.collectMoreThan(1, Comparator.comparingInt(i -> i), list2, true);
    assertEquals(3, list2.size());
    assertEquals(Set.of(1, 2, 3), new HashSet<>(list2));

    ArrayList<Integer> list3 = new ArrayList<>();
    node.collectMoreThan(3, Comparator.comparingInt(i -> i), list3, false);
    assertEquals(0, list3.size());
  }

  @Test
  public void testCollectLessThan() {
    Node<Integer> node =
        new Node<>(1)
            .insert(2, Comparator.comparingInt(i -> i))
            .insert(3, Comparator.comparingInt(i -> i));

    ArrayList<Integer> list = new ArrayList<>();
    node.collectLessThan(3, Comparator.comparingInt(i -> i), list, false);
    assertEquals(2, list.size());
    assertEquals(Set.of(1, 2), new HashSet<>(list));

    ArrayList<Integer> list2 = new ArrayList<>();
    node.collectLessThan(3, Comparator.comparingInt(i -> i), list2, true);
    assertEquals(3, list2.size());
    assertEquals(Set.of(1, 2, 3), new HashSet<>(list2));

    ArrayList<Integer> list3 = new ArrayList<>();
    node.collectLessThan(1, Comparator.comparingInt(i -> i), list3, false);
    assertEquals(0, list3.size());
  }
}
