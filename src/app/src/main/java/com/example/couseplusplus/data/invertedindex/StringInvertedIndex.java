package com.example.couseplusplus.data.invertedindex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A string inverted index implementation
 *
 * @param <V>
 * @author Yuki Misumi (u7582380)
 */
public class StringInvertedIndex<V> implements InvertedIndex<String, V> {
  Map<String, Set<V>> map;

  public StringInvertedIndex() {
    map = new HashMap<>();
  }

  @Override
  public void add(String index, V object) {
    String key = index.toLowerCase();
    if (!map.containsKey(key)) map.put(key, new HashSet<>());
    Set<V> objects = map.get(key);
    objects.add(object);
  }

  @Override
  public Set<V> get(String index) {
    String key = index.toLowerCase();
    return map.getOrDefault(key, Set.of());
  }
}
