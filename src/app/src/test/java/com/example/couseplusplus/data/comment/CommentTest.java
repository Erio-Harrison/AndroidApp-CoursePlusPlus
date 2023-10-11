package com.example.couseplusplus.data.comment;

import static org.junit.Assert.*;

import com.example.couseplusplus.model.comment.Comment;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;

public class CommentTest {
  @Test
  public void testWords() {
    var zeroWordComment = new Comment("1", "C", 2000, 1, "", 0, LocalDateTime.MIN);
    List<String> zeroWord = zeroWordComment.words();
    assertTrue(zeroWord.isEmpty());

    var whitespaceComment = new Comment("1", "C", 2000, 1, "   ", 0, LocalDateTime.MIN);
    List<String> noWord = whitespaceComment.words();
    assertTrue(noWord.isEmpty());

    var oneWordComment = new Comment("1", "C", 2000, 1, "lorem", 0, LocalDateTime.MIN);
    List<String> oneWord = oneWordComment.words();
    assertEquals(1, oneWord.size());
    assertEquals("lorem", oneWord.get(0));

    var twoWordsComment = new Comment("2", "C", 2000, 1, "lorem ipsum", 0, LocalDateTime.MIN);
    List<String> twoWords = twoWordsComment.words();
    assertEquals(2, twoWords.size());
    assertEquals("lorem", twoWords.get(0));
    assertEquals("ipsum", twoWords.get(1));

    var twoWordsWithPeriodComment =
        new Comment("2", "C", 2000, 1, "lorem ipsum.", 0, LocalDateTime.MIN);
    List<String> twoWords2 = twoWordsWithPeriodComment.words();
    assertEquals(2, twoWords2.size());
    assertEquals("lorem", twoWords2.get(0));
    assertEquals("ipsum", twoWords2.get(1));

    var twoWordsWithQuestionComment =
        new Comment("2", "C", 2000, 1, "lorem ipsum?", 0, LocalDateTime.MIN);
    List<String> twoWords3 = twoWordsWithQuestionComment.words();
    assertEquals(2, twoWords3.size());
    assertEquals("lorem", twoWords3.get(0));
    assertEquals("ipsum", twoWords3.get(1));

    var twoWordsWithExComment =
        new Comment("2", "C", 2000, 1, "lorem ipsum!", 0, LocalDateTime.MIN);
    List<String> twoWords4 = twoWordsWithExComment.words();
    assertEquals(2, twoWords4.size());
    assertEquals("lorem", twoWords4.get(0));
    assertEquals("ipsum", twoWords4.get(1));
  }
}
