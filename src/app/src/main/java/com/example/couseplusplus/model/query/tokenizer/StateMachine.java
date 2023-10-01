package com.example.couseplusplus.model.query.tokenizer;

import com.example.couseplusplus.model.query.Query;
import com.example.couseplusplus.model.query.tokenizer.state.AndState;
import com.example.couseplusplus.model.query.tokenizer.state.DateTimeState;
import com.example.couseplusplus.model.query.tokenizer.state.EndState;
import com.example.couseplusplus.model.query.tokenizer.state.EnrolState;
import com.example.couseplusplus.model.query.tokenizer.state.EqualState;
import com.example.couseplusplus.model.query.tokenizer.state.HelpfulState;
import com.example.couseplusplus.model.query.tokenizer.state.LeftParenthesisState;
import com.example.couseplusplus.model.query.tokenizer.state.LessThanState;
import com.example.couseplusplus.model.query.tokenizer.state.LikeState;
import com.example.couseplusplus.model.query.tokenizer.state.MoreThanState;
import com.example.couseplusplus.model.query.tokenizer.state.OrState;
import com.example.couseplusplus.model.query.tokenizer.state.PostedState;
import com.example.couseplusplus.model.query.tokenizer.state.RightParenthesisState;
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
import java.util.Optional;

/**
 * @author Yuki Misumi (u7582380)
 */
public class StateMachine {
  static Map<Class<? extends State>, Map<Class<? extends State>, Transition>> map;

  static {
    map = new HashMap<>();

    Map<Class<? extends State>, Transition> startTo = new HashMap<>();
    startTo.put(WhitespaceState.class, Query::isSpace);

    startTo.put(HelpfulState.class, (query, index) -> query.is(index, 'h'));
    startTo.put(EnrolState.class, (query, index) -> query.is(index, 'e'));
    startTo.put(PostedState.class, (query, index) -> query.is(index, 'p'));
    startTo.put(TextState.class, (query, index) -> query.is(index, 't'));

    startTo.put(YearOrNumberState.class, Query::isDigit);
    startTo.put(TextValueState.class, Query::isDoubleQuote);

    startTo.put(EqualState.class, (query, index) -> query.is(index, '='));
    startTo.put(LessThanState.class, (query, index) -> query.is(index, '<'));
    startTo.put(MoreThanState.class, (query, index) -> query.is(index, '>'));
    startTo.put(LikeState.class, (query, index) -> query.is(index, '~'));

    startTo.put(AndState.class, (query, index) -> query.is(index, '&'));
    startTo.put(OrState.class, (query, index) -> query.is(index, '|'));

    startTo.put(LeftParenthesisState.class, ((query, index) -> query.is(index, '(')));
    startTo.put(RightParenthesisState.class, ((query, index) -> query.is(index, ')')));
    map.put(StartState.class, startTo);

    map.put(
        YearOrNumberState.class,
        Map.of(DateTimeState.class, Query::isHyphen, SemesterState.class, Query::isS));
  }

  State state;

  public StateMachine() {
    this.state = StartState.getInstance();
  }

  public ProcessResult process(Query query, int index) {
    ProcessResult result = state.process(query, index);
    state = getNextState(query, result.index());
    return result;
  }

  State getNextState(Query query, Integer index) {
    if (isNotRunning() || query.isOutOfRange(index)) return StateFactory.create(EndState.class);

    return findStateToTransition(query, index, state.getClass())
        .orElseGet(
            () ->
                findStateToTransition(query, index, StartState.class)
                    .orElseThrow(NoTransitionException::new));
  }

  Optional<State> findStateToTransition(Query query, int index, Class<? extends State> stateClass) {
    if (!map.containsKey(stateClass)) return Optional.empty();

    var entries = map.get(stateClass).entrySet();
    for (var entry : entries) {
      Transition transition = entry.getValue();
      if (!transition.canTransition(query, index)) continue;
      Class<? extends State> stateClazz = entry.getKey();
      return Optional.of(StateFactory.create(stateClazz));
    }
    return Optional.empty();
  }

  public boolean isRunning() {
    return !isNotRunning();
  }

  public boolean isNotRunning() {
    return state instanceof EndState;
  }
}
