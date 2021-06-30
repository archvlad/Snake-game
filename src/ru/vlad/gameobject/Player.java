package ru.vlad.gameobject;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import ru.vlad.engine.GameEngine;
import ru.vlad.engine.Keyboard;
import ru.vlad.saver.Saver;

public class Player {

	public int x = 0;
	public int y = 0;
	public int size = 16;
	boolean isHungry = true;
	public int[] currentMap;
	Random r = new Random();
	public ArrayList<Integer> body = new ArrayList<Integer>(100);

	public Player() {

	}

	public void generateHead() {
		body.clear();
		directionX = -1;
		directionY = 0;
		int head = 0;
		x = 18;
		y = 5;
		head = x + y * Map.width;
		body.add(head);
	}

	double timer = 0;
	double timer2 = 0;
	double xx = 0;
	int k = 1;
	public boolean anim = false;

	public void tick() {
		chooseDirection();
		timer += (1.0 / 60.0);
		if (timer >= 0.2) {
			if (moving) {
				move();
			}
			timer = 0;
		}
		if (!moving) {
			timer2 += (1.0 / 60.0);
			if ((0.5 * (k - 1) <= timer2) && (timer2 <= 0.5 * k)) {
				anim = false;
			}
			if ((0.5 * (k) <= timer2) && (timer2 <= 0.5 * (k + 1))) {
				anim = true;
				k += 2;
			}
			if (timer2 >= 2) {
				generateHead();
				Food.generateFood(this);
				Saver.checkScore(this);
				score = 0;
				moving = true;
				GameEngine.state = GameEngine.STATE.CHOOSE_MAP;
				timer2 = 0;
				k = 1;
			}
		}
	}

	public boolean moving = true;
	int directionX = 0;
	int directionY = 0;
	boolean cho = false;
	public int score = 0;
	public int bestScore = 0;

	public void chooseDirection() {
		if (!cho) {
			if (Keyboard.keys[KeyEvent.VK_DOWN]) {
				if (directionY != -1) {
					directionY = 1;
					directionX = 0;
					cho = true;
				}
			}
			if (Keyboard.keys[KeyEvent.VK_UP]) {
				if (directionY != 1) {
					directionY = -1;
					directionX = 0;
					cho = true;
				}
			}
			if (Keyboard.keys[KeyEvent.VK_RIGHT]) {
				if (directionX != -1) {
					directionX = 1;
					directionY = 0;
					cho = true;
				}
			}
			if (Keyboard.keys[KeyEvent.VK_LEFT]) {
				if (directionX != 1) {
					directionX = -1;
					directionY = 0;
					cho = true;
				}
			}
		}
	}

	public void move() {
		if (body.size() >= 5) {
			for (int i = 0; i < body.size(); i++) {
				if ((x + directionX) + (y + directionY) * Map.width == body.get(i)) {
					moving = false;

				}
			}
		}
		if (x + directionX >= Map.width - 1 || y + directionY >= Map.height - 1 || x + directionX < 0 + 1
				|| y + directionY < 0 + 1) {
			moving = false;

		}
		if (currentMap[(x + directionX) + (y + directionY) * Map.width] == 1) {
			moving = false;

		}
		if (moving) {
			x += directionX;
			y += directionY;
			int head = x + y * Map.width;
			body.add(head);
			if (isHungry) {
				body.remove(0);
			} else {
				isHungry = true;
			}
			cho = false;
		}
	}

}
