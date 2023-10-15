package com.example.couseplusplus.simulation;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.comment.Comment;
import com.example.couseplusplus.model.comment.CommentRepository;
import com.example.couseplusplus.model.query.parser.ParseTree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

public class AddHelpfulnessHandlerTest {
    private AddHelpfulnessHandler addHelpfulnessHandler;
    private CommentRepository commentRepository;

    private boolean addHelpfulnessCalled = false;
    @Before
    public void setup(){
        commentRepository = new CommentRepository() {
            @Override
            public void listenChange(String courseCode, Consumer<List<Comment>> listener) {

            }

            @Override
            public List<Comment> getAll(String courseCode) {
                return null;
            }

            @Override
            public List<Comment> findAll(String courseCode, ParseTree parseTree) {
                return null;
            }

            @Override
            public void addHelpfulness(String courseCode, String commentId, int helpfulness) {
                addHelpfulnessCalled = true;
            }

            @Override
            public void addComment(String courseCode, Comment comment) {

            }
        };
        addHelpfulnessHandler = new AddHelpfulnessHandler(commentRepository);
    }
    @Test
    public void testAddHelpfulnessHandler() {
        // Simulate a helpfulness action
        List<String> simulatedArgs = List.of("ACST3001", "commentSimulation1", "3");
        addHelpfulnessHandler.on(ActionType.addHelpfulness, simulatedArgs);

        // Check if the helpfulness was added
        assertTrue(addHelpfulnessCalled);
    }



}