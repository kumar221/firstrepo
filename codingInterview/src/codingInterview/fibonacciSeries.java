package codingInterview;

import java.util.Scanner;

public class fibonacciSeries {
	
	public void printseries(int k){
		int fib[]=new int[k];
		fib[0]=0;
		fib[1]=1;
		for(int i=2; i<k;i++){
			fib[i]=fib[i-2]+fib[i-1];
		}
	
		for(int j=0;j<k;j++){
			System.out.println(fib[j]);
		}
	}
public static void main(String args[]){
	fibonacciSeries fb=new fibonacciSeries();
	System.out.println("How many numbers do you want to print ");
	Scanner sc=new Scanner(System.in);
	int num=sc.nextInt();
	sc.close();
	fb.printseries(num);
}
	}

