package com.guoquan.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDBConnector {

	public static MongoClient getConnection(String uri) {
		MongoClientURI connectionString;
		if (uri != null && uri.isEmpty() == false) {
			connectionString = new MongoClientURI(uri);
		} else {
			connectionString = new MongoClientURI("mongodb://localhost:27017");
		}

		return new MongoClient(connectionString);
	}

}
