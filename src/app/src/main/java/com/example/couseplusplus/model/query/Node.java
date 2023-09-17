package com.example.couseplusplus.model.query;

public abstract class Node {
  Token token;

  public Node(Token token) {
    this.token = token;
  }

  public Token token() {
    return token;
  }
}
