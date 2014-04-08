package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Ship {
	Tile tile;
	ArrayList<Shot> shots;
	private int lives = 3;

	Ship(Tile tile, ArrayList<Shot> shots) {
		this.tile = tile;
		this.shots = shots;
	}

	public void Fire() {
		if (tile.getNorth() != null) {
			if (!tile.getNorth().isShot()) {
				shots.add(new Shot(tile.getNorth(), true, shots));
				tile.getNorth().setShot(true);
			}
		}

	}

	public void MoveNorth() {
		if (tile.getNorth() != null) {
			tile.setShip(false);
			tile = tile.getNorth();
			tile.setShip(true);
		}
	}

	public void MoveEast() {
		if (tile.getEast() != null) {
			tile.setShip(false);
			tile = tile.getEast();
			tile.setShip(true);
		}
	}

	public void MoveWest() {
		if (tile.getWest() != null) {
			tile.setShip(false);
			tile = tile.getWest();
			tile.setShip(true);
		}
	}

	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives
	 *            the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	public void update(LinkedHashSet<Integer> keysPressed) {
		boolean firstTime = true;
		int trigger = 0;
		for (Iterator iterator = keysPressed.iterator(); firstTime;) {
			Integer keyEventCode = (Integer) iterator.next();
			trigger = keyEventCode;
			firstTime = false;
		}
		System.out.println("This key is being done:" + trigger);
		switch (trigger) {
		case 37:
			// right
			this.MoveWest();
//			keysPressed.remove(37);
			break;
		case 39:
			// left
			this.MoveEast();
//			keysPressed.remove(39);
			break;
		case 32:
			// space
			this.Fire();
//			keysPressed.remove(32);
			break;
		
		}
		keysPressed.remove(trigger);
			

	}
}
