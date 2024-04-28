package com.morse;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MorseCodeEncoder {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "MorseCode";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public String encode(String stateStr) {
        Document finds;
        StringBuilder statementStrBuilder = new StringBuilder();
        stateStr = stateStr.toUpperCase();
        try {
            
       if ( !connectToDatabase()){
        return"Error: Unable to connect to the database";
       }
        for (char c : stateStr.toCharArray()) {
            if (c == ' ')
                statementStrBuilder.append("/ ");
            else {
                finds = (database.getCollection("morse")).find(Filters.eq("character", c)).first();
                statementStrBuilder.append(finds.getString("morse_code"));
                statementStrBuilder.append(" ");
            }
        }
    } catch (Exception e) {
        System.out.println("Error: In Encoder"+e.getMessage());
    }
        return statementStrBuilder.toString();
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
