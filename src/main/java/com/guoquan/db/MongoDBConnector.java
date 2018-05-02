package com.guoquan.db;

import java.util.ArrayList;

import org.bson.Document;

import com.guoquan.bean.Product;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnector {
	private MongoClient mongoClient;
	private MongoDatabase mongoDBInstance;
	private ArrayList<Product> products;

	public  ArrayList<Product> getProducts(String database, String collection) {
		MongoCollection<Document> collections = null;
		if (mongoClient == null) {
			MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
			mongoClient = new MongoClient(connectionString);
		}
		try {
			System.out.println("Client is ready!");
			mongoDBInstance = mongoClient.getDatabase(database);
			collections = mongoDBInstance.getCollection(collection);
			products = new ArrayList<Product>((int) collections.count());
			MongoCursor<Document> cursor = collections.find().iterator();
			try {
				while (cursor.hasNext()) {
					Product product = new Product();
					Document document = cursor.next();

					product.setId(document.getObjectId("_id").toString());
					product.setName(document.getString("name"));
					product.setCategory(document.getString("category"));
					products.add(product);
				}
			} finally {
				cursor.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			mongoClient.close();
			System.out.println("Client is closed!");
		}
		return products;
	}

}
