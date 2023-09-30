package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

/**
 * @author Yuki Misumi (u7582380)
 */
public class YearOrNumberState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    int start = index;
    while (query.isDigit(index)) index++;

    // FIXME knows a lot about other states
    if (index - start == 4 && (query.isHyphen(index) || query.isS(index)))
      return new ProcessResult(index, null);

    Integer integer = query.getInteger(start, index);
    Token token = new Token(TokenType.Integer, integer);
    return new ProcessResult(index, token);
  }
}
