package com.example.couseplusplus.model.query;

public class IllegalSyntaxException extends RuntimeException {
  public IllegalSyntaxException() {
    super("Illegal syntax was found");
  }
}
