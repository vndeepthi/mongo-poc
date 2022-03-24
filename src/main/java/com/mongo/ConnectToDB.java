package com.mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;

public class ConnectToDB {

    public static void main( String args[] ) {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "mydb",
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("mydb");
        System.out.println("Credentials ::"+ credential);

        // Creating a collection
       // database.createCollection("sampleCollection");
        System.out.println("Collection created successfully");
        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("sampleCollection");
        System.out.println("Collection sampleCollection selected successfully");

        Document document = new Document("title", "Home")
                .append("Adults", 2)
                .append("kids", 2)
                .append("address", "29 east port drive")
                .append("names", asList("Sandy", "Deepu", "Siddhi", "Myra"));

       // collection.insertOne(document);
      //  collection.findOneAndUpdate(Filters.eq("title", "Home"), set("title", "MyHome"));
        collection.find().iterator().forEachRemaining(document1 -> {
            document1.putIfAbsent("pets", "yes");
        });
        collection.find().iterator().forEachRemaining(System.out::println);
    }
}
