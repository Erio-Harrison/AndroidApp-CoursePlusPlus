package com.example.couseplusplus.simulation;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.query.parser.ParseTree;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AddCommentHandlerTest {

    private AddCommentHandler addCommentHandler;
    private CommentRepository commentRepository;
    private boolean addCommentCalled = false;

    @Before
    public void setUp() {

        commentRepository = new CommentRepository() {
            @Override
            public void listenChange(String courseCode, Consumer<List<Comment>> listener) {

            }

            @Override
            public List<Comment> getAll(String courseCode) {
                return new ArrayList<>();
            }

            @Override
            public List<Comment> findAll(String courseCode, ParseTree parseTree) {
                return new ArrayList<>();
            }

            @Override
            public void addHelpfulness(String courseCode, String commentId, int helpfulness) {

            }

            @Override
            public void addComment(String courseCode, Comment comment) {
                addCommentCalled = true;
            }
        };
        addCommentHandler = new AddCommentHandler(commentRepository);
    }

    @Test
    public void testAddCommentHandler() {
        // Simulate a comment action
        List<String> simulatedArgs = List.of("commentSimulation1", "ACST3001", "2021", "1", "This course is great!", "3", "15-07-2023 14:25:45");
        addCommentHandler.on(ActionType.addComment, simulatedArgs);

        // Check if the comment was added
        assertTrue(addCommentCalled);
    }
}