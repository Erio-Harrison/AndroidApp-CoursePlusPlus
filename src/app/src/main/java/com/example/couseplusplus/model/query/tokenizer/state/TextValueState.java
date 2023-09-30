package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

public class TextValueState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    int start = index;
    if (!query.isDoubleQuote(index)) throw new IllegalStateException();
    index++;
    while (!query.isDoubleQuote(index) && !query.isOutOfRange(index)) index++;

    if (query.isOutOfRange(index)) throw new IllegalStateException();

    int nextIndex = index + 1;
    Token token = new Token(TokenType.TextValue, query.getSubstring(start, nextIndex));
    return new ProcessResult(nextIndex, token);
  }
}
