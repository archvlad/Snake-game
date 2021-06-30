package ru.vlad.engine;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import ru.vlad.graphics.Renderer;
import ru.vlad.launcher.GameManager;

public class GameEngine {

	public JFrame frame;
	public Canvas canvas;
	public BufferStrategy bs;
	public Graphics g;

	public static int width = 320;
	public static int height = 384;
	public static String title = "Snake";

	public GameManager gameManager;
	public Renderer renderer;
	public Keyboard keyboard;
	public Font font;

	public static enum STATE {
		MENU, GAME, CREDITS, CHOOSE_MAP, HIGH_SCORES;
	}

	public static STATE state = STATE.MENU;

	public GameEngine() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("EightBitDragon-anqx.ttf")).deriveFont(16f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("EightBitDragon-anqx.ttf")).deriveFont(32f));
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		gameManager = new GameManager();
		renderer = new Renderer(font);
	}

	public void createWindow() {
		frame = new JFrame();
		canvas = new Canvas();
		Dimension size = new Dimension(width, height);
		canvas.setPreferredSize(size);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(canvas);
		frame.pack();
		frame.setTitle(title);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		keyboard = new Keyboard();
	}

	public void start() {
		canvas.addKeyListener(keyboard);
		canvas.requestFocus();
		long lastTime = System.nanoTime();
		long nowTime = 0;
		int deltaTime = 0;
		float timePerTick = 1000000000 / 60;
		int ticksCounter = 0;
		int framesCounter = 0;
		int stopwatch = 0;
		while (true) {
			nowTime = System.nanoTime();
			deltaTime += nowTime - lastTime;
			stopwatch += nowTime - lastTime;
			lastTime = nowTime;
			while (deltaTime >= timePerTick) {
				tick();
				deltaTime -= timePerTick;
				ticksCounter++;
			}
			render();
			framesCounter++;
			if (stopwatch >= 1000000000) {
				System.out.printf("FPS: %3d, UPS: %3d%n", framesCounter, ticksCounter);
				ticksCounter = 0;
				framesCounter = 0;
				stopwatch -= 1000000000;
			}
		}
	}

	public void tick() {
		gameManager.tick();
	}

	public void render() {
		bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0, width, height);
		gameManager.render(g2d, renderer);
		g.dispose();
		bs.show();
	}

}
