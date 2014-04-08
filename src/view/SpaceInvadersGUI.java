package view;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.GamePlay;

public class SpaceInvadersGUI extends JFrame {
	/*
	 * This is stuff I'll need for the model
	 */
	GamePlay game;
	BoardAsImage boardImage;
	JPanel mainFrame;
	LinkedHashSet<Integer> keysPressed;

	public SpaceInvadersGUI(GamePlay game) {

		this.setSize(900, 800); // size may need to change

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setResizable(false);
		this.setTitle("Space Invaders-Level 0:");
		initializeGUIComponents(game);
		addGUIComponents();
	}

	private void initializeGUIComponents(GamePlay game) {
		mainFrame = new JPanel();
		mainFrame.setBackground(Color.BLUE);
		this.game = game;
		boardImage = new BoardAsImage(game);
		keysPressed = new LinkedHashSet<Integer>();
	}

	private void addGUIComponents() {
		mainFrame.setSize(this.getSize());
		this.add(mainFrame);
		mainFrame.add(boardImage);
		this.addKeyListener(new KeyButtonListener());
		this.setFocusable(true);
		JOptionPane.showMessageDialog(this, "Welcome! You the left and right arrow keys to move and space to shoot!");
	}

	public void update() {
		boardImage.repaint();
		if(game.getPlayer().getLives()<0){
			JOptionPane.showMessageDialog(this, "You loss!");
			game.getPlayer().setLives(3);
			game.newGame();
		}
		if(game.getLevel() != this.getTitle().charAt(this.getTitle().length()-1) ){
			this.setTitle("Space Invaders-Level " + game.getLevel());
		}
	}

	public LinkedHashSet<Integer> getKeysPressed() {
		return keysPressed;
	}

	private class KeyButtonListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// int trigger = e.getKeyCode();
			keysPressed.add(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

	}

}
