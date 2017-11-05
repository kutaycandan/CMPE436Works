package gameoflife;
/**
 * This class influenced by normal read-write problem.
 * But this time I allow just multiple-read and multiple-write continuously(First read then write then read again...)
 * I use 4 Binary Semaphore for this process. 2 for cs. 2 for waiting each other for ordering.
 * @author kutay
 *
 */
public class ReadWrite {
	int numReaders=0;
	int numWriters=0;
	int count;
	int[][] matrix;
	BinarySemaphore mutex = new BinarySemaphore(true);
	BinarySemaphore mutex2 = new BinarySemaphore(true);
	//BinarySemaphore mutex3 = new BinarySemaphore(true);
	BinarySemaphore w = new BinarySemaphore(true);
	BinarySemaphore r = new BinarySemaphore(true);
	//BinarySemaphore print = new BinarySemaphore(true);
	/**
	 * Construction class for ReadWrite gets total number or read and write and matrix
	 * @param count total number of read and write
	 * @param matrix main matrix table
	 */
	public ReadWrite(int count, int[][] matrix){
		this.count=count;
		this.matrix=matrix;
	}
	/**
	 * Starting read and prevent to write
	 */
	public  void startRead() {
		r.P();
		mutex.P();
		numReaders++;
		if(numReaders==1)
			w.P();
			//mutex3.P();
		mutex.V();
		r.V();
	}
	/**
	 * End reading if each reading ends start writing
	 */
	public  void endRead() {
		mutex.P();
		
		if(numReaders==count) {
			w.V();
			numReaders =0;
		}
		mutex.V();
		//if(numReaders==0)
	}
	/**
	 * Starting write and prevent to read
	 */
	public void startWrite(){ 
		w.P();
		mutex2.P();
		numWriters++;
		if (numWriters == 1) {
			//mutex3.P();
			r.P();
		}
		mutex2.V();
		w.V(); 
	} 
	/**
	 * End writing if each writing is finished and display new matrix.
	 */
	public  void endWrite() {
		mutex2.P();
		
		if (numWriters == count) {
			System.out.println();
			displayTable();
			//mutex3.V();
			numWriters=0;
			r.V();
			
		}
		mutex2.V();
		
	}
	/*public void printStart() {
		mutex3.P();
	}
	public void printEnd() {
		mutex3.V();
		
	}*/
	/**
	 * Display matrix table
	 */
	public void displayTable() {
		for(int i=1;i<this.matrix.length-1;i++) {
			for(int j=1;j<this.matrix[0].length-1;j++) {
				System.out.print(this.matrix[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
