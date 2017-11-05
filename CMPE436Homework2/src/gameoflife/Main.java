//Kutay Candan
//2013400003
//kutay.candan@gmail.com
//CMPE 436 - Assignment 2
package gameoflife;

import java.io.*;
import java.util.*;
/**
 * Main class of game of life.
 * @author kutay
 *
 */
public class Main {
	/**
	 * Main function gets arguments and opens and starts game.
	 * @param args 0.row 1.column 2.turn 3.filename(if wanted)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		int M = Integer.valueOf(args[0]);
		int N = Integer.valueOf(args[1]);
		int totalTurn = Integer.valueOf(args[2]);
		GameOfLife game;
		if(args.length==3) {
			game= new GameOfLife(M,N);
		}
		else {
			String inputName=args[3];
			game= new GameOfLife(M,N,inputName);
		}
		game.playGame(totalTurn);
		}catch(NumberFormatException e ) {
			System.out.println("Values must be integer.");
		}
	}

}
