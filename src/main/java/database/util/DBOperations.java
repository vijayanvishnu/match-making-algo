package database.util;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import entity.util.Game;
import entity.util.MyException;
import entity.util.User;

public class DBOperations {
	DatabaseConnection object = new DatabaseConnection();
	public boolean userPresense(String username,String password) throws MyException{
		boolean res = false;
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			// get collection for mongo database
			Bson filter = Filters.and(Filters.eq("user_name", username),Filters.eq("password", password));
			// filter document is created and search for the presence of user
			res = coll.find(filter).cursor().hasNext();
			return res;
		}catch(Exception e) {
			throw new MyException("Error on logging in - mismatch information.");
		}
	}
	public boolean addGame(Game game) throws MyException {
		try {
			User one = game.getPlayerA();
			User two = game.getPlayerB();
			User winner = game.getWinner();
			MongoCollection<Document> coll = object.getCollection("games");
			Document document = new Document("player_A",one.getUsername());
			document.append("player_B", two.getUsername());
			if(winner != null)
				document.append("winner", winner.getUsername());
			else 
				document.append("winner", "draw");
			coll.insertOne(document);
			return true;
		}catch(Exception e) {
			throw new MyException("Error on Match Updation.");
		}
	}
	public Document userPage(String username,String password)throws Exception{
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			// map is the filter reference get passed to the filter the content
			Bson filter = Filters.and(Filters.eq("user_name", username),Filters.eq("password", password));
			// filter document is created and search for the presence of user
			return coll.find(filter).first();
		}catch(Exception e) {
			throw new MyException("Error on fetchhing user information.");
		}
	}
	public Document addUser(User user) throws MyException{
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			Document document = new Document();
			document.append("user_name", user.getUsername());
			document.append("password", user.getPassword());
			document.append("rating", user.getRating());
			document.append("email", user.getEmail());
			document.append("display_name", user.getName());
			coll.insertOne(document);
			return (new DBOperations()).userPage(user.getUsername(), user.getPassword());
		}catch(Exception e) {
			throw new MyException("Error on fetching user information.");
		}
	}
	public MongoIterable<Document> getUsers() throws MyException{
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			Document project = new Document();
			project.append("_id", 0);
			return coll.find().projection(project);
		}catch(Exception e) {
			throw new MyException("Error on fetching user information.");
		}
	}
	public MongoIterable<Document> getUserMatches(User user)throws MyException{
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			long givenRating = user.getRating();
			List<Document> filter = Arrays.asList(
			    new Document("$project",
			        new Document("user_name", 1L)
			            .append("rating", 1L)
			            .append("rating_difference",
			                new Document("$abs", new Document("$subtract", Arrays.asList("$rating", givenRating))))),
			    new Document("$sort",
			        new Document("rating_difference", 1L)),
			    new Document("$limit", 10L)
			);
			return coll.aggregate(filter);
		}catch(Exception e) {
			throw new MyException("Error on making agggregation information.");
		}
	}
	public Document getUser(String username) throws MyException{
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			Document filter = new Document("user_name",username);
			Document project = new Document("_id", 0);
			project.append("user_name",1);
			project.append("rating", 1);
			project.append("email",1);
			project.append("display_name", 1);
			return coll.find(filter).projection(project).first();
		}catch(Exception e) {
			throw new MyException("Error on fetching user information.");
		}
	}
	public boolean usernameExists(String username)throws MyException {
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			return coll.find(new Document("user_name",username)).cursor().hasNext();
		}catch(Exception e) {
			throw new MyException("Error on fetching user information.");
		}
	}
	public boolean emailExists(String username) throws MyException {
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			return coll.find(new Document("email",username)).cursor().hasNext();
		}catch(Exception e) {
			throw new MyException("Error on fetching user information.");
		}
	}
	public Document userForMatch(String username) throws MyException {
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			Document projection = new Document("user_name",1);
			projection.append("rating", 1);
			Document document = coll.find(new Document("user_name",username)).projection(projection).first();
			return document;
		}catch(Exception e) {
			e.printStackTrace();
			throw new MyException("Error on fetching user information.");
		}
	}
	public boolean updateRating(User user)throws MyException {
		try {
			MongoCollection<Document> coll = object.getCollection("users");
			String username = user.getUsername();
			int rating = user.getRating();
			Bson filter = Filters.eq("user_name" , username);
			Bson update = Updates.set("rating",rating);
			coll.updateOne(filter,update);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new MyException("Error on fetching user information.");
		}
	}
}
