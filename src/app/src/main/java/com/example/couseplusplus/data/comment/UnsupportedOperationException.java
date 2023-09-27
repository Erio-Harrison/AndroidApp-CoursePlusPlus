package com.example.couseplusplus.data.comment;

import com.example.couseplusplus.model.query.TokenType;

/**
 * @author Yuki Misumi (u7582380)
 */
public class UnsupportedOperationException extends RuntimeException {
  public UnsupportedOperationException(TokenType operatorType) {
    super(String.format("%s is not supported", operatorType));
  }
}
