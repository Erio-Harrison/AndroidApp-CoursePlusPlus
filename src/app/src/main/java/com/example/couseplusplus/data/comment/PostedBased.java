package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.DateTimeNode;
import com.example.couseplusplus.model.query.TerminalNode;
import com.example.couseplusplus.model.query.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class PostedBased implements CommentFindingStrategy {
  @Override
  public List<Comment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache) {
    DateTimeNode dateTimeNode = (DateTimeNode) terminalNode;
    String key = dateTimeNode.localDateTime().toString();
    if (operatorType == TokenType.MoreThan) return commentCache.postedTree.collectMoreThan(key);
    if (operatorType == TokenType.EqualOrMoreThan)
      return commentCache.postedTree.collectEqualOrMoreThan(key);
    if (operatorType == TokenType.LessThan) return commentCache.postedTree.collectLessThan(key);
    if (operatorType == TokenType.EqualOrLessThan)
      return commentCache.postedTree.collectEqualOrLessThan(key);
    if (operatorType == TokenType.Equal) return commentCache.postedTree.findAll(key);
    throw new UnsupportedOperationException(operatorType);
  }
}
