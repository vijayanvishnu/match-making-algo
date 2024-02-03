package application.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoIterable;

import algorithm.util.PairingClass;
import entity.util.MyException;
import entity.util.User;

public class Transactions {
	public static User getUserAsAdmin(Document user) throws MyException{
		try {
			String username = user.getString("user_name");
			String password = user.getString("password");
			int rating = user.getInteger("rating");
			String name = user.getString("display_name");
			String email = user.getString("email");
			return new User(name,username,password,email,rating);
		}catch(Exception e) {
			throw new MyException("Error on fetching data form document.");
		}
	}
	public static User getUser(Document document) throws MyException{
		try {
			User user = getUserAsAdmin(document);
			user.display();
			user.setPassword(null);
			return user;
		}catch(Exception e) {
			throw new MyException("Error on fetching data form document.");
		}
	}
	public static int getRating(Document document) throws MyException {
		try {
			int rating = document.getInteger("rating");
			return rating;
		}catch(Exception e) {
			throw new MyException("Error on fetching data form document.");
		}
	}
	public static User userForMatch(Document document) throws MyException{
		try {
			String username = document.getString("user_name");
			int rating = document.getInteger("rating");
			return new User(username,rating);
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("Error on fetching data form document.");
		}
	}
	public static User userForMatch(User user) {
		return new User(user.getUsername(),user.getRating());
	}
	public List<PairingClass> getUsersPairing(MongoIterable<Document> docs)throws MyException{
		try {
			List<PairingClass> res = new ArrayList<>();
			docs.forEach( x -> {
				long l = x.getLong("rating_difference");
				res.add(new PairingClass(x.getString("user_name"),
						x.getInteger("rating"),
						(int)l));
			});
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("Error on list creation or fetcing data");
		}
	}
}
