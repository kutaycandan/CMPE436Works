//Kutay Candan
//2013400003
//kutay.candan@gmail.com
//CMPE 436 - Assignment 1
package matrix;

import java.io.*;
import java.util.*;

/**
 * This is Main class for Matrix Multiply
 * @author kutay
 *
 */
public class Main {
	/**
	 * Main function gets file names and calls readmatrix class to read. 
	 * Then tries to multiply. If operation is successful, writes output to predetermined file.
	 * @param args 0.First matrix file 1.Second matrix file 2.Multiplied Matrix File
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//System.out.println("Hello World");
		String matrixFileName1=args[0];
		String matrixFileName2=args[1];
		String outMatrixName=args[2];
		File newFile = new File(outMatrixName);
		if (!newFile.exists()) {
	        newFile.createNewFile();
	    }
	    FileWriter fw = new FileWriter(newFile.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    ReadMatrix m1= new ReadMatrix(matrixFileName1);
	    ReadMatrix m2= new ReadMatrix(matrixFileName2);
	    
	    if(!m1.isMatrix) {
	    		String outputString=m1.matrixFailureNote;
	    		System.out.println(m1.matrixFailureNote);
	    		bw.write(outputString);
	    		
	    }else if(!m2.isMatrix) {
	    		String outputString=m2.matrixFailureNote;
	    		System.out.println(m2.matrixFailureNote);
	    		bw.write(outputString);
	    		
	    }else {
		    	if(m1.matrixdata[0].length==m2.matrixdata.length) {
				int[][] multMatrix=multiplyMatrix(m1.matrixdata,m2.matrixdata);
				bw.write(multMatrix.length + " " + multMatrix[0].length);
				System.out.println(multMatrix.length + " " + multMatrix[0].length);
				bw.newLine();
				for(int i=0;i<multMatrix.length;i++) {
					for(int j=0;j<multMatrix[0].length;j++){
						bw.write(multMatrix[i][j] + " ");
						System.out.print(multMatrix[i][j] + " ");
					}
					bw.newLine();
					System.out.println();
				}
				
				//System.out.println(Arrays.deepToString(multMatrix));
			}else {
				System.out.println("Dimensions are not matched.");
				bw.write("Dimensions are not matched.");			
			}
	    }
		bw.close();
		//System.out.println(matrix2.length+" "+matrix2[0].length);
		
		
	}
	/**
	 * This is classic matrix multiply function
	 * @param matrix1 First matrix
	 * @param matrix2 Secon matrix
	 * @return
	 */
	public static int[][] multiplyMatrix(int[][] matrix1,int[][] matrix2) {
		int[][] newMatrix = new int [matrix1.length][matrix2[0].length];
		for (int i=0;i<matrix1.length;i++){
            for (int j=0;j<matrix2[0].length; j++){
                for (int k=0;k<matrix2.length; k++){
                    newMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
		return newMatrix;
	}

}
