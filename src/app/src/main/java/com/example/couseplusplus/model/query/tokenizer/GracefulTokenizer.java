package com.example.couseplusplus.model.query.tokenizer;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.state.IllegalTokenException;
import java.util.function.Supplier;

public class GracefulTokenizer extends Tokenizer {
  public GracefulTokenizer(Query query) {
    super(query);
  }

  @Override
  public Token getNextToken() {
    return runOrGetErrorToken(super::getNextToken);
  }

  Token runOrGetErrorToken(Supplier<Token> runner) {
    try {
      return runner.get();
    } catch (IllegalTokenException | NoTransitionException e) {
      return new Token(TokenType.Error, e);
    }
  }
}
