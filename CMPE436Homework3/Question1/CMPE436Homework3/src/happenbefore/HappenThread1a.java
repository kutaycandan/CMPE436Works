package happenbefore;
/**
 * This class just creates one thread and its duty is add first part of string without using semaphore 
 * @author kutay
 *
 */
public class HappenThread1a extends Thread{
	BinarySemaphore b1;
	BinarySemaphore b2;
	String[][] s;
	public HappenThread1a(BinarySemaphore b1,BinarySemaphore b2, String[][] s) {
		this.b1=b1;
		this.b2=b2;
		this.s=s;
		new Thread(this).start();
	}
	public void run() {
		s[0][0]+="This string should";
	}
}
