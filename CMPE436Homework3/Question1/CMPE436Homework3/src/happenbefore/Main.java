package happenbefore;


/**
 * This is main class of happens before relationship example
 * @author kutay
 *
 */
public class Main {
	/**
	 * Create 2 string for 2 example in first example there is a order between threads and it has no race condition
	 * But in second example I did not add semaphore for ordering so race condition occurs.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] s=new String[1][1];
		s[0][0] = "";
		String[][] s1=new String[1][1];
		s1[0][0] = "";
		String real="This string should look like this at the end!";
		BinarySemaphore b1 = new BinarySemaphore(false);
		BinarySemaphore b2 = new BinarySemaphore(false);
		Thread a=new HappenThread3(b1, b2, s);
		Thread b=new HappenThread2(b1, b2, s);
		Thread c=new HappenThread1(b1, b2, s);
		BinarySemaphore b3 = new BinarySemaphore(false);
		BinarySemaphore b4 = new BinarySemaphore(false);
		Thread aa=new HappenThread3a(b3, b4, s1);
		Thread bb=new HappenThread2a(b3, b4, s1);
		Thread cc=new HappenThread1a(b3, b4, s1);
		
		
	}

}
