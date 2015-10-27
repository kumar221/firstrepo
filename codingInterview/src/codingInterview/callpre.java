package codingInterview;


import java.io.File;

import java.io.IOException;

public class callpre {
public static void main(String args[]) throws IOException{
	
	appleinterview ap=new appleinterview();
	File file = new File("1.txt");
	ap.call(file);
	System.out.println("Done");
}
}
