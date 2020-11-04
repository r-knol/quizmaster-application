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
import java.util.Locale;

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
    public QuizResult getQuizResult(User student, Quiz quiz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        QuizResult resultaat = null;
        List<JsonObject> alleResults = db.getClient().view("_all_docs").includeDocs(true).query(JsonObject.class);
        for (JsonObject json : alleResults) {
            resultaat = gson.fromJson(json, QuizResult.class);
            //todo: check of er wel quiz en student is
            if ((resultaat.getStudent().equals(student)) && (resultaat.getQuiz().equals(quiz))) {
                return resultaat;
            }
        }
        return resultaat;
    }
}

// Om de datum + tijd om te zetten in een Json-string
class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}

class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withLocale(Locale.ENGLISH));
    }
}
