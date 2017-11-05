package gameoflife;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Game of life class contains game and its start condition
 * @author kutay
 * @version 1.1
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
		this.row=M+2;
		this.column=N+2;
		this.table=new int [M+2][N+2];
		createRandomTable();
		
	}
	/**
	 * Construction function of game of life.Table's values are taken from file 
	 * @param M number of row of table
	 * @param N number of column of table
	 * @param name File name
	 */
	public GameOfLife(int M,int N,String name) {
		this.row=M+2;
		this.column=N+2;
		this.table=new int [M+2][N+2];
		createDefinedTable(name);
	}
	/**
	 * This function creates random 0 or 1 and puts table.
	 */
	public void createRandomTable() {
		for(int i=0;i<this.row;i++) {
			for(int j=0;j<this.column;j++) {
				int random=0;
				if(i==0 || i==this.row-1 || j==0 || j==this.column-1 ) {
					table[i][j]=0;
					
				}else {
					if(Math.random()>0.5) random=1;
					table[i][j]=random;
				}
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
						if(i==0 || i==this.row-1 || j==0 || j==this.column-1 ) {
							table[i][j]=0;
							
						}else 
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
	 * This function basically creates Threads for manage each cell
	 * .
	 * @param turn determines how many turn game is played
	 */
	public void playGame(int turn) {
		displayTable();
		//CountingSemaphore rmutex= new CountingSemaphore ((this.row-2)*(this.column-2));
		ReadWrite rw= new ReadWrite((this.row-2)*(this.column-2),table);
		for(int i=1;i<this.row-1;i++) {
			for(int j=1;j<this.column-1;j++) {
				Thread a= new ThreadRead(i,j,this.table,rw,turn);		
			}
		}
		//Thread a=new ThreadWrite(table,rw,turn);
	}
	/**
	 * Displays table to console.
	 */
	public void displayTable() {
		for(int i=1;i<this.row-1;i++) {
			for(int j=1;j<this.column-1;j++) {
				System.out.print(this.table[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
