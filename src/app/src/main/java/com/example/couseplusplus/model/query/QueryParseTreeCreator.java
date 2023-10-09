package com.example.couseplusplus.model.query;

import com.example.couseplusplus.model.query.parser.ParseTree;
import com.example.couseplusplus.model.query.parser.Parser;
import com.example.couseplusplus.model.query.tokenizer.Tokenizer;
import java.util.function.Function;

/**
 * @author Yuki Misumi (u7582380)
 */
public class QueryParseTreeCreator {
  Function<Query, Tokenizer> tokenizerCreator;
  Function<Tokenizer, Parser> parserCreator;

  public QueryParseTreeCreator(
      Function<Query, Tokenizer> tokenizerCreator, Function<Tokenizer, Parser> parserCreator) {
    this.tokenizerCreator = tokenizerCreator;
    this.parserCreator = parserCreator;
  }

  public ParseTree create(Query query) {
    Tokenizer tokenizer = tokenizerCreator.apply(query);
    Parser parser = parserCreator.apply(tokenizer);
    return parser.parse();
  }
}
