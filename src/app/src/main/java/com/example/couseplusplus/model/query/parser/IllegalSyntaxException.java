package com.example.couseplusplus.model.query.parser;

/**
 * @author Yuki Misumi (u7582380)
 */
public class IllegalSyntaxException extends RuntimeException {
  public IllegalSyntaxException() {
    super("Illegal syntax was found");
  }
}
