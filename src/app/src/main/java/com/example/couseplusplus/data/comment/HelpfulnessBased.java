package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.NumberNode;
import com.example.couseplusplus.model.query.TerminalNode;
import com.example.couseplusplus.model.query.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class HelpfulnessBased implements CommentFindingStrategy {
  @Override
  public List<Comment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache) {
    NumberNode numberNode = (NumberNode) terminalNode;
    if (operatorType == TokenType.MoreThan)
      return commentCache.helpfulTree.collectMoreThan(numberNode.integer());
    if (operatorType == TokenType.EqualOrMoreThan)
      return commentCache.helpfulTree.collectEqualOrMoreThan(numberNode.integer());
    if (operatorType == TokenType.LessThan)
      return commentCache.helpfulTree.collectLessThan(numberNode.integer());
    if (operatorType == TokenType.EqualOrLessThan)
      return commentCache.helpfulTree.collectEqualOrLessThan(numberNode.integer());
    if (operatorType == TokenType.Equal)
      return commentCache.helpfulTree.findAll(numberNode.integer());
    throw new UnsupportedOperationException(operatorType);
  }
}
