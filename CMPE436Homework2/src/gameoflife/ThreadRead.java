package gameoflife;
/**
 * This class initialize and run Threads for read and write each cell
 * @author kutay
 *
 */
public class ThreadRead extends Thread {
	int row;
	int column;
	int solution;
	int[][] matrix;
	int round;
	ReadWrite rw;
	/**
	 * Thread initialization and starting
	 * @param x row number
	 * @param y column number
	 * @param matrix main matrix table
	 * @param s ReadWrite class for semaphores
	 * @param r number of generations
	 */
	public ThreadRead (int x,int y,int[][] matrix,ReadWrite s,int r) {
		this.row=x;
		this.column=y;
		this.matrix=matrix;
		this.rw=s;
		this.round=r;
		new Thread(this).start();
	}
	/**
	 * This run is for all Threads.
	 * First is start to read after write for each generation
	 */
	public void run(){
		for(int i=0;i<this.round;i++) {
			rw.startRead();
			int totalNum= matrix[row-1][column-1] +  matrix[row-1][column] +  matrix[row-1][column+1] + 
					matrix[row][column-1] + matrix[row][column+1] +  
					matrix[row+1][column-1] +  matrix[row+1][column] +  matrix[row+1][column+1] ;
			if(matrix[row][column]==1) {
				if(totalNum==2 || totalNum==3) 
					this.solution=1;
				else
					this.solution=0;
			}else {
				if(totalNum==3)
					this.solution=1;
				else
					this.solution=0;
			}
			//System.out.println(row + " " + column + " Solution is:" + solution);
			rw.endRead();
			rw.startWrite();
			matrix[row][column]=solution;
			rw.endWrite();
		}
	}
	
	
}
