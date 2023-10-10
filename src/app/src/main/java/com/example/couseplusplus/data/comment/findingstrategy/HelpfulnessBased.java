package com.example.couseplusplus.data.comment.findingstrategy;

import com.example.couseplusplus.data.comment.CommentCache;
import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.parser.NumberNode;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class HelpfulnessBased implements CommentFindingStrategy {
  @Override
  public List<NewComment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache) {
    NumberNode numberNode = (NumberNode) terminalNode;
    if (operatorType == TokenType.MoreThan)
      return commentCache.helpfulTree().collectMoreThan(numberNode.integer());
    if (operatorType == TokenType.EqualOrMoreThan)
      return commentCache.helpfulTree().collectEqualOrMoreThan(numberNode.integer());
    if (operatorType == TokenType.LessThan)
      return commentCache.helpfulTree().collectLessThan(numberNode.integer());
    if (operatorType == TokenType.EqualOrLessThan)
      return commentCache.helpfulTree().collectEqualOrLessThan(numberNode.integer());
    if (operatorType == TokenType.Equal)
      return commentCache.helpfulTree().findAll(numberNode.integer());
    throw new UnsupportedOperationException(operatorType);
  }
}
