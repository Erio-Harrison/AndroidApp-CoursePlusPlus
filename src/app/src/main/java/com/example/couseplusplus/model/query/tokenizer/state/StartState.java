package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import java.util.Objects;

/**
 * @author Yuki Misumi (u7582380)
 */
public class StartState implements State {
  private static State instance;

  public static State getInstance() {
    if (Objects.isNull(instance)) instance = new StartState();
    return instance;
  }

  @Override
  public ProcessResult process(Query query, int index) {
    return new ProcessResult(index, null);
  }
}
