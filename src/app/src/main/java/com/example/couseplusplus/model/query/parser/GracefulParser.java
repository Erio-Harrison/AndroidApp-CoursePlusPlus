package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.NoTransitionException;
import com.example.couseplusplus.model.query.tokenizer.Tokenizer;
import com.example.couseplusplus.model.query.tokenizer.state.IllegalTokenException;
import java.util.function.Supplier;

/**
 * {@link Parser} extension that returns {@link ErrorNode} in the case of illegal token or parser,
 * instead of throwing an exception.
 *
 * @author Yuki Misumi (u7582380)
 */
public class GracefulParser extends Parser {
  public GracefulParser(Tokenizer tokenizer) {
    super(tokenizer);
  }

  @Override
  public Node query() {
    return runOrGetErrorNode(super::query);
  }

  @Override
  public Node expression() {
    return runOrGetErrorNode(super::expression);
  }

  Node runOrGetErrorNode(Supplier<Node> runner) {
    try {
      return runner.get();
    } catch (IllegalTokenException | NoTransitionException | IllegalSyntaxException e) {
      return new ErrorNode(currentToken, e);
    }
  }
}
