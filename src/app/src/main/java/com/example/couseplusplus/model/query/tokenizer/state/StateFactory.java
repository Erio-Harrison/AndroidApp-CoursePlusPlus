package com.example.couseplusplus.model.query.tokenizer.state;

/**
 * @author Yuki Misumi (u7582380)
 */
public class StateFactory {
  public static State create(Class<? extends State> clazz) {
    if (clazz == WhitespaceState.class) return new WhitespaceState();
    if (clazz == StartState.class) return StartState.getInstance();
    if (clazz == YearOrNumberState.class) return new YearOrNumberState();
    if (clazz == DateTimeState.class) return new DateTimeState();
    if (clazz == SemesterState.class) return new SemesterState();
    if (clazz == TextValueState.class) return new TextValueState();
    if (clazz == EqualState.class) return new EqualState();
    if (clazz == LessThanState.class) return new LessThanState();
    if (clazz == MoreThanState.class) return new MoreThanState();
    if (clazz == AndState.class) return new AndState();
    if (clazz == OrState.class) return new OrState();
    if (clazz == LikeState.class) return new LikeState();
    if (clazz == HelpfulState.class) return new HelpfulState();
    if (clazz == EnrolState.class) return new EnrolState();
    if (clazz == PostedState.class) return new PostedState();
    if (clazz == TextState.class) return new TextState();
    if (clazz == LeftParenthesisState.class) return new LeftParenthesisState();
    if (clazz == RightParenthesisState.class) return new RightParenthesisState();
    throw new IllegalArgumentException(String.format("%s is not supported", clazz));
  }
}
