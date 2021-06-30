package ru.vlad.graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import ru.vlad.engine.GameEngine;
import ru.vlad.gameobject.Food;
import ru.vlad.gameobject.Map;
import ru.vlad.gameobject.Player;
import ru.vlad.menu.MainMenu;
import ru.vlad.menu.Menu;
import ru.vlad.saver.Saver;

public class Renderer {

	Graphics2D g;
	Font font;

	public Renderer(Font font) {
		this.font = font;
	}

	public void giveRendererGraphics(Graphics2D g) {
		this.g = g;
	}

	public void renderMap(Player p) {
		for (int y = 0; y < Map.height; y++) {
			for (int x = 0; x < Map.width; x++) {
				if (y % 2 == 0) {
					if (x % 2 == 0) {
						g.setColor(new Color(43, 116, 251));
					} else {
						g.setColor(new Color(23, 102, 249));
					}
				}
				if (y % 2 != 0) {
					if (x % 2 != 0) {
						g.setColor(new Color(43, 116, 251));
					} else {
						g.setColor(new Color(23, 102, 249));
					}
				}
				if (p.currentMap[x + y * Map.width] == 1) {
					g.setColor(new Color(27, 20, 100));
				}
				g.fillRect(x * Map.size, y * Map.size, Map.size, Map.size);
				g.setColor(new Color(27, 20, 100));
				g.drawRect(x * Map.size, y * Map.size, Map.size, Map.size);
			}
		}
	}

	int k = 1;

	public void renderPlayer(Player p, Map m) {
		if (!p.moving) {
			if (p.anim) {
				for (int i = 0; i < p.body.size(); i++) {
					int x = p.body.get(i) % Map.width;
					int y = (p.body.get(i) - x) / Map.width;
					g.setColor(new Color(196, 229, 56));
					g.fillRect(x * Map.size, y * Map.size, p.size, p.size);
					g.setColor(new Color(27, 20, 100));
					g.drawRect(x * Map.size, y * Map.size, Map.size, Map.size);
				}
			}

		} else {
			for (int i = 0; i < p.body.size(); i++) {
				int x = p.body.get(i) % Map.width;
				int y = (p.body.get(i) - x) / Map.width;
				g.setColor(new Color(196, 229, 56));
				g.fillRect(x * Map.size, y * Map.size, p.size, p.size);
				g.setColor(new Color(27, 20, 100));
				g.drawRect(x * Map.size, y * Map.size, Map.size, Map.size);
			}
		}

	}

	public void renderFood(Food f, Map m) {
		g.setColor(new Color(234, 32, 39));
		g.fillRect(Food.x * Map.size, Food.y * Map.size, Food.size, Food.size);
		g.setColor(new Color(27, 20, 100));
		g.drawRect(Food.x * Map.size, Food.y * Map.size, Food.size, Food.size);
	}

	public void renderScore(Player p) {
		g.setColor(new Color(27, 20, 100));
		g.fillRect(0, 320, 320, 64);
		g.setColor(new Color(255, 195, 18));
		g.setFont(font);
		g.drawString(String.format("Score:"), 16, 336);
		g.drawString(String.format("%02d", p.score), 100, 336);
		g.drawString(String.format("Best:"), 16, 360);
		g.drawString(String.format("%02d", p.bestScore), 100, 360);
	}

	int buttonSize = 186;
	int buttonX = GameEngine.width / 2 - buttonSize / 2;
	int buttonStartPostion = 120;

	public void renderStartScreen(Map m, Player p, MainMenu menu) {
		renderBG();
		g.setColor(new Color(255, 195, 18));
		g.setFont(font.deriveFont(50f));
		String name = "SNAKE";
		g.drawString(name, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(name) / 2), 80);

