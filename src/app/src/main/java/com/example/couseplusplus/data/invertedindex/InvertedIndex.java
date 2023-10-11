package com.example.couseplusplus.data.invertedindex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An inverted index implementation
 *
 * @param <V> reference to store
 * @author Yuki Misumi (u7582380)
 */
public class InvertedIndex<K extends Comparable<K>, V> {
  Map<K, Set<V>> map;

  public InvertedIndex() {
    map = new HashMap<>();
  }

  public void add(K index, V object) {
    if (!map.containsKey(index)) map.put(index, new HashSet<>());
    Set<V> objects = map.get(index);
    objects.add(object);
  }

  public Set<V> get(K index) {
    return map.getOrDefault(index, Set.of());
  }
}
