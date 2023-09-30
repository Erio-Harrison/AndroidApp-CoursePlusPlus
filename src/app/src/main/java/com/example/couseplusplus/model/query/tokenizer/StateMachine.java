package com.example.couseplusplus.model.query.tokenizer;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.state.DateTimeState;
import com.example.couseplusplus.model.query.tokenizer.state.EnrolState;
import com.example.couseplusplus.model.query.tokenizer.state.HelpfulState;
import com.example.couseplusplus.model.query.tokenizer.state.PostedState;
import com.example.couseplusplus.model.query.tokenizer.state.ProcessResult;
import com.example.couseplusplus.model.query.tokenizer.state.SemesterState;
import com.example.couseplusplus.model.query.tokenizer.state.StartState;
import com.example.couseplusplus.model.query.tokenizer.state.State;
import com.example.couseplusplus.model.query.tokenizer.state.StateFactory;
import com.example.couseplusplus.model.query.tokenizer.state.TextState;
import com.example.couseplusplus.model.query.tokenizer.state.TextValueState;
import com.example.couseplusplus.model.query.tokenizer.state.Transition;
import com.example.couseplusplus.model.query.tokenizer.state.WhitespaceState;
import com.example.couseplusplus.model.query.tokenizer.state.YearOrNumberState;
import java.util.HashMap;
import java.util.Map;

public class StateMachine {
  static Map<Class<? extends State>, Map<Class<? extends State>, Transition>> map;

  static {
    map = new HashMap<>();

    Map<Class<? extends State>, Transition> startTo = new HashMap<>();
    startTo.put(YearOrNumberState.class, Query::isDigit);
    startTo.put(WhitespaceState.class, Query::isSpace);
    startTo.put(TextValueState.class, Query::isDoubleQuote);
    startTo.put(HelpfulState.class, (query, index) -> query.is(index, 'h'));
    startTo.put(EnrolState.class, (query, index) -> query.is(index, 'e'));
    startTo.put(PostedState.class, (query, index) -> query.is(index, 'p'));
    startTo.put(TextState.class, (query, index) -> query.is(index, 't'));
    map.put(StartState.class, startTo);

    map.put(
        YearOrNumberState.class,
        Map.of(DateTimeState.class, Query::isHyphen, SemesterState.class, Query::isS));
  }

  State state;

  public StateMachine() {
    this.state = StartState.getInstance();
  }

  public ProcessResult process(Query query, Integer index) {
    ProcessResult result = state.process(query, index);
    state = changeState(query, result.index());
    return result;
  }

  State changeState(Query query, Integer index) {
    if (query.isOutOfRange(index) || !map.containsKey(state.getClass()))
      return StateFactory.create(StartState.class);

    var entries = map.get(state.getClass()).entrySet();
    for (var entry : entries) {
      Transition transition = entry.getValue();
      if (!transition.canTransition(query, index)) continue;
      Class<? extends State> stateClazz = entry.getKey();
      return StateFactory.create(stateClazz);
    }
    if (state instanceof StartState) throw new IllegalStateException("nowhere to go");
    return StateFactory.create(StartState.class);
  }
}
