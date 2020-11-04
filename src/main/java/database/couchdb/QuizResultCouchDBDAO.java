package database.couchdb;

/**
 * Author Richard Knol, Wendy Ellens
 */

import com.google.gson.*;
import model.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QuizResultCouchDBDAO {

    private CouchDBaccess db;
    private Gson gson;

    public QuizResultCouchDBDAO(CouchDBaccess db) {
        super();
        this.db = db;
        gson = new Gson();
    }

    public String saveQuizResult(QuizResult quizResult) {
        // Om datum + tijd om te zetten in een Json-string
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        // quizResult opslaan in de database
        String jsonstring = gson.toJson(quizResult);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
        String doc_Id = db.saveDocument(jsonObject);

        return doc_Id;
    }

    // Methode om het de resultaten van de quiz uit couchDB te halen @author Olaf van der Kaaij
    public QuizResult getQuizResult (User student, Quiz quiz) {
        QuizResult resultaat = null;
        List<JsonObject> alleResults = db.getClient().view("_all_docs").includeDocs(true).query(JsonObject.class);
        for (JsonObject json : alleResults) {
            resultaat = gson.fromJson(json, QuizResult.class);
            if ((resultaat.getStudent().equals(student)) && (resultaat.getQuiz().equals(quiz))) {
                return resultaat;
            }
        }
        return resultaat;
    }

//    public void runTest() {
//
//        // quizResult maken als test
//        QuizDAO quizDAO = new QuizDAO(Main.getDBaccess());
//        User user1 = new User("Student", "Richard", "", "Knol");
//        user1.setWachtwoord("hoi");
//        Quiz quiz = quizDAO.getOneById(1);
//        Question question1 = new Question(1, quiz, "Vraag",
//                "Juist", "Onjuist1", "Onjuist2", "Onjuist3");
//        Question question2 = new Question(2, quiz, "Vraag2",
//                "Juist", "Onjuist1", "Onjuist2", "Onjuist3");
//        List<QuestionAnswerPair> vraagAntwoordParen = new ArrayList<QuestionAnswerPair>();
//        vraagAntwoordParen.add(new QuestionAnswerPair(question1, "Juist"));
//        vraagAntwoordParen.add(new QuestionAnswerPair(question2, "Onjuist1"));
//        QuizResult quizResult = new QuizResult(user1, quiz, LocalDateTime.now(), vraagAntwoordParen);
//
//        // Om datum + tijd om te zetten in een Json-string
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
//        Gson gson = gsonBuilder.setPrettyPrinting().create();
//
//        // quizResult opslaan in de database
//        String jsonstring = gson.toJson(quizResult);
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
//        String doc_iD = db.saveDocument(jsonObject);
//        System.out.println("Info als Json: ");
//        System.out.println(jsonstring);
//        System.out.println(doc_iD);
//    }
}

// Om de datum + tijd om te zetten in een Json-string
class LocalDateTimeSerializer implements JsonSerializer < LocalDateTime > {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}