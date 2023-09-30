package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.tokenizer.Token;
import java.util.Objects;

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
