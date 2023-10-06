package com.example.couseplusplus.model.query.tokenizer;

public class NoTransitionException extends RuntimeException {
  public NoTransitionException() {
    super("No state can be transitioned.");
  }
}
