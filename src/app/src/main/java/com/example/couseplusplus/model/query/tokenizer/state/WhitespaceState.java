package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.ProcessResult;

/**
 * @author Yuki Misumi (u7582380)
 */
public class WhitespaceState implements State {
  @Override
  public ProcessResult process(Query query, int index) {
    while (query.isSpace(index)) index++;

    return new ProcessResult(index, null);
  }
}
