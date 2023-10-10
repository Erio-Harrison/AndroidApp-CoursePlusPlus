package com.example.couseplusplus.data.avl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class NodeTest {
  @Test
  public void testInsert() {
    assertEquals(
        "Node{ value=1, left=null, right=Node{ value=2, left=null, right=null } }",
        new Node<>(1, 1).insert(2, 2).toString());

    assertThrows(IllegalArgumentException.class, () -> new Node<>(1, 1).insert(null, null));
    assertThrows(IllegalArgumentException.class, () -> new Node<>(1, 1).insert(1, null));
    assertThrows(IllegalArgumentException.class, () -> new Node<>(1, 1).insert(null, null));
  }

  @Test
  public void testSingleLeft() {
    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(1, 1).insert(2, 2).insert(3, 3).toString());

    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(3, 3).insert(2, 2).insert(1, 1).toString());
  }

  @Test
  public void testDoubleRotate() {
    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(1, 1).insert(3, 3).insert(2, 2).toString());

    assertEquals(
        "Node{ value=2, left=Node{ value=1, left=null, right=null }, right=Node{ value=3, left=null, right=null } }",
        new Node<>(3, 3).insert(1, 1).insert(2, 2).toString());
  }

  @Test
  public void testFind() {
    Node<Integer, Integer> node = new Node<>(1, 1).insert(2, 2).insert(3, 3);
    assertTrue(node.find(1).isPresent());
    assertTrue(node.find(2).isPresent());
    assertTrue(node.find(3).isPresent());
    assertTrue(node.find(4).isEmpty());

    assertThrows(IllegalArgumentException.class, () -> node.find(null));
    assertThrows(IllegalArgumentException.class, () -> node.find(null));
  }

  @Test
  public void testCollectMoreThan() {
    Node<Integer, Integer> node = new Node<>(1, 1).insert(2, 2).insert(3, 3);

    ArrayList<Integer> list = new ArrayList<>();
    node.collectMoreThan(1, list, false);
    assertEquals(2, list.size());
    assertEquals(Set.of(2, 3), new HashSet<>(list));

    ArrayList<Integer> list2 = new ArrayList<>();
    node.collectMoreThan(1, list2, true);
    assertEquals(3, list2.size());
    assertEquals(Set.of(1, 2, 3), new HashSet<>(list2));

    ArrayList<Integer> list3 = new ArrayList<>();
    node.collectMoreThan(3, list3, false);
    assertEquals(0, list3.size());
  }

  @Test
  public void testCollectLessThan() {
    Node<Integer, Integer> node = new Node<>(1, 1).insert(2, 2).insert(3, 3);

    ArrayList<Integer> list = new ArrayList<>();
    node.collectLessThan(3, list, false);
    assertEquals(2, list.size());
    assertEquals(Set.of(1, 2), new HashSet<>(list));

    ArrayList<Integer> list2 = new ArrayList<>();
    node.collectLessThan(3, list2, true);
    assertEquals(3, list2.size());
    assertEquals(Set.of(1, 2, 3), new HashSet<>(list2));

    ArrayList<Integer> list3 = new ArrayList<>();
    node.collectLessThan(1, list3, false);
    assertEquals(0, list3.size());
  }
}
