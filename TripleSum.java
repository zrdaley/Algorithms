/*
Input: An array 𝐴 of 𝑛 non-negative integers.

Output: A boolean value (true or false). If there are three indices 𝑖, 𝑗, 𝑘 such that 0 ≤ 𝑖, 𝑗, 𝑘 ≤
𝑛 − 1 and 𝐴[𝑖] + 𝐴[𝑗] + 𝐴[𝑘] = 225, the output will be true. If no such indices exist, the output will be false. Note that 𝑖, 𝑗 and 𝑘 do not have to be distinct.
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.lang.Math;

public class TripleSum {

	public static boolean TripleSum225(int[] A, int n){
		
		int a, b, c, start, end;

		
		//3Sum method to determine if a 3 int 225 sum is in the array (will always be constant time since n <= 226)
		for(int i = 0; i < n; i++){
			a = A[i];
			start = i;
			end = n - 1;

			while(start <= end) {
				b = A[start];
				c = A[end];

				if((a + b  + c) == 225)
					return true;
				else if ((a + b + c) > 225)
					end--;
				else
					start++;
			}
		}
		return false;
	}



	public static int countSort(int[] A, int n){

		int array[] = new int[226];
		Arrays.fill(array,0);

		//checks array for any values <= 225
		for(int i = 0; i < n; i++){
			if(A[i] <=  225)
				array[A[i]]++;
		}

		//set array to 0
		Arrays.fill(A,0);
		int counter = 0;

		//fill array with sorted contents of integers <= 225 of old array
		for(int i = 0; i < array.length; i++){
			if(array[i] >= 1){
				A[counter] = i;
				counter++;
			}
		}

		//find length of new array (<=226)
		counter = 0;
		for(int i = 0; i < A.length - 1; i++){
			if(A[i + 1] < A[i])
				break;
			counter++;
		}

		//return new length
		return ++counter;
	}



	/* isSorted()
	 Check whether or not the given array is successfully sorted.
	 If it is, return true; otherwise return false.
	*/
    public static boolean isSorted(int[] A, int le, int ri){
    	for (int i= le+1;i<=ri;i++)
    		if (A[i]<A[i-1]) return false;
    	return true;
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

		int newArrayLength = countSort(array, array.length);

		boolean pairExists = TripleSum225(array, newArrayLength);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.printf("Array %s a pair of values which add to 225.\n",pairExists? "contains":"does not contain");
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);


	}

		

}
