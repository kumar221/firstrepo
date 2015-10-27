package codingInterview;

import java.util.Scanner;

public class numberReverse {
public int printreverse(int number){
	int revNumber=0;
	while(number != 0){
		revNumber=(revNumber*10)+(number%10);
		number=number/10;
	}
	return revNumber;
}
public static void main(String args[]){
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the Number");
	int number=sc.nextInt();
	sc.close();
	numberReverse nr=new numberReverse();
	System.out.println("The reverse number of the entered number is "+nr.printreverse(number));
}
}
