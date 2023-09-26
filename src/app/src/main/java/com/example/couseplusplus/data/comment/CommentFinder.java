package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.DateTimeNode;
import com.example.couseplusplus.model.query.EnrolDateNode;
import com.example.couseplusplus.model.query.IdNode;
import com.example.couseplusplus.model.query.NumberNode;
import com.example.couseplusplus.model.query.PostorderParseTreeWalker;
import com.example.couseplusplus.model.query.TerminalNode;
import com.example.couseplusplus.model.query.TextNode;
import com.example.couseplusplus.model.query.Token;
import com.example.couseplusplus.model.query.TokenType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommentFinder implements PostorderParseTreeWalker<List<Comment>> {
  List<Comment> comments;
  CommentCache commentCache;

  public CommentFinder(CommentCache commentCache) {
    this.commentCache = commentCache;
  }

  @Override
  public List<Comment> processComparison(IdNode left, Token operator, TerminalNode right) {
    TokenType idTokenType = left.token().tokenType();
    TokenType operatorType = operator.tokenType();
    if (idTokenType == TokenType.Helpful) {
      NumberNode numberNode = (NumberNode) right;
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
      throw new IllegalStateException(String.format("%s is not supported", operatorType));
    }
    if (idTokenType == TokenType.Enrol) {
      EnrolDateNode enrolDateNode = (EnrolDateNode) right;
      String key = enrolDateNode.value().toString();
      if (operatorType == TokenType.MoreThan) return commentCache.enrolTree.collectMoreThan(key);
      if (operatorType == TokenType.EqualOrMoreThan)
        return commentCache.enrolTree.collectEqualOrMoreThan(key);
      if (operatorType == TokenType.LessThan) return commentCache.enrolTree.collectLessThan(key);
      if (operatorType == TokenType.EqualOrLessThan)
        return commentCache.enrolTree.collectEqualOrLessThan(key);
      if (operatorType == TokenType.Equal) return commentCache.enrolTree.findAll(key);
      throw new IllegalStateException(String.format("%s is not supported", operatorType));
    }
    if (idTokenType == TokenType.Posted) {
      DateTimeNode dateTimeNode = (DateTimeNode) right;
      String key = dateTimeNode.localDateTime().toString();
      if (operatorType == TokenType.MoreThan) return commentCache.postedTree.collectMoreThan(key);
      if (operatorType == TokenType.EqualOrMoreThan)
        return commentCache.postedTree.collectEqualOrMoreThan(key);
      if (operatorType == TokenType.LessThan) return commentCache.postedTree.collectLessThan(key);
      if (operatorType == TokenType.EqualOrLessThan)
        return commentCache.postedTree.collectEqualOrLessThan(key);
      if (operatorType == TokenType.Equal) return commentCache.postedTree.findAll(key);
      throw new IllegalStateException(String.format("%s is not supported", operatorType));
    }
    if (idTokenType == TokenType.Text) {
      TextNode textNode = (TextNode) right;
      String key = textNode.text();
      if (operatorType == TokenType.Like)
        return commentCache.textTree.collectEqualOrMoreThan(
            key,
            (key1, key2) -> {
              if (key1.equals(key2)) return 0;
              return key2.contains(key1) ? -1 : 1;
            });
      throw new IllegalStateException(String.format("%s is not supported", operatorType));
    }
    throw new IllegalArgumentException(String.format("IdToken(%s) not supported", left));
  }

  @Override
  public List<Comment> processConditional(List<Comment> left, Token operator, List<Comment> right) {
    TokenType operatorType = operator.tokenType();
    if (operatorType == TokenType.Or)
      return Stream.concat(left.stream(), right.stream()).distinct().collect(Collectors.toList());
    if (operatorType == TokenType.And)
      return left.stream().filter(right::contains).collect(Collectors.toList());
    throw new IllegalArgumentException();
  }
}
