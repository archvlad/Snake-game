package ru.vlad.gameobject;
import java.util.Random;

public class Food {

	public static int x = 7;
	public static int y = 7;
	public int score = 1;
	public static int[] map;
	public static int size = 16;
	public static Random r = new Random();

	public Food() {

	}

	public void tick(Player p) {
		eat(p);
	}

	public void eat(Player p) {
		if (p.x == x && p.y == y) {
			p.isHungry = false;
			p.score += score;
			if (p.score > p.bestScore) {
				p.bestScore = p.score;
			}
			generateFood(p);
		}
	}

	public static void generateFood(Player p) {
		boolean ok = false;
		do {
			ok = true;
			x = r.nextInt(Map.width - 2) + 1;
			y = r.nextInt(Map.height - 2) + 1;
			int n = x + y * Map.width;
			for (int i = 0; i < p.body.size(); i++) {
				if (n == p.body.get(i)) {
					ok = false;
				}
			}
			if (map[n] == 1) {
				ok = false;
			}
		} while (!ok);
	}

}