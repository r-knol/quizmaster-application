package database.couchdb;

/** Author Richard Knol
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.QuizResult;
import model.User;

public class QuizResultCouchDBDAO {

    private CouchDBaccess db;
    private Gson gson;

    public QuizResultCouchDBDAO(CouchDBaccess db) {
        super();
        this.db = db;
        gson = new Gson();
    }

    public String saveQuizResult(QuizResult quizResult) {
        String jsonstring = gson.toJson(quizResult);
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonstring).getAsJsonObject();
        String doc_Id = db.saveDocument(jsonObject);
        return doc_Id;
    }
    // Json string maken van quizResult
    QuizResult eenResult = new QuizResult(student, quiz, datum, vraagAntwoorParen, vraag);
    Gson gson = new Gson();
    String resultaatJson = gson.toJson(eenResult);
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
