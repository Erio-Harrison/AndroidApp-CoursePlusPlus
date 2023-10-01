package com.example.couseplusplus.model.query.tokenizer;

import com.example.couseplusplus.model.query.Query;

/**
 * @author Yuki Misumi (u7582380)
 */
public class Tokenizer {
  Query query;
  int index;
  StateMachine stateMachine;

  public Tokenizer(Query query) {
    this.query = query;
    index = 0;
    stateMachine = new StateMachine();
  }

  public boolean hasNext() {
    return !query.isOutOfRange(index);
  }

  public Token getNextToken() {
    while (stateMachine.isRunning()) {
      ProcessResult result = stateMachine.process(query, index);
      index = result.index();
      if (result.hasToken()) return result.token();
    }
    return new Token(TokenType.End, null);
  }
}
