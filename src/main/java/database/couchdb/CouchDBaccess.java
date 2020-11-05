package database.couchdb;

import com.google.gson.JsonObject;
import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Response;

/**
 * Author Richard Knol
 */

public class CouchDBaccess {

    // client is een client object o.b.v. de properties van hierboven (lightcouch library)
    private CouchDbClient client;

    public void setupConnection() {
        // properties voor couchdb waarden meegeven. De .set names spreken voor zich
        CouchDbProperties properties = new CouchDbProperties();
        properties.setDbName("quizmaster");
        properties.setCreateDbIfNotExist(true);
        properties.setHost("localhost");
        properties.setPort(5984);
        properties.setProtocol("http");
        properties.setUsername("admin");
        properties.setPassword("Hallotest5@");

        client = new CouchDbClient(properties); // de properties worden hier in het object client gestopt
    }

    // Toegevoegd door Wendy, zodat dit niet in Main hoeft en pas als de verbinding nodig is
    public void openConnection() {
        try {
            this.setupConnection();
            System.out.println("Connection open");
        }
        catch (Exception e) {
            System.out.println("\nEr is iets fout gegaan\n");
            e.printStackTrace();
        }
    }

    public String saveDocument(JsonObject document) { // waarsch nodig voor opslaan JSON objecten
        Response response = client.save(document);
        return response.getId();
    }

    public CouchDbClient getClient() {
        return client;
    }

}
