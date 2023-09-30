package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.Token;
import com.example.couseplusplus.model.query.TokenType;

public class LessThanState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    if (!query.is(index, '<')) throw new IllegalStateException();
    if (!query.is(index + 1, '='))
      return new ProcessResult(index + 1, new Token(TokenType.LessThan, null));
    return new ProcessResult(index + 2, new Token(TokenType.EqualOrLessThan, null));
  }
}
