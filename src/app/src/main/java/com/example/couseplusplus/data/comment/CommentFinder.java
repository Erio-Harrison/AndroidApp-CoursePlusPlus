package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.PostorderParseTreeWalker;
import com.example.couseplusplus.model.query.parser.IdNode;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yuki Misumi (u7582380)
 */
public class CommentFinder implements PostorderParseTreeWalker<List<Comment>> {
  static final Map<TokenType, CommentFindingStrategy> strategyMap =
      Map.of(
          TokenType.Helpful, new HelpfulnessBased(),
          TokenType.Enrol, new EnrolBased(),
          TokenType.Posted, new PostedBased(),
          TokenType.Text, new TextBased());
  CommentCache commentCache;

  public CommentFinder(CommentCache commentCache) {
    this.commentCache = commentCache;
  }

  @Override
  public List<Comment> processComparison(IdNode left, Token operator, TerminalNode right) {
    TokenType idTokenType = left.token().tokenType();
    TokenType operatorType = operator.tokenType();
    if (!strategyMap.containsKey(idTokenType))
      throw new IllegalArgumentException(String.format("IdToken(%s) not supported", left));
    return strategyMap.get(idTokenType).find(operatorType, right, commentCache);
  }

  @Override
  public List<Comment> processConditional(List<Comment> left, Token operator, List<Comment> right) {
    TokenType operatorType = operator.tokenType();
    if (operatorType == TokenType.Or)
      return Stream.concat(left.stream(), right.stream()).distinct().collect(Collectors.toList());
    if (operatorType == TokenType.And)
      return left.stream().filter(right::contains).collect(Collectors.toList());
    throw new UnsupportedOperationException(operatorType);
  }
}
