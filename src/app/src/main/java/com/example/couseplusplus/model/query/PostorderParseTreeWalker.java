package com.example.couseplusplus.model.query;

import java.util.List;

public interface PostorderParseTreeWalker<T> {
  default List<T> walk(ParseTree parseTree) {
    return traverse(parseTree.root());
  }

  default List<T> traverse(Node node) {
    if (node instanceof ComparisonNode) {
      var comparisonNode = (ComparisonNode) node;
      Node left = comparisonNode.left();
      Node right = comparisonNode.right();
      Token operator = comparisonNode.token();
      if (left instanceof IdNode && right instanceof TerminalNode) {
        return processComparison((IdNode) left, operator, (TerminalNode) right);
      }
      List<T> leftResult = traverse(left);
      List<T> rightResult = traverse(right);
      return processComparison(leftResult, operator, rightResult);
    }
    if (node instanceof ConditionalNode) {
      var conditionalNode = (ConditionalNode) node;
      Token operator = conditionalNode.token();
      List<T> leftResult = traverse(conditionalNode.left());
      List<T> rightResult = traverse(conditionalNode.right());
      return processConditional(leftResult, operator, rightResult);
    }
    throw new IllegalArgumentException(String.format("node(%s) is not supported", node));
  }

  List<T> processComparison(IdNode left, Token operator, TerminalNode right);

  List<T> processComparison(List<T> left, Token operator, List<T> right);

  List<T> processConditional(List<T> left, Token operator, List<T> right);
}
