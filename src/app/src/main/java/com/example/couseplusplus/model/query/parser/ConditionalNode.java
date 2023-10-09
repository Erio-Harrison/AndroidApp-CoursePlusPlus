package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.Token;

/**
 * @author Yuki Misumi (u7582380)
 */
public class ConditionalNode extends Node {
  Node left;
  Node right;

  public ConditionalNode(Node left, Token operatorToken, Node right) {
    super(operatorToken);
    this.left = left;
    this.right = right;
  }

  public Node left() {
    return left;
  }

  public Node right() {
    return right;
  }

  @Override
  public String toString() {
    return String.format("ConditionalNode{left=%s, right=%s, token=%s}", left, right, token);
  }
}
