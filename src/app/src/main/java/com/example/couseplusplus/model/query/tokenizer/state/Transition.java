package com.example.couseplusplus.model.query.tokenizer.state;

import com.example.couseplusplus.model.query.Query;

public interface Transition {
  boolean canTransition(Query query, int index);
}
