package racecondition;
/**
 * This class creates threads and send them a pointer
 * @author kutay
 *
 */
public class Main {

	public static void main(String[] args) {
		int [][] matrix=new int[1][1];
		matrix[0][0]=1;
		
		
		Thread a= new Threads(matrix);
		Thread b= new Threads(matrix);
		Thread c= new Threads(matrix);
		

	}
	

}
