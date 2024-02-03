package algorithm.util;

import java.util.Random;
import entity.util.User;

public class RandomWinner {
	Random random = new Random();
	public User getRandomWinner(User playerA , User playerB) {
		User array[] = new User[3];
		array[1] = playerA;
		array[2] = playerB;
		array[0] = null;
		return array[random.nextInt(3)];
	}
}
