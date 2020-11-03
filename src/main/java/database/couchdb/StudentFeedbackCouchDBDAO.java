package database.couchdb;

import com.google.gson.Gson;

public class StudentFeedbackCouchDBDAO {

    private CouchDBaccess db;
    private Gson gson;

    public StudentFeedbackCouchDBDAO(CouchDBaccess db) {
        super();
        this.db = db;
        gson = new Gson();
    }
}
