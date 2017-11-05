package happenbefore;
/**
 * This class just creates one thread and its duty is add final part of string with using semaphore for avoid race condition
 * @author kutay
 *
 */
public class HappenThread3 extends Thread{
	BinarySemaphore b1;
	BinarySemaphore b2;
	String[][] s;
	public HappenThread3(BinarySemaphore b1,BinarySemaphore b2, String[][] s) {
		this.b1=b1;
		this.b2=b2;
		this.s=s;
		new Thread(this).start();
	}
	public void run() {
		b2.P();
		s[0][0]+=" at the end!";
		//Race condition detection
		if(s[0][0].equalsIgnoreCase("This string should look like this at the end!")) {
			System.out.println("No race condition in example 1.");
		}
		else {
			System.out.println("Race condition detected in example 1.");
		}
	}
}
