package com.example.couseplusplus.view.query;

/**
 * A utility for tuple
 *
 * @param <T>
 * @param <U>
 * @author Yuki Misumi (u7582380)
 */
public class Pair<T, U> {
  T first;
  U second;

  public Pair(T first, U second) {
    this.first = first;
    this.second = second;
  }
}
