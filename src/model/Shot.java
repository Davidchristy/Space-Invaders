package model;

import java.util.ArrayList;

public class Shot {
	private boolean playerShot;
	Tile tile;
	ArrayList<Shot> shots;
	
	public Shot(Tile tile, boolean player, ArrayList<Shot> shots){
		this.tile = tile;
		this.playerShot = player;
		this.shots = shots;
	}
	
	private void MoveSouth() {
		tile.setShot(false);
		if (tile.getSouth() != null) {
			
			tile = tile.getSouth();
			tile.setShot(true);
		}
		else{
			shots.remove(this);
		}
	}
	
	private void MoveNorth() {
		tile.setShot(false);
		if (tile.getNorth() != null) {
			tile = tile.getNorth();
			tile.setShot(true);
		}
		else{
			shots.remove(this);
		}
	}
	
	public Tile getTile(){
		return this.tile;
	}
	
	public void updateShot(){
		/*
		 * This moves the shot up or down depending
		 * on whose it is.
		 */
		if(playerShot){
			this.MoveNorth();
		}else{
			this.MoveSouth();
		}
	}

	/**
	 * @return the playerShot
	 */
	public boolean isPlayerShot() {
		return playerShot;
	}

	/**
	 * @param playerShot the playerShot to set
	 */
	public void setPlayerShot(boolean playerShot) {
		this.playerShot = playerShot;
	}

}
