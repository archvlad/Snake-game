package ru.vlad.saver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import ru.vlad.gameobject.Map;
import ru.vlad.gameobject.Player;

public class Saver {

	public static String fileName = "score_sheet.ss";

	public static void checkScore(Player p) {
		int highScore = 0;
		String testScore = null;
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		boolean set = false;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			int line = chooseMapLine(p);
			for (int i = 0; i < line - 1; i++) {
				br.readLine();
			}
			testScore = br.readLine();
			testScore = testScore.split(":")[1];
			if (p.score > Integer.parseInt(testScore)) {
				highScore = p.score;
				set = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (set) {
			// Instantiating the File class
			// Instantiating the Scanner class to read the file
			Scanner sc = null;
			try {
				sc = new Scanner(new File(fileName));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			// instantiating the StringBuffer class
			StringBuffer buffer = new StringBuffer();
			// Reading lines of the file and appending them to StringBuffer
			while (sc.hasNextLine()) {
				buffer.append(sc.nextLine() + System.lineSeparator());
			}
			String fileContents = buffer.toString();
			// closing the Scanner object
			sc.close();
			int line = chooseMapLine(p);
			String oldLine = "map" + line + ":" + testScore;
			String newLine = "map" + line + ":" + highScore;
			// Replacing the old line with new line
			fileContents = fileContents.replaceAll(oldLine, newLine);
			// instantiating the FileWriter class
			try {
				fw = new FileWriter(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fw.append(fileContents);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static int chooseMapLine(Player p) {
		if (p.currentMap == Map.map1) {
			return 1;
		} else if (p.currentMap == Map.map2) {
			return 2;
		} else if (p.currentMap == Map.map3) {
			return 3;
		} else if (p.currentMap == Map.map4) {
			return 4;
		} else {
			return -1;
		}
	}

	public static int getHightScore(int mapN) {
		String highScore = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			for (int i = 0; i < mapN - 1; i++) {
				br.readLine();
			}
			highScore = br.readLine();
			highScore = highScore.split(":")[1];
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Integer.parseInt(highScore);
	}

	public static int getHightScore(Player p) {
		String highScore = null;
		int line = chooseMapLine(p);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			for (int i = 0; i < line - 1; i++) {
				br.readLine();
			}
			highScore = br.readLine();
			highScore = highScore.split(":")[1];
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Integer.parseInt(highScore);
	}

	public static void clearScore() {
		FileWriter fw = null;
		for (int i = 1; i <= 4; i++) {
			// Instantiating the File class
			// Instantiating the Scanner class to read the file
			Scanner sc = null;
			try {
				sc = new Scanner(new File(fileName));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			// instantiating the StringBuffer class
			StringBuffer buffer = new StringBuffer();
			// Reading lines of the file and appending them to StringBuffer
			while (sc.hasNextLine()) {
				buffer.append(sc.nextLine() + System.lineSeparator());
			}
			String fileContents = buffer.toString();
			// closing the Scanner object
			sc.close();
			int line = i;
			String oldLine = "map" + line + ":" + getHightScore(i);
			String newLine = "map" + line + ":" + 0;
			// Replacing the old line with new line
			fileContents = fileContents.replaceAll(oldLine, newLine);
			// instantiating the FileWriter class
			try {
				fw = new FileWriter(fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fw.append(fileContents);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}