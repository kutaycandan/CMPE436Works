package lockset;

import java.util.ArrayList;
/**
 * This is first type of thread. At the end 10th thread also prints whether race condition occurs or not.
 * @author kutay
 *
 */
public class LockSetThread extends Thread {
	BinarySemaphore[] locks;
	String[][] s;
	int[][] num;
	String name;
	int id;
	ArrayList<BinarySemaphore> forinteger;
	ArrayList<BinarySemaphore> forstring;
	int exampletype;
	public LockSetThread(BinarySemaphore[] arr, String[][] s, int[][] num, int id,
			ArrayList<BinarySemaphore> forinteger,ArrayList<BinarySemaphore> forstring,int exampletype) {
		this.locks=arr;
		this.s=s;
		this.num=num;
		this.id=id;
		this.name="Thread "+id;
		this.forinteger=forinteger;
		this.forstring=forstring;
		this.exampletype=exampletype;
		new Thread(this).start();
		
	}
	public void run() {
		locks[3].P();
		locks[2].P();
		num[0][0]+=id;
		locks[2].V();
		locks[3].V();
		locks[8].P();
		for(int i=0;i<forinteger.size();i++) {
			if(forinteger.get(i).equals(locks[2]) || forinteger.get(i).equals(locks[3]) ) {
				//System.out.println("lock 2 or 3 ");
			}
			else {
				forinteger.remove(i);
			}
		}
		locks[8].V();
		//locks[0].P();
		locks[5].P();
		s[0][0]=name;
		locks[5].V();
		//locks[0].V();
		
		locks[9].P();
		for(int i=0;i<forstring.size();i++) {
			if(forstring.get(i).equals(locks[5]) ) {
				
			}
			else {
				forstring.remove(i);
			}
		}
		locks[9].V();
		if(name.equalsIgnoreCase("Thread 10") && exampletype==1) {
			System.out.println(forstring.size());
			System.out.println(forinteger.size());
			if(forinteger.size()==0) {
				System.out.println("Race condition detected in integer num in example 1");
			}
			else {
				System.out.println("No race condition detected in integer num in example 1");
			}
			if(forstring.size()==0) {
				System.out.println("Race condition detected in String s in example 1");
				
			}
			else {
				System.out.println("No race condition detected in String s in example 1");
			}
		}
	}
	
}
