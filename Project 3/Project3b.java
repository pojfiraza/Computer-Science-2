import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project3b {
	//Main Method
	public static void main(String[]args){

		String temp = null;
		boolean bool2 = false;
		//Opens while menu is not closed.
		while(bool2 != true){
			BoxofProduce x = new BoxofProduce();
			boolean bool = false;
			while(bool != true){
				bool = menu(temp,x,bool);
			}
			x.removeFlyer();
			bool2 = repeatMenu(bool2);
		}
		
		System.out.println("Thank you for using the app!");
			
	}
	//Created This Method Just to make main look smaller(Menu List)(Albeit Slightly Inefficient due to scanner)
	public static boolean menu(String temp, BoxofProduce x,boolean bool){
		Scanner obama = new Scanner(System.in);
		System.out.println("The produce box contains :"+x.toString());
		System.out.printf("Do you want to change the contents?\n[1]Yes \n[2]No \n");
		//Try if they Put nonViable Input
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
		//Gives a Print of Wrong Input.
		catch(InputMismatchException e){
			System.out.println("Wrong Input.Try Again.");
		}
		
		return bool;
		
	}
	// Repeat Menu
	public static boolean repeatMenu( boolean bool2){
		short choice = 0;
		try{
		System.out.println("Do you want another Produce Box?[1]Yes [2]No");
		Scanner michelle = new Scanner(System.in);
		choice = michelle.nextShort();
		switch(choice){
			case 1 : bool2 = false;
			break;
			case 2: bool2 = true;
			break;
		}
		}
		catch(InputMismatchException e){
			System.out.println("Wrong Input.Try Again.");
		}

		return bool2;
	}
}
// The Box of Produce Class
class BoxofProduce{
	
	//Three instance variable for the boxes
	static private String a = new String();
	static private String b = new String();
	static private String c = new String();
	//Salsa Recipe Number List
	static private short flyer = 5;
	
	//Reads a file and create the object
	BoxofProduce(){
		//File Location(Need to Change if needed)
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
		//Catch and Prints a Back Trace of IOexception
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
		String salsa = "";
		if(a.equals("Tomatillo") || b.equals("Tomatillo") || c.equals("Tomatillo")){
			if(flyer != 0)salsa = "It contains a free Salsa Verde Recipe!";
		}
		String back = "[1]"+a+" [2]"+b+" [3]" +c+ "."+ salsa;
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
	//Remove flyer at the end of the final change
	public void removeFlyer(){
		if(a.equals("Tomatillo") || b.equals("Tomatillo") || c.equals("Tomatillo")){
			if(flyer != 0)flyer--;
		}
	}
	
}
	