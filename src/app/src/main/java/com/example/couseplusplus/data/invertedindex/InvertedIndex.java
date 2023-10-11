package com.example.couseplusplus.data.invertedindex;

import java.util.Set;

/**
 * An inverted index implementation
 *
 * @param <V> reference to store
 * @author Yuki Misumi (u7582380)
 */
public interface InvertedIndex<K extends Comparable<K>, V> {
  void add(K index, V object);

  Set<V> get(K index);
}
