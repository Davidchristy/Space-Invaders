package model;


public class Tile {
	private int X = -1;
	private int Y = -1;
	private boolean ship = false;
	private boolean shot = false;
	private boolean alien = false;
	private Tile north = null;
	private Tile west = null;
	private Tile east = null;
	private Tile south = null;

	Tile(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	/**
	 * @return the alien
	 */
	public boolean isAlien() {
		return alien;
	}

	/**
	 * @param alien the alien to set
	 */
	public void setAlien(boolean alien) {
		this.alien = alien;
	}

	/**
	 * @return the shot
	 */
	public boolean isShot() {
		return shot;
	}

	/**
	 * @param shot the shot to set
	 */
	public void setShot(boolean shot) {
		this.shot = shot;
	}

	/**
	 * @return the ship
	 */
	public boolean isShip() {
		return ship;
	}

	/**
	 * @param ship the ship to set
	 */
	public void setShip(boolean ship) {
		this.ship = ship;
	}

	/**
	 * @return the north
	 */
	public Tile getNorth() {
		return north;
	}

	/**
	 * @param north the north to set
	 */
	public void setNorth(Tile north) {
		this.north = north;
	}

	/**
	 * @return the west
	 */
	public Tile getWest() {
		return west;
	}

	/**
	 * @param west the west to set
	 */
	public void setWest(Tile west) {
		this.west = west;
	}

	/**
	 * @return the east
	 */
	public Tile getEast() {
		return east;
	}

	/**
	 * @param east the east to set
	 */
	public void setEast(Tile east) {
		this.east = east;
	}

	/**
	 * @return the south
	 */
	public Tile getSouth() {
		return south;
	}

	/**
	 * @param south the south to set
	 */
	public void setSouth(Tile south) {
		this.south = south;
	}
}