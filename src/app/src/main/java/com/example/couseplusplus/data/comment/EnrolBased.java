package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.NewComment;
import com.example.couseplusplus.model.query.parser.EnrolDateNode;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public class EnrolBased implements CommentFindingStrategy {
  @Override
  public List<NewComment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache) {
    EnrolDateNode enrolDateNode = (EnrolDateNode) terminalNode;
    String key = enrolDateNode.value().toString();
    if (operatorType == TokenType.MoreThan) return commentCache.enrolTree.collectMoreThan(key);
    if (operatorType == TokenType.EqualOrMoreThan)
      return commentCache.enrolTree.collectEqualOrMoreThan(key);
    if (operatorType == TokenType.LessThan) return commentCache.enrolTree.collectLessThan(key);
    if (operatorType == TokenType.EqualOrLessThan)
      return commentCache.enrolTree.collectEqualOrLessThan(key);
    if (operatorType == TokenType.Equal) return commentCache.enrolTree.findAll(key);
    throw new UnsupportedOperationException(operatorType);
  }
}
