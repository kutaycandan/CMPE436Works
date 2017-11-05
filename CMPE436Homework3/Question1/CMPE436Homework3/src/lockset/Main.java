package lockset;

import java.util.ArrayList;

/**
 * This is main class of lockset algorithm
 * @author kutay
 *
 */
public class Main {
	/**
	 * Create 2 string and 2 integer for 2 example in first example I use similar type threads and they locks with using same semaphores
	 * for similar variables. So lockset dont find race condition.
	 * But in second example I use different type of thread and they locks with using different semaphores for similar variables.
	 * So lockset detects a race condition.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[][] s=new String[1][1];
		s[0][0]="kutay";
		int[][] a=new int[1][1];
		a[0][0]=1;
		
		BinarySemaphore[] locks = new BinarySemaphore[10];
		//These are for example 1
		ArrayList<BinarySemaphore> forinteger= new ArrayList<BinarySemaphore>();
		ArrayList<BinarySemaphore> forstring= new ArrayList<BinarySemaphore>();
		//These are for example 2
		ArrayList<BinarySemaphore> forinteger2= new ArrayList<BinarySemaphore>();
		ArrayList<BinarySemaphore> forstring2= new ArrayList<BinarySemaphore>();
		for(int i=0;i<10;i++) {
			BinarySemaphore b = new BinarySemaphore(true);
			locks[i]=b;
			forinteger.add(b);
			forstring.add(b);
			forinteger2.add(b);
			forstring2.add(b);
		}
		//First example ends Second example start
		int exampletype=1;
		String[][] s1=new String[1][1];
		s1[0][0]="kutay";
		int[][] a1=new int[1][1];
		a1[0][0]=1;
		for(int i=1;i<11;i++) {
			
			Thread t=new LockSetThread(locks,s,a,i,forinteger,forstring,exampletype);
		}
		exampletype=2;
		for(int i=1;i<11;i++) {
			Thread t=new LockSetThread(locks,s1,a1,i,forinteger2,forstring2,exampletype);
			Thread t2=new LockSetThread2(locks,s1,a1,i,forinteger2,forstring2,exampletype);
		}
	}

}
