package gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Game of life class contains game and its start condition
 * @author kutay
 *
 */
public class GameOfLife {
	int row;
	int column;
	int [][] table;
	/**
	 * Construction function of game of life. Table is filled with random numbers.
	 * @param M number of row of table
	 * @param N number of column of table
	 */
	public GameOfLife(int M,int N) {
		this.row=M;
		this.column=N;
		this.table=new int [M][N];
		createRandomTable();
		
	}
	/**
	 * Construction function of game of life.Table's values are taken from file 
	 * @param M number of row of table
	 * @param N number of column of table
	 * @param name File name
	 */
	public GameOfLife(int M,int N,String name) {
		this.row=M;
		this.column=N;
		this.table=new int [M][N];
		createDefinedTable(name);
	}
	/**
	 * This function creates random 0 or 1 and puts table.
	 */
	public void createRandomTable() {
		for(int i=0;i<this.row;i++) {
			for(int j=0;j<this.column;j++) {
				int random=0;
				if(Math.random()>0.5) random=1;
				table[i][j]=random;
			}
		}
	}
	/**
	 * This function gets values from file and puts table. Also checks data is correctly given.
	 * @param name File name
	 */
	public void createDefinedTable(String name) {
		try {
			Scanner input = new Scanner (new File(name));
			for(int i=0;i<this.row;i++) {
				for(int j=0;j<this.column;j++) {
					try {
						int temp=Integer.valueOf(input.next());
						if(temp!=0 && temp!=1) {
							System.out.println("Values must be 0 or 1.");
							System.exit(1);
						}
						this.table[i][j]=temp;
					}catch(NumberFormatException e) {
						System.out.println("Input has different types. Must be integer.");
						System.exit(1);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found.");
		}
	}
	/**
	 * This function basically gets one data from table and checks its neighbor data.
	 * Changes or keeps this data for games requirement for each turn.
	 * @param turn determines how many turn game is played
	 */
	public void playGame(int turn) {
		displayTable();
		int point=0;
		
		for(int i=0;i<turn;i++) {
			int[][] temp= new int [this.row][this.column];
			for(int j=0;j<this.row;j++) {
				for(int k=0;k<this.column;k++) {
					if(j!=0) point+=this.table[j-1][k];
					if(j!=this.row-1) point+=this.table[j+1][k];
					if(k!=0) point+=this.table[j][k-1];
					if(k!=this.column-1) point+=this.table[j][k+1];
					if(j!=0 && k!=0) point+=this.table[j-1][k-1];
					if(j!=0 && k!=this.column-1) point+=this.table[j-1][k+1];
					if(j!=this.row-1 && k!=0) point+=this.table[j+1][k-1] ;
					if(j!=this.row-1 && k!=this.row-1) point+=this.table[j+1][k+1];
					
					if(this.table[j][k]==0) {
						if(point==3) temp[j][k]=1;
						else temp[j][k]=0;	
					}else {
						if(point!=2 && point!=3) temp[j][k]=0;
						else temp[j][k]=1;
					}
					point=0;
				}
			}
			for(int j=0;j<this.row;j++) {
				for(int k=0;k<this.column;k++) {
					this.table[j][k]=temp[j][k];
				}
			}
			displayTable();
		}
	}
	/**
	 * Displays table to console.
	 */
	public void displayTable() {
		for(int i=0;i<this.row;i++) {
			for(int j=0;j<this.column;j++) {
				System.out.print(this.table[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
