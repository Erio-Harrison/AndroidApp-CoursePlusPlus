package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.ProcessResult;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

/**
 * @author Yuki Misumi (u7582380)
 */
public class EnrolState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    if (!query.is(index, 'e')
        || !query.is(index + 1, 'n')
        || !query.is(index + 2, 'r')
        || !query.is(index + 3, 'o')
        || !query.is(index + 4, 'l')) throw new IllegalTokenException();

    int nextIndex = index + 5;
    return new ProcessResult(nextIndex, new Token(TokenType.Enrol, null));
  }
}