		// Buttons properties
		g.setFont(font.deriveFont(25f));
		int buttonHeight = g.getFontMetrics().getHeight() + 5;
		int skip = 18;
		int spaceForButton = buttonHeight + skip;
		int startYPostionStrings = buttonStartPostion + buttonHeight
				- (buttonHeight - g.getFontMetrics().getHeight()) / 2 - 2;

		// Play button
		String play = "Play";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 0, buttonSize, buttonHeight);

		// High scores button
		String highScores = "High Scores";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 1, buttonSize, buttonHeight);

		// Credits button
		String credits = "Credits";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 2, buttonSize, buttonHeight);

		g.setColor(new Color(238, 90, 36));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * menu.currentOption, buttonSize, buttonHeight);

		g.setColor(new Color(253, 235, 200));
		g.drawString(play, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(play) / 2), startYPostionStrings);
		g.drawString(highScores, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(highScores) / 2),
				startYPostionStrings + spaceForButton);
		g.drawString(credits, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(credits) / 2),
				startYPostionStrings + spaceForButton * 2);
	}

	public void renderBG() {
		g.setColor(new Color(27, 20, 100));
		g.fillRect(0, 0, GameEngine.width, GameEngine.height);
		for (int y = 1; y < 23; y++) {
			for (int x = 1; x < 19; x++) {
				if (y % 2 == 0) {
					if (x % 2 == 0) {
						g.setColor(new Color(43, 116, 251));
					} else {
						g.setColor(new Color(23, 102, 249));
					}
				}
				if (y % 2 != 0) {
					if (x % 2 != 0) {
						g.setColor(new Color(43, 116, 251));
					} else {
						g.setColor(new Color(23, 102, 249));
					}
				}
				g.fillRect(x * 16, y * 16, 16, 16);
			}
		}
	}

	public void renderCredits() {
		renderBG();
		g.setColor(new Color(255, 195, 18));
		g.setFont(font.deriveFont(30f));
		String name = "Credits";
		g.drawString(name, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(name) / 2), 80);
		g.setFont(font.deriveFont(16f));
		g.setColor(new Color(253, 235, 200));
		int lineHeight = g.getFontMetrics().getHeight();
		String textToDraw = "This game was created by" + " Vladislav Grigoriev over the summer"
				+ " 2020 at age 17. Java was used for this game development";
		String[] arr = textToDraw.split(" ");
		int n = 0;
		int x = 40;
		int y = 130;
		int wrapLine = 241;
		while (n < arr.length) {
			String line = arr[n++];
			while ((n < arr.length) && (g.getFontMetrics().stringWidth(line + " " + arr[n]) < wrapLine)) {
				line = line + " " + arr[n];
				n++;
			}
			g.drawString(line, x, y);
			y = y + lineHeight;
		}
		g.setFont(font.deriveFont(25f));
		int buttonHeight = g.getFontMetrics().getHeight() + 5;
		int yB = GameEngine.height - buttonHeight - 32;
		String back = "Back";
		g.setColor(new Color(238, 90, 36));
		g.fillRect(buttonX, yB, buttonSize, buttonHeight);
		g.setColor(new Color(253, 235, 200));
		int yS = yB + buttonHeight - (buttonHeight - g.getFontMetrics().getHeight()) / 2 - 2;
		g.drawString(back, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(back) / 2), yS);
	}

	public void renderChoosingMap(Menu m) {
		renderBG();
		g.setColor(new Color(255, 195, 18));
		g.setFont(font.deriveFont(30f));
		String name = "Choose Map";
		g.drawString(name, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(name) / 2), 80);
		g.setFont(font.deriveFont(16f));
		g.setColor(new Color(253, 235, 200));

		g.setFont(font.deriveFont(25f));
		int buttonHeight = g.getFontMetrics().getHeight() + 5;
		int skip = 18;
		int spaceForButton = buttonHeight + skip;
		int startYPostionStrings = buttonStartPostion + buttonHeight
				- (buttonHeight - g.getFontMetrics().getHeight()) / 2 - 2;

		// Map1 button
		String map1 = "Simple";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 0, buttonSize, buttonHeight);

		// Map2 button
		String map2 = "Cross";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 1, buttonSize, buttonHeight);

		// Map3 button
		String map3 = "Floors";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 2, buttonSize, buttonHeight);

		// Map4 button
		String map4 = "Fork";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 3, buttonSize, buttonHeight);

		// Back button
		String back = "Back";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * 4, buttonSize, buttonHeight);

		g.setColor(new Color(238, 90, 36));
		g.fillRect(buttonX, buttonStartPostion + spaceForButton * m.currentOption, buttonSize, buttonHeight);

		g.setColor(new Color(253, 235, 200));
		g.setFont(font.deriveFont(25f));
		g.drawString(map1, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(map1) / 2), startYPostionStrings);
		g.drawString(map2, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(map2) / 2),
				startYPostionStrings + spaceForButton);
		g.drawString(map3, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(map3) / 2),
				startYPostionStrings + spaceForButton * 2);
		g.drawString(map4, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(map4) / 2),
				startYPostionStrings + spaceForButton * 3);
		g.drawString(back, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(back) / 2),
				startYPostionStrings + spaceForButton * 4);
	}

	public void renderHighScores(Menu m) {
		renderBG();
		g.setColor(new Color(255, 195, 18));
		g.setFont(font.deriveFont(30f));
		String name = "High Scores";
		g.drawString(name, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(name) / 2), 80);

		g.setFont(font.deriveFont(25f));
		int buttonHeight = g.getFontMetrics().getHeight() + 5;
		int yB = GameEngine.height - buttonHeight - 32;
		int skip = 16;
		int spaceForButton = 16 + buttonHeight;

		String back = "Back";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, yB - spaceForButton * 0, buttonSize, buttonHeight);

		String reset = "Reset";
		g.setColor(new Color(247, 159, 31));
		g.fillRect(buttonX, yB - spaceForButton * 1, buttonSize, buttonHeight);
		g.setColor(new Color(253, 235, 200));

		g.setColor(new Color(238, 90, 36));
		g.fillRect(buttonX, yB - spaceForButton * m.currentOption, buttonSize, buttonHeight);

		g.setColor(new Color(253, 235, 200));
		int yS = yB + buttonHeight - (buttonHeight - g.getFontMetrics().getHeight()) / 2 - 2;
		g.drawString(back, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(back) / 2), yS);
		g.drawString(reset, (GameEngine.width / 2 - g.getFontMetrics().stringWidth(reset) / 2),
				yS - buttonHeight - skip);

		g.setFont(font.deriveFont(20f));
		g.setColor(new Color(253, 235, 200));
		g.drawString("Map", 40, 115);
		g.drawString("High Score", GameEngine.width - 40 - g.getFontMetrics().stringWidth("High Score"), 115);

		g.drawLine(40, 120, GameEngine.width - 40, 120);

		int skipS = g.getFontMetrics().getHeight() + 8;

		g.drawString("Simple", 40, 148);
		String score = String.format("%02d", Saver.getHightScore(1));
		g.drawString(score, GameEngine.width - 40 - g.getFontMetrics().stringWidth(score), 148);

		g.drawString("Cross", 40, 148 + skipS);
		score = String.format("%02d", Saver.getHightScore(2));
		g.drawString(score, GameEngine.width - 40 - g.getFontMetrics().stringWidth(score), 148 + skipS);

		g.drawString("Floors", 40, 148 + skipS * 2);
		score = String.format("%02d", Saver.getHightScore(3));
		g.drawString(score, GameEngine.width - 40 - g.getFontMetrics().stringWidth(score), 148 + skipS * 2);

		g.drawString("Fork", 40, 148 + skipS * 3);
		score = String.format("%02d", Saver.getHightScore(4));
		g.drawString(score, GameEngine.width - 40 - g.getFontMetrics().stringWidth(score), 148 + skipS * 3);

	}

}
