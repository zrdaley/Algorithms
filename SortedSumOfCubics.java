/*
Write a program that prints out all integers of the form 𝑎3 + 𝑏3, where 𝑎 and 𝑏 are integers in the range [0, 𝑛], in sorted order while using 𝑂(𝑛) space. That is, you cannot use an array of size 𝑛2 and then sort it.

Input: A nonnegative integer n.
Output: A sorted list of all integers of the form 𝑎3 + 𝑏3, where 𝑎 and 𝑏 are integers in the range [0, 𝑛].
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.lang.Math;

public class SortedSumOfCubics {


	public static class node {

		public int val;
		public int a;
		public int b;

		/* node constructor */
		public node(int x, int i, int j) {
			val = x;
			a = i;
			b = j;
		}
	}

	public static class Heap {
		public int size;
		public node [] heap;

		/* heap constructor */
		public Heap(int n) {
			size = 0;
			heap = new node[n+1];
		}

		/* insert item into heap */
		public void insert(int a, int b) {
			//create node to be inserted
			int key = a*a*a + b*b*b;
			node newNode = new node(key, a, b);

      		//Insert new node to the end of the array
      		heap[size++] = newNode;

      		bubbleUp(size-1);
   		}


   		private void bubbleUp(int k) {
            int parent;
            node temp;

            if (k != 0) {
            	parent = (k-1)/2;
            	if(heap[parent].val > heap[k].val){
            		temp = heap[parent];
            		heap[parent] = heap[k];
            		heap[k] = temp;
            		bubbleUp(parent);
            	}
            }

     	}

   		/* delete minimum */
   		public node deleteMin() {
     		 node min = heap[0];
     		 heap[0] = heap[--size];
     		 if(size > 0)
     			 bubbleDown(0);
      		return min;
		}

		/* bubbles node down after insertion */
		private void bubbleDown(int k) {
      		node temp;
      		int min;

      		int left = 2 * k + 1;
         	int right = left + 1;

 
         	if(right >= size){
         		if(left >= size)
         			return;
         		else
         			min = left;
         	}
         	else {
         		if(heap[left].val <= heap[right].val)
         			min = left;
         		else
         			min = right;
         	}
  
         	if (heap[k].val > heap[min].val){
         		temp = heap[min];
         		heap[min] = heap[k];
         		heap[k] = temp;
         		bubbleDown(min);
         	}
         	
        }
     }


	public static void SortedSumOfCubics(int n){
		int a = 0;
		int b = 0;
		node min;

		Heap heap = new Heap(n);

		//node[] array = new node[n*n + 1];
		
		while(heap.size <= n){
			heap.insert(a, b);
			a++;
		}
		int i = 0;
		while(heap.size > 0){	
			min = heap.deleteMin();

			System.out.print(min.val + " ");
			//array[i] = min;
			//i++;

			if(min.b < min.a){
				heap.insert(min.a, (min.b + 1));
			}
		}

		//System.out.println(isInOrder(array, i));


	}

	public static boolean isInOrder(node[] array, int n){
		
		for(int i = 0; i < n - 1; i++){
			if(array[i].val > array[i+1].val)
				return false;
		}
		return true;
	}


	public static void main(String[] args){
		
		//take in integer value 
		Scanner scan = new Scanner(System.in);
		System.out.printf("Enter a positive integer: ");
		int n = scan.nextInt();

		//start timer
		long startTime = System.currentTimeMillis();

		System.out.printf("The sorted sum of the cubics of %d are: \n", n);
		
		//pass integer into cubing function
		SortedSumOfCubics(n);

		System.out.println();

		//print time to find cubic sum
		long endTime = System.currentTimeMillis();
		double totalTimeSeconds = (endTime-startTime)/1000.0;
		System.out.printf("Total Time of SortedSumOfCubics algorithm (seconds): %.4f\n",totalTimeSeconds);


	}

}
