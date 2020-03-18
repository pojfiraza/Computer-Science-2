import java.util.Scanner;



public class Project2 {
	
	//Initialize Final Variables
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	//oa is a global variable for the specific column the piece is placed.
	private static int oa;
	
	public static void main(String[] args) {
		
		Scanner obama = new Scanner(System.in);
		System.out.print("Welcome to Connect Four.\n[1]New Game\n[2]Exit\n");
		int choice = obama.nextInt();
	
		int count = 1;
		int change = 0;
		boolean win = false;
		switch(choice){
			case 1 : 
				int[][]arr = new int[ROWS][COLUMNS];
				printArray(arr);
				while(count <= 42){
					change = playerTurn(count);
					arr=changeBoard(count,change,arr);
					printArray(arr);
					win = verifyWin(count,change,arr);
					if(win == true){
						String s =(count%2 == 1)?"Red":"Yellow";
						System.out.println("The " + s + " player wins.");
						break;
					}
					count++;
				}
				break;
			case 2 : 
				break;
		}
		if(count >= 42)System.out.println("No more space.Its a draw.");
		System.out.println("Thank you for playing.Have a nice day!");
	
		obama.close();
	}

	// Determines Player Turn, then return choice
	@SuppressWarnings("resource")
	private  static int playerTurn(int count){
		Scanner obama = new Scanner(System.in);
		int change = -1;
		if(count%2 == 1){
			System.out.println("Drop a red disk at column(0-6):");
			change = obama.nextInt();
			return change;			
		}
		else{
			System.out.println("Drop a yellow disk at column(0-6):");
			change = obama.nextInt();
			return change;	
		}
	}
	
	// Prints the Board
	private static void printArray(int[][] arr){
		String a = null;
		for(int count1 = 0; count1<ROWS; count1++){
			System.out.print("|");
			for(int count2 = 0; count2<COLUMNS; count2++){
				switch(arr[count1][count2]){
					case 1: a = "R";
					break;
					case 2: a = "Y";
					break;
					default: a = " ";
					break;
				}
				System.out.print(a +"|" );
			}
			System.out.print("\n");
		}
		System.out.println("---------------");
	}
	//Add changes to the board
	private static int[][] changeBoard(int count,int change, int[][]arr){
		int count1;
		for(count1 = ROWS-1; count1 >= 0; count1--){
			if(arr[count1][change] != 0){
				continue;
			}else{
				arr[count1][change] =(count%2 == 1)?1:2;
				oa = count1;
				return arr;
			}
		}
		//If the area if full.Repeat Turn.
		if(count1 <0){
			System.out.println("The area is full,pick another.");
			change = playerTurn(count);
			changeBoard(count,change,arr);
		}
		return arr;
	}
	//Search for Win Status
	private static boolean verifyWin(int turn,int change,int[][] arr){
		//Horizontal Search
		int connect = 1;
		for(int count = 0; count<COLUMNS-1; count++){
			if(arr[oa][count] == arr[oa][count+1] && arr[oa][count] != 0){
				connect++;
				if(connect == 4)return true;
			}else{
				connect = 1;
			}
		}
		//Vertical Search
		connect = 1;
		for(int count = 0; count<ROWS-1; count++){
			if(arr[count][change] == arr[count+1][change] && arr[count][change] != 0){
				connect++;
				if(connect == 4)return true;
			}else{
				connect = 1;
			}
		}
		//Diagonal Right Search
		connect = 1;
		int first= ((oa-change)<0)?0:oa-change;
		int second = ((change-oa)<0)?0:change-oa;
		for(int count = 0; count<COLUMNS; count++){
			if((first+count)>ROWS-2 || (second+count)>COLUMNS-2)break;
			if(arr[first+count][second+count] == arr[first+count+1][second+count+1] && arr[first+count][second+count] != 0){
				connect++;
				if(connect == 4)return true;
			}else{
				connect = 1;
			}
		}
		//Diagonal Left Search
		connect = 1;
		first= (oa-((COLUMNS-1)-change) < 0)?0:oa-((COLUMNS-1)-change);
		second = ((change+oa)>COLUMNS-1)?COLUMNS-1:change+oa;
		for(int count = 0; count<COLUMNS; count++){
			if((first+count)>ROWS-2 || (second-count)<1)break;
			if(arr[first+count][second-count] == arr[first+count+1][second-(count+1)] && arr[first+count][second-count] != 0){
				connect++;
				if(connect == 4)return true;
			}else{
				connect = 1;
			}
		}
		return false;
	}
	

}
