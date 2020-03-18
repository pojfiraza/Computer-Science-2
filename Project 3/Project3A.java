import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project3A {
	//Main Method
	public static void main(String[]args){

		boolean bool = false;
		String temp = null;
		BoxofProduce x = new BoxofProduce();
		//Opens while menu is not closed.
		while(bool != true){
			bool = menu(temp,x,bool);
		}
		System.out.println("Thank you for using the app!");
			
	}
	//Created This Method Just to make main look smaller(Menu List)(Albeit Slightly Inefficient due to opening/closing of scanner)
	public static boolean menu(String temp, BoxofProduce x,boolean bool){
		Scanner obama = new Scanner(System.in);
		System.out.println("The produce boxes are :"+x.toString());
		System.out.printf("Do you want to change the contents?\n[1]Yes \n[2]No \n");
		try{
		short choice = obama.nextShort();
		if(choice == 1){
				System.out.printf("What Produce do you want?\n[1]Broccoli \n[2]Tomato \n[3]Kiwi \n[4]Kale \n[5]Tomatillo \n");
				choice = obama.nextShort();
				switch(choice){
					default: temp = "Problem";
					break;
					case 1: temp = "Broccoli";
					break;
					case 2: temp = "Tomato";
					break;
					case 3: temp = "Kiwi";
					break;
					case 4: temp = "Kale";
					break;
					case 5: temp = "Tomatillo";
					break;
				}
				System.out.println("Insert Box Number:"+ x.toString());
				choice = obama.nextShort();
				x.changeBox(choice,temp);
				
		}else{
			bool = true;
		}
		}
		catch(InputMismatchException e){
			System.out.println("Wrong Input.Try Again");
		}
		return bool;
		
	}	
}
// The Box of Produce Class
class BoxofProduce{
	
	//Three instance variable for the boxes
	static private String a = new String();
	static private String b = new String();
	static private String c = new String();
	
	//Reads a file and create the object
	BoxofProduce(){
		//File Location(Need to Change)
		final String Produce = "C:/Users/Paul/Desktop/Ecllipse/Project3/src/Produce.txt";

		BufferedReader buf = null;
		FileReader fil = null;
		
		try {
			fil = new FileReader(Produce);
			buf = new BufferedReader(fil);

			String sCurrentLine;			
			// Random 3 times in a number 1-5 and get the produce based on the number.
			for(short count = 0; count< 3; count++){
				buf = new BufferedReader(new FileReader(Produce));
				int random = (int)(Math.random()*5)+1;
				int counter = 0;
				while ( (sCurrentLine = buf.readLine()) != null) {
					counter++;
					if(counter == random){
						switch(count){
							case 0: a = sCurrentLine;
							break;
							case 1: b = sCurrentLine;
							break;
							case 2: c = sCurrentLine;
							break;
						}
					}
				}

			}
		}
		//Print if not found
		catch (IOException e) {
				e.printStackTrace();
		} 
		finally {

			try {
				if (buf != null)buf.close();
				if (fil != null)fil.close();
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	// Return a String of the Boxes
	public String toString(){
		String back = "[1]"+a+" [2]"+b+" [3]" +c+ ".";
		return back;
	}
	// ChangeBox
	void changeBox(short x, String y){
		switch(x){
			case 1: a = y;
			break;
			case 2: b = y;
			break;
			case 3: c = y;
			break;
		}
	}
	
}
	