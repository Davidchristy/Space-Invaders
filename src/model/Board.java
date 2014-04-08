package model;

import java.util.ArrayList;

public class Board {
	final int XSIZE = 12;
	final int YSIZE = 15;
	private Tile[][] board = new Tile[XSIZE][YSIZE];
	private ArrayList<Alien> aliens;
	private Ship ship;
	Board(ArrayList<Alien> aliens, ArrayList<Shot> shots) {
		this.aliens = aliens;
		//creating Blank Tiles
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				Tile tempTile = new Tile(i, j);
				board[i][j] = tempTile;
			}
		}
		//filling neighbors
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				Tile tempTile = board[i][j];
				if(i>0){
					tempTile.setNorth(board[i-1][j]);
				}
				if(i<board.length-1){
					tempTile.setSouth(board[i+1][j]);
				}if(j>0){
					tempTile.setWest(board[i][j-1]);
				}
				if(j<board.length-1){
					tempTile.setEast(board[i][j+1]);
				}
				board[i][j] = tempTile;
			}
		}
		//setting aliens
		for (int i = 1; i < board[0].length; i += 2) {
			aliens.add(new Alien(board[0][i], shots));
			board[0][i].setAlien(true);
			aliens.add(new Alien(board[1][i-1], shots));
			board[1][i - 1].setAlien(true);
		}
		//setting  ship
		ship = new Ship(board[XSIZE - 1][YSIZE / 2], shots);
		board[XSIZE - 1][YSIZE / 2].setShip(true);
	
		/**
		 * TODO: If I want to add blocks like in the real game, do it here
		 */
	}

	public String toString() {
		String output = "";

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j].isAlien()) {
					output += "[X] ";
				} else if (board[i][j].isShip()) {
					output += "[W] ";
				} else if (board[i][j].isShot()) {
					output += "[|] ";
				} else {
					output += "[ ] ";
				}
			}
			output += "\n";
		}
		return output;
	}

	/**
	 * @return the ship
	 */
	public Ship getShip() {
		return ship;
	}
	
	public Tile getTile(int X, int Y){
		return board[X][Y];
	}
	
	public int getXSize(){
		return XSIZE;
	}
	public int getYSize(){
		return YSIZE;
	}
	public Tile[][] getMap(){
		return board;
	}



}
