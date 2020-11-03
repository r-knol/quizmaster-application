package database.couchdb;

/** Author Richard Knol
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.QuizResult;

public class QuizResultCouchDBDAO {

    private CouchDBaccess db;
    private Gson gson;

    public QuizResultCouchDBDAO(CouchDBaccess db) {
        super();
        this.db = db;
        gson = new Gson();
    }

    // todo: Json string maken van een eenvoudig object
//    QuizResult eenResult = new QuizResult(quizID, gebruikerID, pogingnr, resultaat, datum);
//    Gson gson = new Gson();
//    String resultaatJson = gson.toJson(eenResult);

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
