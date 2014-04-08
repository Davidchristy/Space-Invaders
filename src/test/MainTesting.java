package test;

import java.util.Scanner;

import model.GamePlay;

public class MainTesting {

	public static void main(String[] args) {
		GamePlay game = new GamePlay();
		
		Scanner wait = new Scanner(System.in);
		while(true){
//			game.updateGame();
			System.out.println(game);
			wait.next();
		}
		

	}

}
