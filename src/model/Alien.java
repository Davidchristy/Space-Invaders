package model;

import java.util.ArrayList;
import java.util.Random;

public class Alien {
	final int WAITTIME = 20;
	private Tile tile;
	private int TurnsTillMove = WAITTIME;
	ArrayList<Shot> shots;

	public Alien(Tile tile, ArrayList<Shot> shots) {
		this.tile = tile;
		this.shots = shots;
	}

	private void Fire() {
		if (tile.getSouth() != null) {
			if (!tile.getSouth().isShot()) {
				shots.add(new Shot(tile.getSouth(), false, shots));
				tile.getSouth().setShot(true);
			}
		}
	}

	private void MoveSouth() {
		if (tile.getSouth() != null) {
			tile.setAlien(false);
			tile = tile.getSouth();
			tile.setAlien(true);
		}
	}

	private void MoveEast() {
		if (tile.getEast() != null && !tile.getEast().isAlien() && !tile.getEast().isShot()) {
			tile.setAlien(false);
			tile = tile.getEast();
			tile.setAlien(true);
		}
	}

	private void MoveWest() {
		if (tile.getWest() != null && !tile.getWest().isAlien() && !tile.getWest().isShot()) {
			tile.setAlien(false);
			tile = tile.getWest();
			tile.setAlien(true);
		}
	}

	public void update() {

		if (TurnsTillMove == 0) {
			// move forward
			this.MoveSouth();
			TurnsTillMove = WAITTIME;
		} else {

			// maybe shift see if you can shoot
			int r = new Random().nextInt(101);
			// there is a 40% chance of moving, 60% chance of shooting
			if (r > 60) {
				// shift left or right
				if (new Random().nextBoolean()) {
					// trying to move east, if it can't does nothing
					this.MoveEast();
				} else {

					this.MoveWest();
				}
			} else {

				// see if you can shoot
				/*
				 * Here is looks at the col south of it and checks if the ship
				 * is there, if it is it sets inSight to true, unless there is
				 * Alien ship, then it's always going to be false
				 */
				boolean inSight = false;
				Tile tempTile = tile;
				while (tempTile.getSouth() != null) {
					tempTile = tempTile.getSouth();
					if (tempTile.isAlien()) {
						break;
					}
					if (tempTile.isShip()) {
						inSight = true;
					}
				}
				if (inSight) {
					/*
					 * This uses the same idea of the above while loop to count
					 * the number of shots in the col already
					 */
					int shots = 0;
					tempTile = tile;
					while (tempTile.getSouth() != null) {
						tempTile = tempTile.getSouth();
						if (tempTile.isShot()) {
							shots += 1;
						}
					}
					/*
					 * To give the apperence of fasting shooting speed change
					 * the 5 to a higher number
					 */
					if (shots < 3 && !tile.getSouth().isShot()) {
						// fire a shot
						this.Fire();
					}
				}

			}
			TurnsTillMove -= 1;
		}
	}
	public Tile getTile(){
		return this.tile;
	}
}
