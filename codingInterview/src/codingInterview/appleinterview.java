package codingInterview;


import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;



public class appleinterview {
	
	public void call(File f) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(f));
		String s=null;
		while((s=br.readLine())!=null){
			System.out.println("");// just a change
			System.out.println("");// just a change 2 and 3 4334 now this is the 5 th change
			System.out.println(s);
			String[] y= s.split("\\s");
			for(String xy:y){
				if(xy.startsWith("p")){
					System.out.println(xy);
				}
			}
		
		}
		br.close();
	}
}
