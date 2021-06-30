package ru.vlad.launcher;
import java.awt.Graphics2D;

import ru.vlad.engine.GameEngine;
import ru.vlad.gameobject.Food;
import ru.vlad.gameobject.Map;
import ru.vlad.gameobject.Player;
import ru.vlad.graphics.Renderer;
import ru.vlad.menu.MainMenu;
import ru.vlad.menu.MenuChoosingMap;
import ru.vlad.menu.MenuCredits;
import ru.vlad.menu.MenuScores;

public class GameManager {

	public Map map = new Map();
	public Player player;
	public Food food;
	public MainMenu menu;
	public MenuCredits menuCredits;
	public MenuChoosingMap mcm;
	public MenuScores hsm;

	public GameManager() {
		food = new Food();
		player = new Player();
		menu = new MainMenu();
		menuCredits = new MenuCredits();
		mcm = new MenuChoosingMap(player, food);
		hsm = new MenuScores();
	}

	public void tick() {
		if (GameEngine.state == GameEngine.STATE.GAME) {
			player.tick();
			food.tick(player);
		}
		if (GameEngine.state == GameEngine.STATE.MENU) {
			menu.tick();
		}
		if (GameEngine.state == GameEngine.STATE.CREDITS) {
			menuCredits.tick();
		}
		if (GameEngine.state == GameEngine.STATE.CHOOSE_MAP) {
			mcm.tick();
		}
		if (GameEngine.state == GameEngine.STATE.HIGH_SCORES) {
			hsm.tick();
		}
	}

	public void render(Graphics2D g, Renderer r) {
		r.giveRendererGraphics(g);
		if (GameEngine.state == GameEngine.STATE.MENU) {
			r.renderStartScreen(map, player, menu);
		}
		if (GameEngine.state == GameEngine.STATE.CREDITS) {
			r.renderCredits();
		}
		if (GameEngine.state == GameEngine.STATE.CHOOSE_MAP) {
			r.renderChoosingMap(mcm);
		}
		if (GameEngine.state == GameEngine.STATE.HIGH_SCORES) {
			r.renderHighScores(hsm);
		}
		if (GameEngine.state == GameEngine.STATE.GAME) {
			r.renderMap(player);
			r.renderFood(food, map);
			r.renderPlayer(player, map);
			r.renderScore(player);
		}
	}

	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.createWindow();
		game.start();
	}

}
