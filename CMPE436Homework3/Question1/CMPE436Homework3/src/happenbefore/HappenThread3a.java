package happenbefore;
/**
 * This class just creates one thread and its duty is add final part of string without using semaphore 
 * @author kutay
 *
 */
public class HappenThread3a extends Thread{
	BinarySemaphore b1;
	BinarySemaphore b2;
	String[][] s;
	public HappenThread3a(BinarySemaphore b1,BinarySemaphore b2, String[][] s) {
		this.b1=b1;
		this.b2=b2;
		this.s=s;
		new Thread(this).start();
	}
	public void run() {
		s[0][0]+=" at the end!";
		//Race condition detection
		if(s[0][0].equalsIgnoreCase("This string should look like this at the end!")) {
			System.out.println("No race condition in example 2.");
		}
		else {
			System.out.println("Race condition detected in example 2.");
		}
	}
}
