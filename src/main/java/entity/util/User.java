package entity.util;

public class User {
	private String id;
	private String name;
	private String username;
	private String password;
	private String email;
	private int rating;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public User(String id, String name, String username, String password, String email, int rating) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.rating = rating;
	}
	public User(String name, String username, String email, int rating) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.rating = rating;
	}
	
	public User(String name, String username, String password, String email) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.rating = 1000;
	}
	public User(String name, String username,String pasword,String email, int rating) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.rating = rating;
	}
	public User(String username, int rating) {
		super();
		this.username = username;
		this.rating = rating;
	}
	public User() {
		super();
	}
	public User(User user) {
		this.name = user.name;
		this.email = user.email;
		this.id = user.id;
		this.rating = rating;
		this.username = user.username;
	}
	public void display() {
		System.out.println("USERNAME : " + username);
		System.out.println("NAME : " + name);
		System.out.println("EMAIL : " + email);
		System.out.println("RATING : " + rating);
	}
	public void displayForMatch() {
		System.out.println("USERNAME : " + username);
		System.out.println("RATING : " + rating);
	}
}
