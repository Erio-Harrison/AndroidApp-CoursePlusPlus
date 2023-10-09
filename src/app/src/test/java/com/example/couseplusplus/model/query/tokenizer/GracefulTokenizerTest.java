package com.example.couseplusplus.model.query.tokenizer;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.query.Query;
import org.junit.Test;

public class GracefulTokenizerTest {
  @Test
  public void testGetNextToken() {
    GracefulTokenizer uut = new GracefulTokenizer(new Query("helpfulness"));
    Token token = uut.getNextToken();
    assertEquals(TokenType.Error, token.tokenType);
    assertEquals(NoTransitionException.class, token.value().getClass());
  }
}
