package com.example.couseplusplus.model.query;

import com.example.couseplusplus.model.query.parser.ComparisonNode;
import com.example.couseplusplus.model.query.parser.ConditionalNode;
import com.example.couseplusplus.model.query.parser.IdNode;
import com.example.couseplusplus.model.query.parser.Node;
import com.example.couseplusplus.model.query.parser.ParseTree;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.Token;

/**
 * @author Yuki Misumi (u7582380)
 */
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
      if (left instanceof IdNode && right instanceof TerminalNode)
        return processComparison((IdNode) left, operator, (TerminalNode) right);
      throw new IllegalStateException();
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

  T processConditional(T left, Token operator, T right);
}
