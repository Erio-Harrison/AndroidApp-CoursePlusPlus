package com.example.couseplusplus.model.query.tokenizer;

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

  @Override
  public String toString() {
    return String.format("Token{tokenType=%s, value=%s}", tokenType, value);
  }
}
