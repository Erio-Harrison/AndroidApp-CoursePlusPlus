package com.example.couseplusplus.model.query.parser;

import com.example.couseplusplus.model.query.tokenizer.Token;

public class ErrorNode extends Node {
  Exception exception;

  public ErrorNode(Token token, Exception exception) {
    super(token);
    this.exception = exception;
  }

  @Override
  public String toString() {
    return String.format(
        "ErrorNode{exception=%s, token=%s}", exception.getClass().getSimpleName(), token);
  }
}
