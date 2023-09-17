package com.example.couseplusplus.model.query;

public class BinaryOperationNode extends Node {
  Node left;
  Node right;

  public BinaryOperationNode(Node left, Token operatorToken, Node right) {
    super(operatorToken);
    this.left = left;
    this.right = right;
  }
}
