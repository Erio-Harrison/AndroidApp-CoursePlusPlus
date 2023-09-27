package com.example.couseplusplus.model.query;

/**
 * @author Yuki Misumi (u7582380)
 */
public class Token {
  TokenType tokenType;
  Object value;

  public Token(TokenType tokenType, Object value) {
    this.tokenType = tokenType;
    this.value = value;
  }

  public TokenType tokenType() {
    return tokenType;
  }

  public Object value() {
    return value;
  }
}
