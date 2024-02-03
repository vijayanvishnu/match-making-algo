package algorithm.util;

import java.util.Objects;

public class PairingClass implements Comparable<PairingClass>{
	private String username;
	private int rating;
	private int ratingDiff;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getRatingDiff() {
		return ratingDiff;
	}
	public void setRatingDiff(int ratingDiff) {
		this.ratingDiff = ratingDiff;
	}
	public PairingClass(String username, int rating, int ratingDiff) {
		super();
		this.username = username;
		this.rating = rating;
		this.ratingDiff = ratingDiff;
	}
	public PairingClass() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PairingClass other = (PairingClass) obj;
		return Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "[" + ratingDiff + "]";
	}
	public int compareTo(PairingClass other) {
        return Integer.compare(this.ratingDiff, other.ratingDiff);
    }
}
