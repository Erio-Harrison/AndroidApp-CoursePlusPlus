package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.ProcessResult;
import com.example.couseplusplus.model.query.tokenizer.Token;
import com.example.couseplusplus.model.query.tokenizer.TokenType;

/**
 * @author Yuki Misumi (u7582380)
 */
public class PostedState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    if (!query.is(index, 'p')
        || !query.is(index + 1, 'o')
        || !query.is(index + 2, 's')
        || !query.is(index + 3, 't')
        || !query.is(index + 4, 'e')
        || !query.is(index + 5, 'd')) throw new IllegalTokenException();

    int nextIndex = index + 6;
    return new ProcessResult(nextIndex, new Token(TokenType.Posted, null));
  }
}
