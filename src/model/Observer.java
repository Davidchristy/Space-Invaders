package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import view.SpaceInvadersGUI;

public class Observer implements ActionListener {
	Timer timer;
	static GamePlay game;
	static SpaceInvadersGUI gui;

	public static void main(String[] args) {
		game =  new GamePlay();
		gui = new SpaceInvadersGUI(game);
		gui.setVisible(true);
		new Observer();
	}

	public Observer() {
		timer = new Timer(200 - (50*game.getLevel()), this);
		timer.setInitialDelay(1000);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(gui.getKeysPressed().contains(27)){
			game.newGame();
		}
		game.updateGame(gui.getKeysPressed());
		gui.update();
	}
}
