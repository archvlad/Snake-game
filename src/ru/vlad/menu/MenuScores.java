package ru.vlad.menu;
import java.awt.event.KeyEvent;

import ru.vlad.engine.GameEngine;
import ru.vlad.engine.Keyboard;
import ru.vlad.saver.Saver;

public class MenuScores extends Menu {

	public MenuScores() {
		numOptions = 2;
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
					GameEngine.state = GameEngine.STATE.MENU;
					break;
				case 1:
					Saver.clearScore();
					break;
				}
				currentOption = 0;
				canChoose = false;
			}
		} else {
			canChoose = true;
		}
	}

}
