package com.example.couseplusplus.model.query;

public class NumberNode extends TerminalNode {
  public NumberNode(Token token) {
    super(token);
  }

  public Integer integer() {
    return (Integer) value();
  }
}
