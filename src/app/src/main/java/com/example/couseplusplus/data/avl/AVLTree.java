package com.example.couseplusplus.data.avl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AVLTree<T> {
  Node<T> root;
  Comparator<T> comparator;

  public AVLTree(Comparator<T> comparator) {
    root = null;
    this.comparator = comparator;
  }

  public void insert(T value) {
    if (Objects.isNull(root)) {
      root = new Node<>(value);
      return;
    }
    root = root.insert(value, comparator);
  }

  public Optional<T> find(T value) {
    if (Objects.isNull(root)) return Optional.empty();
    Optional<Node<T>> optionalNode = root.find(value, comparator);
    return optionalNode.map(Node::value);
  }

  public List<T> collectMoreThan(T value) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<T> result = new ArrayList<>();
    root.collectMoreThan(value, comparator, result, false);
    return result;
  }

  public List<T> collectEqualOrMoreThan(T value) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<T> result = new ArrayList<>();
    root.collectMoreThan(value, comparator, result, true);
    return result;
  }

  public List<T> collectLessThan(T value) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<T> result = new ArrayList<>();
    root.collectLessThan(value, comparator, result, false);
    return result;
  }

  public List<T> collectEqualOrLessThan(T value) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<T> result = new ArrayList<>();
    root.collectLessThan(value, comparator, result, true);
    return result;
  }

  @Override
  public String toString() {
    return String.format("AVLTree{ root=%s }", root);
  }
}
