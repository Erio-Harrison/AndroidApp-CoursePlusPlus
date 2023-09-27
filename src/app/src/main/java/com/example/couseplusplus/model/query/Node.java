package com.example.couseplusplus.model.query;

/**
 * @author Yuki Misumi (u7582380)
 */
public abstract class Node {
  Token token;

  public Node(Token token) {
    this.token = token;
  }

  public Token token() {
    return token;
  }

  public Object value() {
    return token.value();
  }
}
