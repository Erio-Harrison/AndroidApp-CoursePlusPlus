package com.example.couseplusplus.model.query;

import com.example.couseplusplus.model.query.parser.ParseTree;
import com.example.couseplusplus.model.query.parser.Parser;
import com.example.couseplusplus.model.query.tokenizer.Tokenizer;
import java.util.function.Function;

/**
 * @author Yuki Misumi (u7582380)
 */
public class QueryParseTreeCreator {
  Function<Tokenizer, Parser> parserCreator;

  public QueryParseTreeCreator(Function<Tokenizer, Parser> parserCreator) {
    this.parserCreator = parserCreator;
  }

  public ParseTree create(Query query) {
    Tokenizer tokenizer = new Tokenizer(query);
    Parser parser = parserCreator.apply(tokenizer);
    return parser.parse();
  }
}
