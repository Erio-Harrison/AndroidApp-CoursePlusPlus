package com.example.couseplusplus.model.query;

import com.example.couseplusplus.model.query.parser.ComparisonNode;
import com.example.couseplusplus.model.query.parser.ConditionalNode;
import com.example.couseplusplus.model.query.parser.ErrorNode;
import com.example.couseplusplus.model.query.parser.IdNode;
import com.example.couseplusplus.model.query.parser.Node;
import com.example.couseplusplus.model.query.parser.ParseTree;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.Token;

/**
 * @author Yuki Misumi (u7582380)
 */
public interface PostorderParseTreeWalker<T> {

  /**
   * walks {@link ParseTree}.
   *
   * @param parseTree
   * @return T or null if {@link ErrorNode} is the root of the tree.
   */
  default T walk(ParseTree parseTree) {
    return traverse(parseTree.root());
  }

  /**
   * traverses {@link Node}.
   *
   * @param node
   * @return if {@link ErrorNode} was found, returns null, otherwise T.
   */
  default T traverse(Node node) {
    if (node instanceof ErrorNode) return null;
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

  /**
   * processes {@link ComparisonNode}.
   *
   * @param left
   * @param operator
   * @param right
   * @return T
   */
  T processComparison(IdNode left, Token operator, TerminalNode right);

  /**
   * processes {@link ConditionalNode}.
   *
   * @implNote any {@link Node} can be null because of {@link #traverse(Node)}
   * @param left
   * @param operator
   * @param right
   * @return T
   */
  T processConditional(T left, Token operator, T right);
}
