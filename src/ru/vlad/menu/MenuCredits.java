package ru.vlad.menu;
import java.awt.event.KeyEvent;

import ru.vlad.engine.GameEngine;
import ru.vlad.engine.Keyboard;

public class MenuCredits extends Menu {

	public MenuCredits() {
		numOptions = 1;
	}

	public void tick() {
		if (Keyboard.keys[KeyEvent.VK_ESCAPE]) {
			if (canChoose) {
				GameEngine.state = GameEngine.STATE.MENU;
				canChoose = false;
			}
		} else if (Keyboard.keys[KeyEvent.VK_ENTER]) {
			if (canChoose) {
				GameEngine.state = GameEngine.STATE.MENU;
				canChoose = false;
			}
		} else {
			canChoose = true;
		}

	}

}
