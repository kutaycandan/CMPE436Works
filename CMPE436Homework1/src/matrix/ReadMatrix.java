package matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This class is for reading matrix data from file properly.
 * @author kutay
 *
 */
public class ReadMatrix {
	String matrixFile;
	int[][] matrixdata;
	boolean isMatrix;
	String matrixFailureNote;
	/**
	 * Construction function of ReadMatrix. Also sets matrix name and starts to read file.
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public ReadMatrix(String fileName) throws FileNotFoundException {
		this.matrixFile=fileName;
		readMatrix();
	}
	/**
	 * Returns this file correctly puts matrix data
	 * @return is matrix or not
	 */
	public boolean isMatrix() {
		return this.isMatrix;
	}
	/**
	 * Returns matrix if is correctly put
	 * @return matrix double int array
	 */
	public int[][] returnMatrix(){
		return this.matrixdata;
	}
	/**
	 * Returns failure message if it is not matrix
	 * @return Failure message
	 */
	public String returnFailureNote() {
		return this.matrixFailureNote;
	}
	/**
	 * This function reads matrix. If matrix is properly given, writes matrix. Else, writes failure message and sets isMatrix false
	 */
	public void readMatrix() {
		try {
			String file=this.matrixFile;
			Scanner input = new Scanner (new File(this.matrixFile));
			int row=0;
			int column=0;
			String s="";
			if(input.hasNextLine()) {
				s=input.nextLine().trim();
				String[] temp = s.split("\\s+");
				if(temp.length!=2) {
					this.matrixFailureNote="Not valid row and colunm on file: "+file;
					this.isMatrix=false;
					return;
				}else {
					try {
						row=Integer.valueOf(temp[0]);
						column=Integer.valueOf(temp[1]);
					}
					catch(NumberFormatException e) {
						this.matrixFailureNote="Row and column must be integer on file: "+file;
						this.isMatrix=false;
						return;
					}
				}
			}else{
				this.matrixFailureNote="File: "+file+" is empty.";
				return;
			}
			int[][] matrix=new int[row][column];
			int tempRow=0;
			while(input.hasNextLine()) {
				tempRow++;
				if(tempRow>row) {
					this.matrixFailureNote="Row is declared less then matrix on file: "+file;
					this.isMatrix=false;
					return;
				}
				s=input.nextLine().trim();
				String[] temp=s.split("\\s+");
				//System.out.println(Arrays.asList(temp)+ " "+temp.length+ " " + column);
				if(temp.length!=column){
					this.matrixFailureNote="Number of column is not match at row: "+tempRow+" on file: "+file;
					this.isMatrix=false;
					return;
				}else {
					try {
						for(int i=0;i<temp.length;i++) {
							int node=Integer.valueOf(temp[i]);
							matrix[tempRow-1][i]=node;
						}
					}catch(NumberFormatException e) {
						this.matrixFailureNote="Some data are not integer at row: "+tempRow+" on file: "+file;
						this.isMatrix=false;
						return;
					}
				}
				
			}
			
			if(tempRow!=row) {
				this.matrixFailureNote="Row is declared is more then matrix on file: "+ file;
				this.isMatrix=false;
				return;
			}
			this.isMatrix=true;
			this.matrixdata=matrix;
			
		}
		catch(FileNotFoundException e){
			this.matrixFailureNote="File: "+ this.matrixFile+ " is not found.";
			this.isMatrix=false;
			return;
		}
	}
	
}
