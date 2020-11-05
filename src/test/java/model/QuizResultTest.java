package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Wendy Ellens
 * Unit testen voor klasse QuizResult
 */

class QuizResultTest {

    private User user = new User(0, "", "", "", "", "", "");
    // Quiz waarvoor minstens 1 van de 2 vragen goed moet zijn
    private Quiz quiz = new Quiz(new Course(), "", 2,1);
    private Question question = new Question("", "1", "2", "3", "4");
    // Juist antwoord
    private QuestionAnswerPair questionAnswerPair1 = new QuestionAnswerPair(question, "1");
    // Onjuist antwoord
    private QuestionAnswerPair questionAnswerPair2 = new QuestionAnswerPair(question, "2");
    List<QuestionAnswerPair> questionAnswerPairList = new ArrayList<>();

    // Tests voor methode bepaalBehaald
    @Test
    void testBehaald() { // Quiz met twee juiste antwoorden
        questionAnswerPairList.add(questionAnswerPair1);
        questionAnswerPairList.add(questionAnswerPair1);
        QuizResult quizResult = new QuizResult(user, quiz, LocalDateTime.now(), questionAnswerPairList);
        assertTrue(quizResult.getBehaald());
    }
    @Test
    void testBehaald2() { // Quiz met een juist antwoord
        questionAnswerPairList.add(questionAnswerPair1);
        questionAnswerPairList.add(questionAnswerPair2);
        QuizResult quizResult = new QuizResult(user, quiz, LocalDateTime.now(), questionAnswerPairList);
        assertTrue(quizResult.getBehaald());
    }
    @Test
    void testBehaald3() { // Quiz zonder juiste antwoorden
        questionAnswerPairList.add(questionAnswerPair2);
        questionAnswerPairList.add(questionAnswerPair2);
        QuizResult quizResult = new QuizResult(user, quiz, LocalDateTime.now(), questionAnswerPairList);
        assertFalse(quizResult.getBehaald());
    }
}