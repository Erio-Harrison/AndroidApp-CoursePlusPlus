package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.DateNode;
import com.example.couseplusplus.model.query.EnrolDateNode;
import com.example.couseplusplus.model.query.IdNode;
import com.example.couseplusplus.model.query.NumberNode;
import com.example.couseplusplus.model.query.PostorderParseTreeWalker;
import com.example.couseplusplus.model.query.TerminalNode;
import com.example.couseplusplus.model.query.TextNode;
import com.example.couseplusplus.model.query.Token;
import com.example.couseplusplus.model.query.TokenType;
import java.time.LocalDate;
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
      DateNode dateNode = (DateNode) right;
      String key = dateNode.localDate().toString();
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

  public boolean compareHelpful(Comment comment, TokenType operatorType, TerminalNode right) {
    NumberNode numberNode = (NumberNode) right;
    Integer number = numberNode.integer();
    int helpfulness = comment.helpfulness();
    if (operatorType == TokenType.MoreThan) return helpfulness > number;
    if (operatorType == TokenType.LessThan) return helpfulness < number;
    if (operatorType == TokenType.EqualOrMoreThan) return helpfulness >= number;
    if (operatorType == TokenType.EqualOrLessThan) return helpfulness <= number;
    if (operatorType == TokenType.Equal) return helpfulness == number;
    throw new IllegalArgumentException();
  }

  public boolean compareEnrol(Comment comment, TokenType operatorType, TerminalNode right) {
    EnrolDateNode enrolDateNode = (EnrolDateNode) right;
    if (operatorType == TokenType.MoreThan) {
      return (comment.year() > enrolDateNode.year()
          || (comment.year() == enrolDateNode.year()
              && comment.semester() > enrolDateNode.semester()));
    }
    if (operatorType == TokenType.LessThan) {
      return (comment.year() < enrolDateNode.year()
          || (comment.year() == enrolDateNode.year()
              && comment.semester() < enrolDateNode.semester()));
    }
    if (operatorType == TokenType.EqualOrMoreThan) {
      return (comment.year() >= enrolDateNode.year()
          || (comment.year() == enrolDateNode.year()
              && comment.semester() >= enrolDateNode.semester()));
    }
    if (operatorType == TokenType.EqualOrLessThan) {
      return (comment.year() <= enrolDateNode.year()
          || (comment.year() == enrolDateNode.year()
              && comment.semester() <= enrolDateNode.semester()));
    }
    if (operatorType == TokenType.Equal) {
      return comment.year() == enrolDateNode.year()
          && comment.semester() == enrolDateNode.semester();
    }
    throw new IllegalArgumentException();
  }

  public boolean comparePosted(Comment comment, TokenType operatorType, TerminalNode right) {
    DateNode dateNode = (DateNode) right;
    LocalDate localDate = comment.postedDateTime().toLocalDate();
    if (operatorType == TokenType.MoreThan) return localDate.isAfter(dateNode.localDate());
    if (operatorType == TokenType.LessThan) return localDate.isBefore(dateNode.localDate());
    if (operatorType == TokenType.EqualOrMoreThan)
      return localDate.isEqual(dateNode.localDate()) || localDate.isAfter(dateNode.localDate());
    if (operatorType == TokenType.EqualOrLessThan)
      return localDate.isEqual(dateNode.localDate()) || localDate.isBefore(dateNode.localDate());
    if (operatorType == TokenType.Equal) return localDate.isEqual(dateNode.localDate());
    throw new IllegalArgumentException();
  }

  public boolean compareText(Comment comment, TokenType operatorType, TerminalNode right) {
    TextNode textNode = (TextNode) right;
    if (operatorType != TokenType.Like) throw new IllegalArgumentException();
    return comment.text().contains(textNode.text());
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
