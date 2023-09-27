package com.example.couseplusplus.model.query;

/**
 * @author Yuki Misumi (u7582380) Parses the following grammar <query> ::= <expression> |
 *     <expression> <condition> <query> <expression> ::= helpful <operator> <number> | enrol
 *     <operator> <enroldate> | posted <operator> <date> | text <fuzzy> <string> | ( <query> )
 */
public class Parser {
  Tokenizer tokenizer;
  Token currentToken;

  public Parser(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
    currentToken = tokenizer.getNextToken();
  }

  void take(TokenType tokenType) {
    if (currentToken.tokenType() != tokenType) throw new IllegalSyntaxException();
    currentToken = tokenizer.getNextToken();
  }

  void takeComparable() {
    if (!currentToken.tokenType().isComparable()) throw new IllegalSyntaxException();
    currentToken = tokenizer.getNextToken();
  }

  void takeCondition() {
    TokenType tokenType = currentToken.tokenType;
    if (tokenType == TokenType.And) {
      take(TokenType.And);
      return;
    }
    take(TokenType.Or);
  }

  boolean isCondition(Token token) {
    TokenType tokenType = token.tokenType();
    return tokenType == TokenType.And || tokenType == TokenType.Or;
  }

  public Node query() {
    Node expression = expression();
    if (!tokenizer.hasNext() || !isCondition(currentToken)) return expression;

    Token token = currentToken;
    takeCondition();
    Node query = query();
    return new ConditionalNode(expression, token, query);
  }

  public Node expression() {
    TokenType tokenType = currentToken.tokenType();
    if (tokenType == TokenType.Helpful) {
      Node helpful = id();
      Token token = currentToken;
      takeComparable();
      return new ComparisonNode(helpful, token, number());
    }
    if (tokenType == TokenType.Enrol) {
      Node enrol = id();
      Token token = currentToken;
      takeComparable();
      return new ComparisonNode(enrol, token, enrolDate());
    }
    if (tokenType == TokenType.Posted) {
      Node posted = id();
      Token token = currentToken;
      takeComparable();
      return new ComparisonNode(posted, token, datetime());
    }
    if (tokenType == TokenType.Text) {
      Node text = id();
      Token token = currentToken;
      take(TokenType.Like);
      return new ComparisonNode(text, token, textString());
    }
    take(TokenType.LeftParenthesis);
    Node statement = query();
    take(TokenType.RightParenthesis);
    return statement;
  }

  public Node id() {
    Token token = currentToken;
    TokenType tokenType = token.tokenType();
    if (tokenType == TokenType.Helpful) take(TokenType.Helpful);
    if (tokenType == TokenType.Enrol) take(TokenType.Enrol);
    if (tokenType == TokenType.Posted) take(TokenType.Posted);
    if (tokenType == TokenType.Text) take(TokenType.Text);
    return new IdNode(token);
  }

  public Node number() {
    Token token = currentToken;
    take(TokenType.Integer);
    return new NumberNode(token);
  }

  public Node enrolDate() {
    Token token = currentToken;
    take(TokenType.EnrolDate);
    return new EnrolDateNode(token);
  }

  public Node datetime() {
    Token token = currentToken;
    take(TokenType.DateTime);
    return new DateTimeNode(token);
  }

  public Node textString() {
    Token token = currentToken;
    take(TokenType.TextString);
    return new TextNode(token);
  }

  public ParseTree parse() {
    return new ParseTree(query());
  }
}
