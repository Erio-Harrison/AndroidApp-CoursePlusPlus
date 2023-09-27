package com.example.couseplusplus.model.query;

/**
 * @author Yuki Misumi (u7582380)
 */
public class IllegalTokenException extends RuntimeException {
  public IllegalTokenException() {
    super("Illegal token was found");
  }
}
