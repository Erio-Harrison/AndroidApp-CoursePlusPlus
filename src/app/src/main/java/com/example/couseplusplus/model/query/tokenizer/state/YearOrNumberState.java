package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.Token;
import com.example.couseplusplus.model.query.TokenType;

public class YearOrNumberState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    int start = index;
    while (query.isDigit(index)) index++;

    if (index - start == 4) return new ProcessResult(index, null);

    Integer integer = query.getInteger(start, index);
    Token token = new Token(TokenType.Integer, integer);
    return new ProcessResult(index, token);
  }
}
