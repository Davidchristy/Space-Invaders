package view;

import model.GamePlay;
import model.Shot;
import model.Tile;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardAsImage extends JPanel {

	GamePlay game;

	/*
	 * I might change how I work the Pictures in this GUI later on to get it
	 * working with .jar files. until that time I'll stick with the setup I used
	 * in checkers
	 */
//	ImageIcon icon = new ImageIcon(getClass().getResource("/image.jpg"));
	private ImageIcon Ship;
	private ImageIcon Alien;
	private ImageIcon Lazer;
	private ImageIcon BackGround;
	private ImageIcon ShotLazer;
//	private static BufferedImage Ship;
//	private static BufferedImage Alien;
//	private static BufferedImage Lazer;
//	private static BufferedImage BackGround;


	BoardAsImage(GamePlay game) {
		// try to read in all of our images
				// this block is the blueprint to be used for all images
		
//		System.out.println(getClass().getResource("./alien.png"));
		Ship = new ImageIcon(getClass().getResource("/ship.png"));
		
		Alien = new ImageIcon(getClass().getResource("/alien.png"));
		Lazer = new ImageIcon(getClass().getResource("/lazer.png"));
		BackGround = new ImageIcon(getClass().getResource("/background.png"));
		ShotLazer = new ImageIcon(getClass().getResource("/shipLazer.png"));
		
//		try {
//				
////					Ship = ImageIO.read(new File(".." + File.separator + "ship.png"));
////					Alien = ImageIO
////							.read(new File("img" + File.separator + "alien.png"));
////					Lazer = ImageIO
////							.read(new File("img" + File.separator + "lazer.png"));
////					BackGround = ImageIO.read(new File("img" + File.separator
////							+ "background.png"));
//
//				} catch (IOException e) {
//					System.out.println("Could not find an image.");
//				}

		
		
		this.game = game;
		this.setPreferredSize(new Dimension(game.getMap().getYSize() * 50, game
				.getMap().getXSize() * 50));
		// this.setPreferredSize(this.getMaximumSize());
	}

	public void paintComponent(Graphics g) { // here is where we paint it
		super.paintComponent(g); // normal business
		Graphics2D g2d = (Graphics2D) g;

		Image newImage; // instantiate our image for painting
		for (int r = 0; r < game.getMap().getXSize(); r++) { // for every row
			for (int c = 0; c < game.getMap().getYSize(); c++) { // for every
																	// column

				newImage = BackGround.getImage();
				g2d.drawImage(newImage, c * 50, r * 50, this);

				newImage = chooseImage(r, c);
				g2d.drawImage(newImage, c * 50, r * 50, this);
			}
		}

	}

	private Image chooseImage(int r, int c) {
		if (game.getMap().getTile(r, c).isAlien()) {
			return Alien.getImage();
		}
		if (game.getMap().getTile(r, c).isShip()) {
			return Ship.getImage();
		}
		if (game.getMap().getTile(r, c).isShot()) {
			
			Shot tempLazy = findShotAt(r,c);
			if(tempLazy == null)
				return null;
			if(tempLazy.isPlayerShot()){
				return this.ShotLazer.getImage();
			}else{
				return Lazer.getImage();
			}
		}
		// I might have to add a clear tile
		return null;
	}

	private Shot findShotAt(int r, int c) {
		Tile tempTile = game.getMap().getMap()[r][c];
		for (Shot tempShot : game.getShots()) {
			if(tempShot.getTile().equals(tempTile)){
				return tempShot;
			}
		}
		return null;
	}
}
