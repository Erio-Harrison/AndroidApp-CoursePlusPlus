package com.example.couseplusplus.model.query;

public class ConditionalNode extends Node {
  Node left;
  Node right;

  public ConditionalNode(Node left, Token operatorToken, Node right) {
    super(operatorToken);
    this.left = left;
    this.right = right;
  }
}
