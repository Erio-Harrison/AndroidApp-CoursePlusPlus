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

  public CommentFinder(List<Comment> comments) {
    this.comments = comments;
  }

  @Override
  public List<Comment> processComparison(IdNode left, Token operator, TerminalNode right) {
    TokenType idTokenType = left.token().tokenType();
    TokenType operatorType = operator.tokenType();
    if (idTokenType == TokenType.Helpful) {
      return comments.stream()
          .filter(comment -> compareHelpful(comment, operatorType, right))
          .collect(Collectors.toList());
    }
    if (idTokenType == TokenType.Enrol) {
      return comments.stream()
          .filter(comment -> compareEnrol(comment, operatorType, right))
          .collect(Collectors.toList());
    }
    if (idTokenType == TokenType.Posted) {
      return comments.stream()
          .filter(comment -> comparePosted(comment, operatorType, right))
          .collect(Collectors.toList());
    }
    if (idTokenType == TokenType.Text) {
      return comments.stream()
          .filter(comment -> compareText(comment, operatorType, right))
          .collect(Collectors.toList());
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
