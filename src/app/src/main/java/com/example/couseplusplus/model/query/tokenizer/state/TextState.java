package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.Token;
import com.example.couseplusplus.model.query.TokenType;

public class TextState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    if (!query.is(index, 't')
        || !query.is(index + 1, 'e')
        || !query.is(index + 2, 'x')
        || !query.is(index + 3, 't')) throw new IllegalStateException();

    int nextIndex = index + 4;
    return new ProcessResult(nextIndex, new Token(TokenType.Text, null));
  }
}
