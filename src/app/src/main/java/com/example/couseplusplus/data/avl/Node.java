package com.example.couseplusplus.data.avl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Node<T> {
  T value;
  Node<T> left;
  Node<T> right;

  public Node(T value) {
    this.value = value;
    left = null;
    right = null;
  }

  public Node(T value, Node<T> left, Node<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public Node<T> insert(T value, Comparator<T> comparator) {
    requireNonNull(value, comparator);

    int difference = comparator.compare(value, this.value);
    if (difference == 0) return new Node<>(this.value, left, right);
    Node<T> node;
    if (difference < 0) {
      Node<T> newLeft = Objects.isNull(left) ? new Node<>(value) : left.insert(value, comparator);
      node = new Node<>(this.value, newLeft, right);
    } else {
      Node<T> newRight =
          Objects.isNull(right) ? new Node<>(value) : right.insert(value, comparator);
      node = new Node<>(this.value, left, newRight);
    }

    int balancingFactor = node.getBalancingFactor();
    if (balancingFactor > 1) {
      if (comparator.compare(value, node.left.value) > 0) {
        node.left = node.left.rotateLeft();
        return node.rotateRight();
      }
      if (comparator.compare(value, node.left.value) < 0) {
        return node.rotateRight();
      }
    }
    if (balancingFactor < -1) {
      if (comparator.compare(value, node.right.value) < 0) {
        node.right = node.right.rotateRight();
        return node.rotateLeft();
      }
      if (comparator.compare(value, node.right.value) > 0) {
        return node.rotateLeft();
      }
    }
    return node;
  }

  public Optional<Node<T>> find(T value, Comparator<T> comparator) {
    requireNonNull(value, comparator);

    if (comparator.compare(value, this.value) < 0) {
      if (Objects.isNull(left)) return Optional.empty();
      return left.find(value, comparator);
    }
    if (comparator.compare(value, this.value) > 0) {
      if (Objects.isNull(right)) return Optional.empty();
      return right.find(value, comparator);
    }
    return Optional.of(this);
  }

  public void collectMoreThan(
      T value, Comparator<T> comparator, List<T> list, boolean includesEqual) {
    requireNonNull(value, comparator, list);

    if (comparator.compare(value, this.value) < 0) {
      list.add(this.value);
      if (Objects.nonNull(left)) left.collectMoreThan(value, comparator, list, includesEqual);
      if (Objects.nonNull(right)) right.collectMoreThan(value, comparator, list, includesEqual);
    }
    if (comparator.compare(value, this.value) > 0 && Objects.nonNull(right)) {
      right.collectMoreThan(value, comparator, list, includesEqual);
    }
    if (comparator.compare(value, this.value) == 0) {
      if (includesEqual) list.add(this.value);
      if (Objects.nonNull(right)) right.collectMoreThan(value, comparator, list, includesEqual);
    }
  }

  public void collectLessThan(
      T value, Comparator<T> comparator, List<T> list, boolean includesEqual) {
    requireNonNull(value, comparator, list);

    if (comparator.compare(value, this.value) > 0) {
      list.add(this.value);
      if (Objects.nonNull(left)) left.collectLessThan(value, comparator, list, includesEqual);
      if (Objects.nonNull(right)) right.collectLessThan(value, comparator, list, includesEqual);
    }
    if (comparator.compare(value, this.value) < 0 && Objects.nonNull(left)) {
      left.collectLessThan(value, comparator, list, includesEqual);
    }
    if (comparator.compare(value, this.value) == 0) {
      if (includesEqual) list.add(this.value);
      if (Objects.nonNull(left)) left.collectLessThan(value, comparator, list, includesEqual);
    }
  }

  void requireNonNull(Object... objects) {
    for (Object object : objects) {
      if (Objects.nonNull(object)) continue;
      throw new IllegalArgumentException(String.format("%s is null", object));
    }
  }

  public T value() {
    return value;
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

  Node<T> rotateLeft() {
    Node<T> newRoot = right;
    Node<T> newLeftRight = newRoot.left;
    newRoot.left = new Node<>(value, left, right);
    newRoot.left.right = newLeftRight;
    return newRoot;
  }

  Node<T> rotateRight() {
    Node<T> newRoot = left;
    Node<T> newRightLeft = newRoot.right;
    newRoot.right = new Node<>(value, left, right);
    newRoot.right.left = newRightLeft;
    return newRoot;
  }

  @Override
  public String toString() {
    return String.format("Node{ value=%s, left=%s, right=%s }", value, left, right);
  }
}
