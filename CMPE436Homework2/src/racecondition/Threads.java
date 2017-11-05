package racecondition;


/**
 * This thread gets one pointer and inrements 1 for 1000000 times without lock.
 * It generates race condition
 * @author kutay
 *
 */
public class Threads extends Thread{
	int[][] matrix;
	
	int round;
	public Threads (int[][] matrix) {
		this.matrix=matrix;
		
		new Thread(this).start();
	}
	/**
	 * Increments pointer for 1000000 times
	 */
	public void run() {
		matrix[0][0]=0;
		for(int i=0;i<1000000;i++) {
			matrix[0][0]++;
		}
		System.out.println(matrix[0][0]);
	}
	
	
}
