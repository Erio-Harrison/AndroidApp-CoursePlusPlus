package com.example.couseplusplus.model.query;

public interface PostorderParseTreeWalker<T> {
  default T walk(ParseTree parseTree) {
    return traverse(parseTree.root());
  }

  default T traverse(Node node) {
    if (node instanceof ComparisonNode) {
      var comparisonNode = (ComparisonNode) node;
      Node left = comparisonNode.left();
      Node right = comparisonNode.right();
      Token operator = comparisonNode.token();
      if (left instanceof IdNode && right instanceof TerminalNode) {
        return processComparison((IdNode) left, operator, (TerminalNode) right);
      }
      T leftResult = traverse(left);
      T rightResult = traverse(right);
      return processComparison(leftResult, operator, rightResult);
    }
    if (node instanceof ConditionalNode) {
      var conditionalNode = (ConditionalNode) node;
      Token operator = conditionalNode.token();
      T leftResult = traverse(conditionalNode.left());
      T rightResult = traverse(conditionalNode.right());
      return processConditional(leftResult, operator, rightResult);
    }
    throw new IllegalArgumentException(String.format("node(%s) is not supported", node));
  }

  T processComparison(IdNode left, Token operator, TerminalNode right);

  T processComparison(T left, Token operator, T right);

  T processConditional(T left, Token operator, T right);
}
