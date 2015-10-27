package searchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import codingInterview.Porter;

public class TokenizeTerms {
	static Set<String> stopWords = new HashSet<String>();
	static HashMap<String,HashMap<String,Double>> indexMap=new HashMap<String,HashMap<String,Double>>();
	public void Normalize() throws IOException{
		
		BufferedReader SW= new BufferedReader(new FileReader("StopWords.txt"));
		for(String line;(line = SW.readLine()) != null;){
        	stopWords.add(line.trim());
        }
		SW.close();    
	}	
	
	public static void tokenize()throws Exception{
    	Porter stemmer = new Porter();
    	String data_folder = "C:/Users/Prudhvi/Desktop/IR/crawleddocs/";  // Path to files folder
        File file= new File(data_folder);
        File[] textfiles = file.listFiles();
     // Reading all files in the directory and then tokenize the data and putting the tokens into docindx. 
        for (File f : textfiles) {
        	if(f.isFile()) 
        	{
        		
        		try {
        			BufferedReader br = new BufferedReader(new FileReader(f));
        			String line;  
        			while ((line = br.readLine()) != null)
        			{
        				line=line.replaceAll("</?\\w+>","");
        				for(String term:line.split("[\\p{Punct} \\s]+"))
        				{
        					term=term.toLowerCase();
        					if(term.length()==0)
        					{
                				continue;
                			}
                			if(stopWords.contains(term))
                			{
                				continue;
                			}
                			term=stemmer.stripAffixes(term);                            	
                			if(!indexMap.containsKey(term))
                			{
                				indexMap.put(term,new HashMap<String,Double>());
                				indexMap.get(term).put(f.getName(), 1.0);                     		
                			}                     	                        
                			if(indexMap.containsKey(term))
                			{
                				if(indexMap.get(term).containsKey(f.getName()))
                				{
                					indexMap.get(term).put(f.getName(),indexMap.get(term).get(f.getName())+1.0);
                				}  
                				else if(!indexMap.get(term).containsKey(f.getName())){
                					indexMap.get(term).put(f.getName(),1.0);
                				}
                			}                         
                		}                  	        	   
                	}    
        			br.close();
                }
                catch(Exception e) {
                	System.out.println("There is an error");
                }             
        	}         
        }
    }
}
