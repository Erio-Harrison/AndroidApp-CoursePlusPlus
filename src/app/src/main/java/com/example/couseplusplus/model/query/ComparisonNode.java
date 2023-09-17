package com.example.couseplusplus.model.query;

public class ComparisonNode extends Node {
  Node left;
  Node right;

  public ComparisonNode(Node left, Token operatorToken, Node right) {
    super(operatorToken);
    this.left = left;
    this.right = right;
  }
}
