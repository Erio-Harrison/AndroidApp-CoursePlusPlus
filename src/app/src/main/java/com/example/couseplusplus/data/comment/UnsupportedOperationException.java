package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.query.TokenType;

public class UnsupportedOperationException extends RuntimeException {
  public UnsupportedOperationException(TokenType operatorType) {
    super(String.format("%s is not supported", operatorType));
  }
}
