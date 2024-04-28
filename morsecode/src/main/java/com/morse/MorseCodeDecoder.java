package com.morse;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.event.ConnectionCheckedInEvent;

public class MorseCodeDecoder {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "MorseCode";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public String decode(String MorseCodes) {

        Document finds;
        StringBuilder strBuilder = new StringBuilder();
        try {
            if (!connectToDatabase()) {
                return "Error: Unable to connect to the database";
            }
            String[] morsearr = MorseCodes.split(" ");

            for (int i = 0; i < morsearr.length; i++) {
                System.out.println(morsearr[i]);
                if (morsearr[i].equals("/")) {
                    strBuilder.append(" ");
                } else {
                    finds = (database.getCollection("morse")).find(Filters.eq("morse_code", morsearr[i])).first();
                    if (finds != null)
                        strBuilder.append(finds.getString("character"));
                    else
                        strBuilder.append("?");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: In Decoder:" + e.getMessage());
        }
        return strBuilder.toString();

    }

    private boolean connectToDatabase() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Connected to MongoDB successfully!");
            return true;
        } catch (Exception e) {
            System.err.println("Error connecting to MongoDB: " + e);
            return false;
        }
    }
}
