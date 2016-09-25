import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.Deque;

public class SolveNinePuzzle {

	/* Class to store a board state */
	public static class Board {
		int[][] B;
		Board parent;
		String board;
		Vector <int[][]> children = new Vector<int[][]>();
		
		public Board(int[][] array){
			B = array;
			board = Arrays.deepToString(array);
			findChildren();
		}

		/* findChildren()
	 	  finds all of the possible children of the board (2,3 or 4)
	 	 */ 
		public void findChildren(){

			//find vertex of board
			int vx = 0, vy = 0;
			for (int i = 0; i < 3; i++){
				for(int j = 0; j < 3; j++){
					if (B[i][j] == 0){
						vy = i;
						vx = j;
					}
				}
			}

			int [][] tempR = deepCopy(B);
			if((vx+1) < 3){
				
				int temp = tempR[vy][vx+1];
				tempR[vy][vx+1] = 0;
				tempR[vy][vx] = temp;

				children.add(tempR);
			}

			int [][] tempU = deepCopy(B);
			if((vy+1) < 3){
				int temp = tempU[vy+1][vx];
				tempU[vy+1][vx] = 0;
				tempU[vy][vx] = temp;

				children.add(tempU);
			}

			int [][] tempL = deepCopy(B);
			if((vx - 1) >= 0){
				int temp = tempL[vy][vx-1];
				tempL[vy][vx-1] = 0;
				tempL[vy][vx] = temp;

				children.add(tempL);
			}

			int [][] tempD = deepCopy(B);
			if((vy - 1) >= 0){
				int temp = tempD[vy-1][vx];
				tempD[vy-1][vx] = 0;
				tempD[vy][vx] = temp;

				children.add(tempD);

			}
		}
	}

	/* deepCopy(int[][] OG)
	  makes a deep copy of the 2d array passed in
	  Input:
	 		int[][] OG - the 2d array to be copied
	 	
	  Returns:
	 		a copy of the 2d array passed in
	 */ 
	public static int[][] deepCopy(int[][] OG){
		int[][] temp = new int[OG.length][];
      	for (int i = 0; i < OG.length; i++) {
        	temp[i] = Arrays.copyOf(OG[i], OG[i].length);
      	}
      	return temp;
	}


	/* ninePuzzleBFS(int[][] B, int[][] G)
	  Contains code to determine whether a 9X9 board can be solved using breadth first searching
	  Input:
	 		int[][] B - the starting state of the board
	 		int[][] G - the desired solved state of the board
	 	
	  Returns:
	 		True if the solved state can be reached by the starting state
	 		False otherwise
	 */ 
	public static void printBoard(int[][] array){
		System.out.println(array[0][0] + " " + array[0][1] + " " + array[0][2]);
		System.out.println(array[1][0] + " " + array[1][1] + " " + array[1][2]);
		System.out.println(array[2][0] + " " + array[2][1] + " " + array[2][2]);
	}


	/* printPath(Board end)
	  Contains code to print the path taken to solve puzzle during ninePuzzleBFS
	  Input:
	 		Board end - the last board of the path (end.B should be equal to the solved state)
	 	
	  Output:
	 		The path taken to solve (2D arrays of boards)
	 */
	public static void printPath(Board end){
		Board curr = end;
		Vector<Board> path = new Vector<Board>();

		while(true){
			path.add(curr);
			if(curr.parent == null)
				break;
			curr = curr.parent;
		}

		while(path.size() > 0){
			printBoard(path.remove(path.size()-1).B);
			System.out.println();	
		}
	}


	/* ninePuzzleBFS(int[][] B, int[][] G)
	  Contains code to determine whether a 9X9 board can be solved using breadth first searching
	  Input:
	 		int[][] B - the starting state of the board
	 		int[][] G - the desired solved state of the board
	 	
	  Returns:
	 		True if the solved state can be reached by the starting state
	 		False otherwise
	 */
	public static Boolean ninePuzzleBFS(int[][] B, int[][] G){
		
		Deque<Board> q = new ArrayDeque<Board>();
		Set<String> discovered = new HashSet<String>();

		Boolean found = false;
		String solved = Arrays.deepToString(G);

		Board root = new Board(B);
		discovered.add(root.board);
		q.add(root);
		Board curr = root;

		//printPath(root);

		while(!q.isEmpty()){
			curr = q.remove();

			if(curr.board.equals(solved)) {
				found = true;
				break;
			}
			for(int i = 0; i < curr.children.size(); i++){
				if(!discovered.contains(Arrays.deepToString(curr.children.get(i)))){
					Board next = new Board(curr.children.get(i));
					next.parent = curr;

					discovered.add(next.board);
					q.add(next);

				}
			}
		}

		if (found == true){
			printPath(curr);
			return true;
		}
		return false;
	}


	/* main()
	 Contains code to take 9X9 boards with numbers ranging from 0-8 input from a .txt file
	 	example format:
	 
	 	1 2 3
	 	4 5 6
	 	7 8 0
	 
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
		}
		else{
			s = new Scanner(System.in);
			System.out.printf("Enter 0 - 8 separated by spaces. Enter a negative value to end the list.\n");
		}

		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		int length = 1;
		while(s.hasNext() && (v = s.nextInt()) >= 0){
			inputVector.add(v);
			length++;
		}

		Boolean solved;
		String sol;
		int[][] array = new int[3][3];
		for(int i = 0; i < (length/9) ; i++){ //(length/9)

			//store board in 3-d vector
			for (int j = 0; j < 3; j++){
				for (int k = 0; k < 3; k++)
					array[j][k] = inputVector.get((i*9)+k+(3*j));
			}

			//print initial board
			System.out.printf("Reading board %d.\n", i+1);
			printBoard(array);

			long startTime = System.currentTimeMillis();


			//desired board state
			int[][] G = new int[][]{
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 0}
			};
			
			//solve puzzle
			System.out.printf("Attempting to solve...\n");
			
			solved = ninePuzzleBFS(array, G);
			if(solved)
				sol = "Solvable";
			else
				sol = "Not Solvable";
			

			//print stats
			long endTime = System.currentTimeMillis();
			double totalTimeSeconds = (endTime-startTime)/1000.0;
			System.out.printf("Board %d: " + sol + "\n", i+1);
			System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
		}
	}	
}