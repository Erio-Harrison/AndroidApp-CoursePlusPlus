package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.ProcessResult;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

/**
 * @author Yuki Misumi (u7582380)
 */
public class DateTimeState implements State {

  @Override
  public ProcessResult process(Query query, int index) {
    if (!query.isHyphen(index)
        || !query.isDigit(index + 1)
        || !query.isDigit(index + 2)
        || !query.isHyphen(index + 3)
        || !query.isDigit(index + 4)
        || !query.isDigit(index + 5)) throw new IllegalTokenException();

    int nextIndex = index + 6;

    if (query.is(index + 6, 'T')
        && query.isDigit(index + 7)
        && query.isDigit(index + 8)
        && query.is(index + 9, ':')
        && query.isDigit(index + 10)
        && query.isDigit(index + 11)) nextIndex = index + 12;

    if (query.is(index + 12, ':') && query.isDigit(index + 13) && query.isDigit(index + 14))
      nextIndex = index + 15;

    int yearStartIndex = index - 4;
    Token token = new Token(TokenType.DateTime, query.getDateTime(yearStartIndex, nextIndex));
    return new ProcessResult(nextIndex, token);
  }
}
