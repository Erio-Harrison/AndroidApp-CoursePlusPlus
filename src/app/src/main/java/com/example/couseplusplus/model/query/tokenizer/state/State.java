package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.ProcessResult;

/**
 * @author Yuki Misumi (u7582380)
 */
public interface State {
  ProcessResult process(Query query, int index);
}
