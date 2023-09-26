package com.example.couseplusplus.data.avl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AVLTree<K extends Comparable<K>, V> {
  Node<K, V> root;

  public AVLTree() {
    root = null;
  }

  public void insert(K key, V value) {
    if (Objects.isNull(root)) {
      root = new Node<>(key, value);
      return;
    }
    root = root.insert(key, value);
  }

  public Optional<V> find(K key) {
    if (Objects.isNull(root)) return Optional.empty();
    Optional<Node<K, V>> optionalNode = root.find(key);
    return optionalNode.map(Node::value);
  }

  public List<V> findAll(K key) {
    if (Objects.isNull(root)) return List.of();
    Optional<Node<K, V>> optionalNode = root.find(key);
    if (optionalNode.isEmpty()) return List.of();
    return optionalNode.get().values();
  }

  public List<V> collectMoreThan(K key) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<V> result = new ArrayList<>();
    root.collectMoreThan(key, result, false);
    return result;
  }

  public List<V> collectEqualOrMoreThan(K key, Comparator<K> comparator) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<V> result = new ArrayList<>();
    root.collectMoreThan(key, result, true, comparator);
    return result;
  }

  public List<V> collectEqualOrMoreThan(K value) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<V> result = new ArrayList<>();
    root.collectMoreThan(value, result, true);
    return result;
  }

  public List<V> collectLessThan(K key) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<V> result = new ArrayList<>();
    root.collectLessThan(key, result, false);
    return result;
  }

  public List<V> collectEqualOrLessThan(K key) {
    if (Objects.isNull(root)) return List.of();
    ArrayList<V> result = new ArrayList<>();
    root.collectLessThan(key, result, true);
    return result;
  }

  @Override
  public String toString() {
    return String.format("AVLTree{ root=%s }", root);
  }
}
