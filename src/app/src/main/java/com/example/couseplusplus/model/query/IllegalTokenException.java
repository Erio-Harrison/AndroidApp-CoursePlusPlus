package com.example.couseplusplus.model.query;

public class IllegalTokenException extends RuntimeException {
  public IllegalTokenException() {
    super("Illegal token was found");
  }
}
