package com.example.couseplusplus.model.query;

/**
 * @author Yuki Misumi (u7582380)
 */
public class NumberNode extends TerminalNode {
  public NumberNode(Token token) {
    super(token);
  }

  public Integer integer() {
    return (Integer) value();
  }
}
