package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

public class EqualState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    if (!query.is(index, '=')) throw new IllegalStateException();
    return new ProcessResult(index + 1, new Token(TokenType.Equal, null));
  }
}
