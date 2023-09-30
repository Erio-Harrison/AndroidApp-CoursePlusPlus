package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;

public interface State {
  ProcessResult process(Query query, int index);
}
