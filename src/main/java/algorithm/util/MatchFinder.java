package algorithm.util;

import java.util.List;
import java.util.PriorityQueue;
public class MatchFinder{
	private String requstedUser;
	private List<PairingClass> usersList = null;
	private PriorityQueue<PairingClass> queue = null;
	public String getRequstedUser() {
		return requstedUser;
	}
	public void setRequstedUser(String requstedUser) {
		this.requstedUser = requstedUser;
	}
	public List<PairingClass> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<PairingClass> usersList) {
		this.usersList = usersList;
	}
	public void setQueue(PriorityQueue<PairingClass> queue) {
		this.queue = queue;
	}
	public MatchFinder(String requstedUser, List<PairingClass> usersList) {
		super();
		this.requstedUser = requstedUser;
		this.usersList = usersList;
	}
	public void addUser(PairingClass user) {
		usersList.add(user);
	}
	public void startPairing() {
		queue = new PriorityQueue<>(usersList);
	}
	public MatchFinder() {
		super();
	}
	public void clean() {
		usersList.clear();
		queue.clear();
	}
	public String getBestMatch() {
		this.startPairing();
		PairingClass p = null;
		try {
			while((p = queue.poll()).getUsername().equals(requstedUser)) {
			}
		}catch (Exception e) {
			return null;
		}
		return p.getUsername();
	}
}
