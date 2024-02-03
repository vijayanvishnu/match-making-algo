package database.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import entity.util.MyException;

public class DatabaseConnection {
	private MongoDatabase getDatabase() throws MyException {
		try {
			MongoClient client = null;
			MongoDatabase db = null;
			client = MongoClients.create("mongodb+srv://newuser-362:password362@cluster0.ksdjsrx.mongodb.net/");
			// connection between client is created here
			db = client.getDatabase("project");
			// project database is accessed here
			return db;
			// return the mongo database
		}catch(Exception e) {
			throw new MyException("Error on database connection.");
		}
	}
	@SuppressWarnings("rawtypes")
	public MongoCollection getCollection(String collection) {
		MongoCollection coll = null;
		DatabaseConnection object = new DatabaseConnection();
		// object for this class is created to get database connetion
		try {
			MongoDatabase db = object.getDatabase();
			coll = db.getCollection(collection);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return coll;
		// return mongo collection for the give collection
	}
}
