package com.example.couseplusplus.data.avl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Node<K extends Comparable<K>, V> {
  K key;
  Node<K, V> left;
  Node<K, V> right;
  List<V> values;

  public Node(K key, V value) {
    this.key = key;
    left = null;
    right = null;
    values = new ArrayList<>();
    values.add(value);
  }

  public Node(K key, Node<K, V> left, Node<K, V> right, List<V> values) {
    this.key = key;
    this.left = left;
    this.right = right;
    this.values = values;
  }

  public Node<K, V> insert(K key, V value) {
    requireNonNull(key, value);

    int difference = key.compareTo(this.key);
    if (difference == 0) {
      values.add(value);
      return new Node<>(this.key, left, right, values);
    }
    Node<K, V> node;
    if (difference < 0) {
      Node<K, V> newLeft = Objects.isNull(left) ? new Node<>(key, value) : left.insert(key, value);
      node = new Node<>(this.key, newLeft, right, values);
    } else {
      Node<K, V> newRight =
          Objects.isNull(right) ? new Node<>(key, value) : right.insert(key, value);
      node = new Node<>(this.key, left, newRight, values);
    }

    int balancingFactor = node.getBalancingFactor();
    if (balancingFactor > 1) {
      if (key.compareTo(node.left.key) > 0) {
        node.left = node.left.rotateLeft();
        return node.rotateRight();
      }
      if (key.compareTo(node.left.key) < 0) {
        return node.rotateRight();
      }
    }
    if (balancingFactor < -1) {
      if (key.compareTo(node.right.key) < 0) {
        node.right = node.right.rotateRight();
        return node.rotateLeft();
      }
      if (key.compareTo(node.right.key) > 0) {
        return node.rotateLeft();
      }
    }
    return node;
  }

  public Optional<Node<K, V>> find(K key) {
    requireNonNull(key);

    if (key.compareTo(this.key) < 0) {
      if (Objects.isNull(left)) return Optional.empty();
      return left.find(key);
    }
    if (key.compareTo(this.key) > 0) {
      if (Objects.isNull(right)) return Optional.empty();
      return right.find(key);
    }
    return Optional.of(this);
  }

  public void collectMoreThan(K key, List<V> list, boolean includesEqual) {
    requireNonNull(key, list);

    if (key.compareTo(this.key) < 0) {
      list.addAll(values);
      if (Objects.nonNull(left)) left.collectMoreThan(key, list, includesEqual);
      if (Objects.nonNull(right)) right.collectMoreThan(key, list, includesEqual);
    }
    if (key.compareTo(this.key) > 0 && Objects.nonNull(right)) {
      right.collectMoreThan(key, list, includesEqual);
    }
    if (key.compareTo(this.key) == 0) {
      if (includesEqual) list.addAll(values);
      if (Objects.nonNull(right)) right.collectMoreThan(key, list, includesEqual);
    }
  }

  public void collectLessThan(K key, List<V> list, boolean includesEqual) {
    requireNonNull(key, list);

    if (key.compareTo(this.key) > 0) {
      list.addAll(values);
      if (Objects.nonNull(left)) left.collectLessThan(key, list, includesEqual);
      if (Objects.nonNull(right)) right.collectLessThan(key, list, includesEqual);
    }
    if (key.compareTo(this.key) < 0 && Objects.nonNull(left)) {
      left.collectLessThan(key, list, includesEqual);
    }
    if (key.compareTo(this.key) == 0) {
      if (includesEqual) list.addAll(values);
      if (Objects.nonNull(left)) left.collectLessThan(key, list, includesEqual);
    }
  }

  public void collectMoreThan(
      K key, List<V> list, boolean includesEqual, Comparator<K> comparator) {
    requireNonNull(key, list, comparator);

    if (comparator.compare(key, this.key) < 0) {
      list.addAll(values);
      if (Objects.nonNull(left)) left.collectMoreThan(key, list, includesEqual, comparator);
      if (Objects.nonNull(right)) right.collectMoreThan(key, list, includesEqual, comparator);
    }
    if (comparator.compare(key, this.key) > 0 && Objects.nonNull(right)) {
      right.collectMoreThan(key, list, includesEqual, comparator);
    }
    if (comparator.compare(key, this.key) == 0) {
      if (includesEqual) list.addAll(values);
      if (Objects.nonNull(right)) right.collectMoreThan(key, list, includesEqual, comparator);
    }
  }

  void requireNonNull(Object... objects) {
    for (Object object : objects) {
      if (Objects.nonNull(object)) continue;
      throw new IllegalArgumentException(String.format("%s is null", object));
    }
  }

  public V value() {
    return values.get(0);
  }

  public List<V> values() {
    return values;
  }

  int getHeight() {
    int leftHeight = Objects.isNull(left) ? 1 : left.getHeight() + 1;
    int rightHeight = Objects.isNull(right) ? 1 : right.getHeight() + 1;
    return Math.max(leftHeight, rightHeight);
  }

  int getBalancingFactor() {
    return (Objects.isNull(left) ? 0 : left.getHeight())
        - (Objects.isNull(right) ? 0 : right.getHeight());
  }

  Node<K, V> rotateLeft() {
    Node<K, V> newRoot = right;
    Node<K, V> newLeftRight = newRoot.left;
    newRoot.left = new Node<>(key, left, right, values);
    newRoot.left.right = newLeftRight;
    return newRoot;
  }

  Node<K, V> rotateRight() {
    Node<K, V> newRoot = left;
    Node<K, V> newRightLeft = newRoot.right;
    newRoot.right = new Node<>(key, left, right, values);
    newRoot.right.left = newRightLeft;
    return newRoot;
  }

  @Override
  public String toString() {
    return String.format("Node{ value=%s, left=%s, right=%s }", key, left, right);
  }
}
