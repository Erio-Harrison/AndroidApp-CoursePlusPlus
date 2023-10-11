package com.example.couseplusplus.data.comment.findingstrategy;

import com.example.couseplusplus.data.comment.CommentCache;
import com.example.couseplusplus.data.invertedindex.InvertedIndex;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.parser.TextNode;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yuki Misumi (u7582380)
 */
public class TextBased implements CommentFindingStrategy {
  @Override
  public List<Comment> find(
      TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache) {
    if (operatorType != TokenType.Like) throw new UnsupportedOperationException(operatorType);

    TextNode textNode = (TextNode) terminalNode;
    String key = textNode.text();

    InvertedIndex<String, Comment> textIndex = commentCache.textIndex();
    return words(key).stream()
        .map(textIndex::get)
        .flatMap(Collection::stream)
        .distinct()
        .collect(Collectors.toList());
  }

  List<String> words(String key) {
    return Arrays.asList(key.split(Comment.WordSplitRegex));
  }
}
