package com.example.couseplusplus.model.query.tokenizer;

import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class ProcessResult {
  int index;
  Token token;

  public ProcessResult(int index, Token token) {
    this.index = index;
    this.token = token;
  }

  public int index() {
    return index;
  }

  public Token token() {
    return token;
  }

  public boolean hasToken() {
    return Objects.nonNull(token);
  }
}
