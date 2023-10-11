package com.example.couseplusplus.data.comment.findingstrategy;

import com.example.couseplusplus.data.comment.CommentCache;
import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.query.parser.TerminalNode;
import com.example.couseplusplus.model.query.tokenizer.TokenType;
import java.util.List;

/**
 * @author Yuki Misumi (u7582380)
 */
public interface CommentFindingStrategy {
  List<Comment> find(TokenType operatorType, TerminalNode terminalNode, CommentCache commentCache);
}
