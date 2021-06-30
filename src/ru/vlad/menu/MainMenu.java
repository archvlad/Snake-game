package ru.vlad.menu;
import java.awt.event.KeyEvent;

import ru.vlad.engine.GameEngine;
import ru.vlad.engine.Keyboard;

public class MainMenu extends Menu {

	public MainMenu() {
		numOptions = 3;
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

		} else if (Keyboard.keys[KeyEvent.VK_ENTER]) {
			if (canChoose) {
				switch (currentOption) {
				case 0:
					GameEngine.state = GameEngine.STATE.CHOOSE_MAP;
					break;
				case 1:
					GameEngine.state = GameEngine.STATE.HIGH_SCORES;
					break;
				case 2:
					GameEngine.state = GameEngine.STATE.CREDITS;
					break;
				}
				canChoose = false;
			}

		} else {
			canChoose = true;
		}
	}

}