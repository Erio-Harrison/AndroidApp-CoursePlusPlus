package com.example.couseplusplus.model.query.parser;

/**
 * @author Yuki Misumi (u7582380)
 */
public class ParseTree {
  Node root;

  public ParseTree(Node root) {
    this.root = root;
  }

  public Node root() {
    return root;
  }

  @Override
  public String toString() {
    return String.format("ParseTree{root=%s}", root);
  }
}
