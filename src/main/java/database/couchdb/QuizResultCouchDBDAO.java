package database.couchdb;

/** Author Richard Knol
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Quiz;
import model.QuizResult;
import model.User;
import view.Main;

import java.util.List;

public class QuizResultCouchDBDAO {

    private CouchDBaccess db;
    private Gson gson;

    public QuizResultCouchDBDAO(CouchDBaccess db) {
        super();
        this.db = db;
        gson = new Gson();
    }

    // todo: Json string maken van een eenvoudig object
    public void runTest() {
        User user1 = new User("Student", "Richard", "", "Knol");
        user1.setWachtwoord("hoi");
        Gson gson = new Gson();
        String jsonstring = gson.toJson(user1);
        String gebruiker = gson.toJson(user1);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
        String doc_iD = db.saveDocument(jsonObject);
        System.out.println("Info als Json: ");
        System.out.println(gebruiker);
        System.out.println(doc_iD);
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

    // todo: mogelijke oplossing voor opslaan enkele poging in Json object?
//    public String saveSingleQuizResult(QuizResult quizresult) {
//        String jsonstring = gson.toJson(quizresult);
//        System.out.println(jsonstring);
//        JsonParser parser = new JsonParser();
//        JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
//        String doc_Id = db.saveDocument(jsonObject);
//        return doc_Id;
//    }

}
