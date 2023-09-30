package com.example.couseplusplus.model.query.tokenizer;

import static com.example.couseplusplus.model.query.tokenizer.TokenType.*;
import static org.junit.Assert.*;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.state.ProcessResult;
import com.example.couseplusplus.model.query.tokenizer.state.YearOrNumberState;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

/**
 * @author Yuki Misumi (u7582380)
 */
public class StateMachineTest {
  @Test
  public void testProcess() {
    Query query = new Query("12345");
    StateMachine uut = new StateMachine();

    ProcessResult result = uut.process(query, 0);

    assertTrue(uut.state instanceof YearOrNumberState);
    assertFalse(result.hasToken());
    assertEquals(0, result.index());
  }

  @Test
  public void proceedsStatesToTheEnd() {
    Query query = new Query("  123 2000-01-01 ");
    StateMachine uut = new StateMachine();

    List<Token> tokens = new ArrayList<>();
    int index = 0;
    while (!query.isOutOfRange(index)) {
      ProcessResult result = uut.process(query, index);
      if (result.hasToken()) tokens.add(result.token());
      index = result.index();
    }

    assertEquals(
        List.of(Integer, DateTime),
        tokens.stream().map(Token::tokenType).collect(Collectors.toList()));
  }
}
