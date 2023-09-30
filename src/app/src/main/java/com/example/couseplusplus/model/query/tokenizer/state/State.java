package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;

/**
 * @author Yuki Misumi (u7582380)
 */
public interface State {
  ProcessResult process(Query query, int index);
}
