//package database.couchdb;
//
// ** Author Richard Knol
// */
//
//public class CouchDBQuizmasterLauncher {
//
//    private static CouchDBaccess db;
//    private static QuizResultCouchDBDAO quizResultCouchDBDAO;
//
//    public CouchDBQuizmasterLauncher() {
//        super();
//        db = new CouchDBaccess();
//        quizResultCouchDBDAO = new QuizResultCouchDBDAO(db);
//    }
//
//    /* nodig om connectie te leggen zonder hele Application te starten
//    misschien later in Main verwerken op één of andere manier*/
//    public static void main(String[] args) {
//        CouchDBQuizmasterLauncher myself = new CouchDBQuizmasterLauncher();
//        myself.run();
//    }
//
//    // setupconnection CouchDB
//    public void run() {
//        try {
//            db.setupConnection();
//            System.out.println("Connection open");
//        } catch (Exception e) {
//            System.out.println("\nEr is iets fout gegaan");
//            e.printStackTrace();
//        }
//    }
//
//}
