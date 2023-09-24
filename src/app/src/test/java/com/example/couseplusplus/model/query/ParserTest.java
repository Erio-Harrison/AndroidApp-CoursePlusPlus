package com.example.couseplusplus.model.query;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {
  @Test
  public void parsesWithoutException() {
    testParse("helpful > 10");
    testParse("enrol > 2023S1");
    testParse("posted > 2023-09-20");
    testParse("text ~ \"hello world\"");
    testParse("helpful > 10 & text ~ \"hello world\"");
    testParse("helpful > 10 | text ~ \"hello world\"");
    testParse("(helpful > 10 & posted > 2023-09-20) | text ~ \"hello world\"");
    testParse("helpful > 10 | (text ~ \"hello world\" & posted > 2023-09-20)");
    testParse("(helpful > 10)");
    testParse("((helpful > 10))");
  }

  void testParse(String queryString) {
    Tokenizer tokenizer = new Tokenizer(new Query(queryString));
    Parser uut = new Parser(tokenizer);
    ParseTree result = uut.parse();
    assertNotNull(result); // FIXME test node structure
  }
}
