package ru.vlad.menu;
import java.awt.event.KeyEvent;

import ru.vlad.engine.GameEngine;
import ru.vlad.engine.Keyboard;
import ru.vlad.gameobject.Food;
import ru.vlad.gameobject.Map;
import ru.vlad.gameobject.Player;
import ru.vlad.saver.Saver;

public class MenuChoosingMap extends Menu {

	Player p;
	Food f;

	public MenuChoosingMap(Player p, Food f) {
		this.p = p;
		this.f = f;
		numOptions = 5;
		currentOption = 0;
	}

	public void tick() {
		if (Keyboard.keys[KeyEvent.VK_DOWN]) {
			if (canChoose) {
				setCurrentOption(++currentOption);
				canChoose = false;
			}

		} else if (Keyboard.keys[KeyEvent.VK_UP]) {
			if (canChoose) {
				setCurrentOption(--currentOption);
				canChoose = false;
			}

		} else if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
			if (canChoose) {
				GameEngine.state = GameEngine.STATE.MENU;
				currentOption = 0;
				canChoose = false;
			}
		} else if (Keyboard.keys[KeyEvent.VK_ENTER]) {
			if (canChoose) {
				switch (currentOption) {
				case 0:
					p.currentMap = Food.map = Map.map1;
					startGame();

					break;
				case 1:
					p.currentMap = Food.map = Map.map2;
					startGame();
					break;
				case 2:
					p.currentMap = Food.map = Map.map3;
					startGame();
					break;
				case 3:
					p.currentMap = Food.map = Map.map4;
					startGame();
					break;
				case 4:
					GameEngine.state = GameEngine.STATE.MENU;
					break;
				}
				currentOption = 0;
				canChoose = false;
			}
		} else {
			canChoose = true;
		}

	}

	public void startGame() {
		p.generateHead();
		p.bestScore = Saver.getHightScore(p);
		Food.generateFood(p);
		GameEngine.state = GameEngine.STATE.GAME;
	}

}
