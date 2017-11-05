package deadlock;
/**
 * This main class gets 3 thread and 3 Binary Semaphore each uses 2 semaphore but for one time each one gets 1 and waits others.
 * @author kutay
 *
 */
public class Main {
	/**
	 * Gets 2 semaphore each for terminating but if all gets 1 it becomes deadlock
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySemaphore a = new BinarySemaphore(true);	
		BinarySemaphore b = new BinarySemaphore(true);	
		BinarySemaphore c = new BinarySemaphore(true);	
		Thread i = new Thread(new Runnable() {           
            public void run() { 
                while(true) {
                		a.P();
                		b.P();
                		System.out.println("This is run1");
                		b.V();
                		a.V();
                }
            		
            } 
        });
		Thread j = new Thread(new Runnable() {           
            public void run() { 
            	while(true) {
            		b.P();
            		c.P();
            		System.out.println("This is run2");
            		c.V();
            		b.V();
            }
            		
            } 
        });
		Thread k = new Thread(new Runnable() {           
            public void run() { 
            	while(true) {
            		c.P();
            		a.P();
            		System.out.println("This is run3");
            		a.V();
            		c.V();
            }
            		
            } 
        });
        i.start();
        j.start();
        k.start();
	}

	

}
