package entity.util;

import algorithm.util.RandomWinner;

public class Game {
	private User playerA;
	private User playerB;
	private String id;
	private User winner;
	public Game(User playerA, User playerB, String i) {
		super();
		this.playerA = playerA;
		this.playerB = playerB;
		this.id = id;
		this.winner = winner;
	}
	public Game(User playerA, User playerB) {
		super();
		this.playerA = playerA;
		this.playerB = playerB;
		this.winner = winner;
	}
	public User getPlayerA() {
		return playerA;
	}
	public void setPlayerA(User playerA) {
		this.playerA = playerA;
	}
	public User getPlayerB() {
		return playerB;
	}
	public void setPlayerB(User playerB) {
		this.playerB = playerB;
	}
	public String getId(){
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getWinner() {
		return winner;
	}
	public void setWinner(User winner) {
		this.winner = winner;
	}
}
