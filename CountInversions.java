/*
The programming assignment is to implement an algorithm which counts the number of inversions in an input sequence:

Input: An array 𝐴 of 𝑛 integers in the range 1 − 𝑛.
Output: An integer, corresponding to the number of inversions in 𝐴.
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.lang.Math;

public class CountInversions {

	public static int CountInversions(int[] A){
		int count = 0;
		int curr;
		int temp;
		for(int i = 1; i < A.length; i ++){
			curr = i;
			//swap
			while(curr > 0 && A[curr-1] > A[curr]){
				temp = A[curr-1];							
				A[curr - 1] = A[curr];
				A[curr] = temp;
				curr --;
				count ++;
			}
		}
		return count;
	}


	/* main()
	 Contains code to test the PairSum225 function.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);

		long startTime = System.currentTimeMillis();

		//System.out.printf(Arrays.toString(array));
		
		//call function here
		int inversions = CountInversions(array);

		
		//run time
		long endTime = System.currentTimeMillis();
		double totalTimeSeconds = (endTime-startTime)/1000.0;
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);

		System.out.printf("Number of inversions: %d\n", inversions);


	}

		

}
