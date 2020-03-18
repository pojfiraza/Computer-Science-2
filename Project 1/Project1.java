//Paul Firaza
//Project_1

import java.util.Scanner;
public class Project1 {
	
	//Opening Sequence
	public static void main(String[] args){
		Scanner obama = new Scanner(System.in);
		long number;
		String answer = null;
	
		System.out.println("Welcome to Credit Card App! Insert your Card Number.");
		number = obama.nextLong();
		
		answer = (isValid(number) == true)?"valid":"invalid";
		System.out.println(number +" is "+ answer);
		
		System.out.println("Thank you for using the Card App!Have a Great Day.");
		obama.close();
	}
	// Returns boolean whether the car is valid or not
	public static Boolean isValid(long number){
		
		Boolean result;
		int sum, even, odd;
	//Find the sum and use modulus to find if it divisible by 10
		odd = sumOfoddPlace(number);
		even = sumOfDoubleEvenPlace(number);
		sum = odd + even; 
		
		result =(sum%10 == 0)?true:false;
		
		
		return result;
	}
	// Find the even sides and double the value
	public static int sumOfDoubleEvenPlace(long number){
		
		int answer = 0;
		int size = getSize(number);
	//Since it prefix, i reverse the logic in order to start on the left, than right
		int count =(size%2 == 0)?1:0;
	
	// It calls the getPrefix to get a prefix and utilize it
		while(count <= size){
			long suffix = getPrefix(number,count);
			while(suffix>9){
				suffix%=10;	
			}
			suffix = ((suffix*2) > 9)?getDigit((int)suffix*2):(suffix*2);		
			answer += suffix;
			count+=2;

		}
		return answer;
	
	}
	//Gets the single digit from the double digit
	public static int getDigit(int number){
		int result;
		result = (number%10) + 1;
		return result;
	}
	//Sum of Odd Sides
	public static int sumOfoddPlace(long number){
		int answer = 0;
		int size = getSize(number);	
	//Same Formula Different Place
		int count =(size%2 == 0)?0:1;

		while(count <= size){
			long suffix = getPrefix(number,count);
			while(suffix>9){
				suffix%=10;
			}
			answer += suffix;

			count+=2;
		}
		return answer;
	}
	// Find the Length of the Digit
	public static int getSize(long d){
		
		int length = (int) Math.log10(d) + 1;		
		return length;
	}
	
// When you mean prefix you mean the left side? Isn't more practical if its suffix? Oh well.
	public static long getPrefix(long number, int k){
		long answer = 0;
		int size = getSize(number);
		answer= number/(long)Math.pow(10,(size - k));
			
		return answer;
	}
	
}
