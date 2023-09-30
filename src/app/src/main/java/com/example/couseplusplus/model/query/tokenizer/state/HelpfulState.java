package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

/**
 * @author Yuki Misumi (u7582380)
 */
public class HelpfulState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    if (!query.is(index, 'h')
        || !query.is(index + 1, 'e')
        || !query.is(index + 2, 'l')
        || !query.is(index + 3, 'p')
        || !query.is(index + 4, 'f')
        || !query.is(index + 5, 'u')
        || !query.is(index + 6, 'l')) throw new IllegalStateException();

    int nextIndex = index + 7;
    return new ProcessResult(nextIndex, new Token(TokenType.Helpful, null));
  }
}
