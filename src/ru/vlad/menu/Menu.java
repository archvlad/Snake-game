package ru.vlad.menu;
public abstract class Menu {

	public int currentOption;
	public int numOptions;
	
	boolean canChoose = false;
	
	public void setCurrentOption(int co) {
		currentOption = co % numOptions;
		if (currentOption < 0) {
			currentOption += numOptions;
		}
	}
	
}
