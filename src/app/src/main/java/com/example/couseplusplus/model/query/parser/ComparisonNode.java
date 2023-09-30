package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.Token;

/**
 * @author Yuki Misumi (u7582380)
 */
public class ComparisonNode extends Node {
  Node left;
  Node right;

  public ComparisonNode(Node left, Token operatorToken, Node right) {
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
}
