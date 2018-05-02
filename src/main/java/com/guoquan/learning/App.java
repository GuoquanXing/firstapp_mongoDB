package com.guoquan.learning;

import org.bson.Document;

import com.guoquan.db.MongoDBConnector;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        App app = new App();
        MongoCollection<Document> documents = app.getCollection(MongoDBConnector.getConnection(""), "products");
        
        MongoCursor<Document> cursor = documents.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
    
    public MongoCollection<Document> getCollection(MongoClient client, String collection){
    	MongoCollection<Document> collections;
    	collections = client.getDatabase("webshop").getCollection(collection);
    	return collections;
    }
    
}
