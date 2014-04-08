package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class GamePlay {
	Ship player;
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	private ArrayList<Shot> shots = new ArrayList<Shot>();
	Board map;
	private int level = 0;
	
	public GamePlay() {
		map = new Board(aliens, shots);
		player = map.getShip();
	
	}
	
	public void newGame(){
		map = new Board(aliens, shots);
		player = map.getShip();
	}
	
	public Board getMap(){
		return map;
	}
	public Ship getPlayer(){
		return player;
	}
	public String toString(){
		return map.toString();
	}
	public void updateGame(LinkedHashSet<Integer> KeysPressed){
		updateShots();
		updateAliens();
		if(levelIsWon()){
			level+=1;
			newGame();
		}
		if(!KeysPressed.isEmpty()){
			System.out.println("Keys are being added");
			player.update(KeysPressed);
		}
	}
	
	private boolean levelIsWon() {
		for (int i = 0; i < map.getMap().length; i++) {
			for (int j = 0; j < map.getMap()[0].length; j++) {
				if(map.getMap()[i][j].isAlien()){
					return false;
				}
			}
		}
		return true;
	}

	private void updateShots() {
		//first it moves all of them
		for (int i = 0; i < shots.size(); i++) {
			Shot tempShot = shots.get(i);
			tempShot.updateShot();
		}
		
		//then it checks to see if it hits any aliens or player
		for (int i = 0; i < map.XSIZE; i++) {
			for (int j = 0; j < map.YSIZE; j++) {
				Tile tempTile = map.getTile(i, j);
				if(tempTile.isShot() && tempTile.isAlien()){
					//here it hits an alien ship
					Shot tempShot = shotAt(i,j);
					shots.remove(tempShot);
					Alien tempAlien = AlienAt(i,j);
					aliens.remove(tempAlien);
					tempTile.setAlien(false);
					tempTile.setShot(false);
				}
				if(tempTile.isShot() && tempTile.isShip()){
					//here is hits the player
					Shot tempShot = shotAt(i,j);
					shots.remove(tempShot);
					player.setLives(player.getLives()-1);
				}
			}
		}
	}

	private Alien AlienAt(int i, int j) {
		Tile tempTile = map.getTile(i, j);
		for (Alien tempAlien : aliens) {
			if(tempAlien.getTile().equals(tempTile)){
				return tempAlien;
			}
		}
		return null;
	}

	private Shot shotAt(int i, int j) {
		Tile tempTile = map.getTile(i, j);
		for (Shot tempShot : shots) {
			if(tempShot.getTile().equals(tempTile)){
				return tempShot;
			}
		}
		return null;
	}

	private void updateAliens(){
		for (int i = aliens.size()-1; i > -1; i--) {
			Alien tempAlien = aliens.get(i);
			tempAlien.update();
		}

	}

	/**
	 * @return the shots
	 */
	public ArrayList<Shot> getShots() {
		return shots;
	}

	/**
	 * @param shots the shots to set
	 */
	public void setShots(ArrayList<Shot> shots) {
		this.shots = shots;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}


}
