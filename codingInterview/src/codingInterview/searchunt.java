package codingInterview;


import java.io.*;
import java.util.*;

public class searchunt {
	static double docveclen=0.0;
	static HashMap<String,Double> veclen=new HashMap<String,Double>();
	static HashMap<String,HashMap<String,Double>> docindx=new HashMap<String,HashMap<String,Double>>();
	static HashMap<String,HashMap<String,Double>> idfindx=new HashMap<String,HashMap<String,Double>>();
	static HashMap<String,Double>idf=new HashMap<String,Double>();
	static HashMap<String,Double>maxfreq=new HashMap<String,Double>();
	static Set<String> stopWords = new LinkedHashSet<String>();
   	static HashMap<String,Double> qrweight=new HashMap<String,Double>();
	static HashMap<String,Double> xmap=new HashMap<String,Double>();
	static HashMap<String,Double> querylen=new HashMap<String,Double>();
	static HashMap<String,Double>cosinemap=new HashMap<String,Double>();
	
	public static void stopwords()throws Exception{
    	
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
        	if(f.isFile()) {
                BufferedReader inputStream = null;
                try {
                	inputStream = new BufferedReader(new FileReader(f));
                	String line;  
                	while ((line = inputStream.readLine()) != null) {
                		line=line.replaceAll("</?\\w+>","");
                		for(String out:line.split("[\\p{Punct} \\s]+"))
                		{
                			out=out.toLowerCase();
                			if(out.length()==0){
                				continue;
                			}
                			if(stopWords.contains(out))
                			{
                				continue;
                			}
                			out=stemmer.stripAffixes(out);                            	
                			if(!docindx.containsKey(out))
                			{
                				docindx.put(out,new HashMap<String,Double>());
                				docindx.get(out).put(f.getName(), 1.0);                     		
                			}                     	                        
                			if(docindx.containsKey(out))
                			{
                				if(docindx.get(out).containsKey(f.getName()))
                				{
                					docindx.get(out).put(f.getName(),docindx.get(out).get(f.getName())+1.0);
                				}  
                				else if(!docindx.get(out).containsKey(f.getName())){
                					docindx.get(out).put(f.getName(),1.0);
                				}
                			}                         
                		}                  	        	   
                	}                                                         
                }
                finally 
                {
                	if (inputStream != null)
                	{
                		inputStream.close();
                	}
                }             
        	}         
        }
    }
    public static void idf(){
    	for(String xy:docindx.keySet())
    	{
    		double y=docindx.get(xy).size();
    		double z=Math.log(3000.0/y);
    		idf.put(xy, z);
    	}
    }
    
    public static void termfrequnc(){
    	for(String xy:docindx.keySet())
    		for(String y:docindx.get(xy).keySet())
    		{ 
    			if(!maxfreq.containsKey(y))
    			{
    				maxfreq.put(y,docindx.get(xy).get(y));
    			}
    			if(maxfreq.get(y)<docindx.get(xy).get(y)){
    				maxfreq.put(y,docindx.get(xy).get(y));
    			}
    			else
    			{
    				continue;
    			}
    		}
    }
    
    public static void docweight(){
    	for(String x:docindx.keySet())
    	{
    		for(String y:docindx.get(x).keySet())
    		{
    			double tf=docindx.get(x).get(y)/maxfreq.get(y);
    			double weight=tf*idf.get(x);
    			if(!idfindx.containsKey(y))
    			{
    				idfindx.put(y,new HashMap<String,Double>());
    				idfindx.get(y).put(x,weight); 
    			}
    			else
    			{ 			
    				if(!idfindx.get(y).containsKey(x))
    				{
    					idfindx.get(y).put(x, weight);		 
    				}
    			}
    		}
    	}
    }
    
    public static void doclength(){
    	double z=0.0;
    	for (String x:idfindx.keySet())
    	{
    		for(String y:idfindx.get(x).keySet())
    		{
    			z=Math.pow(idfindx.get(x).get(y),2.0);
    			docveclen+=z; 	    		
    		}
    		veclen.put(x,docveclen);
    		docveclen=0.0;
    	}
    }
    
    public static void querytoken(String query){
    	Porter stemmer = new Porter();
    	for(String token:query.split("[\\p{Punct} \\s]+"))
    	{
    		token=token.toLowerCase();
    		if(token.length()==0){
    			continue;
    		}
    		if(stopWords.contains(token))
    		{
    			continue;
    		}
    		token=stemmer.stripAffixes(token);
    		if(xmap.containsKey(token))
    		{
    			xmap.put(token,xmap.get(token)+1.0);
    		}  
    		xmap.put(token,1.0);
    	}   	
    }
    public static void queryweight(){
    	for(String tokens:xmap.keySet())
    	{
    		if(docindx.containsKey(tokens))
    		{
    			double qweight=(xmap.get(tokens))*idf.get(tokens);
    			qrweight.put(tokens,qweight); 				  
    		}
    	}     			
    }
    public static double querylen(){
    	double z;
    	for(String y:qrweight.keySet())
    	{
    		z=Math.pow(qrweight.get(y),2.0);
    		docveclen+=z; 	    		
    	}
    	return docveclen;
    }
    
    public static void cossimilarity(){
    	for(String tokens:qrweight.keySet())
    	{
    		if(docindx.containsKey(tokens))
    		{
    			for(String docs:docindx.get(tokens).keySet())
    			{
    				
    				double similarity=idfindx.get(docs).get(tokens)*qrweight.get(tokens)/(Math.sqrt(veclen.get(docs)*querylen()));
    				if(!cosinemap.containsKey(docs))
    				{	
    					cosinemap.put(docs, similarity);
    				}
    				
    				else
    				{
    					cosinemap.put(docs,cosinemap.get(docs)+similarity);
    				}
    			}
    		}
    	}
    }	
    public static void result(int docs){
    	ArrayList<Double> values=new ArrayList<Double>();
    	for(String docus:cosinemap.keySet())
    	{
    		values.add(cosinemap.get(docus));
    	}
    	if(values.size()==cosinemap.size())
    	{
    		Collections.sort(values, Collections.reverseOrder());
    		double last=-1.0;
    		for(double arval:values.subList(0, docs)) 
    		{
    			if(last==arval)
    				continue;
    			last=arval;
    			for(String s:cosinemap.keySet())
    			{
    				if(cosinemap.get(s)==arval)
    					System.out.println(s);
    			}
    		}
    		 	  
    	}
    }
    public static void main(String args[]){
   
    	String query;
    	int docs;
    	String c;
    	int docnew;
    	System.out.println("Type the Query");
    	Scanner scan=new Scanner(System.in);
    	query=scan.nextLine();
    	System.out.println("Enter the number of documents you would like to retrieve");
    	docs=scan.nextInt();
    	try{
    	searchunt.stopwords();
    	searchunt.tokenize();
    	searchunt.idf();
    	searchunt.termfrequnc();
    	searchunt.docweight();
    	searchunt.doclength();
    	searchunt.querytoken(query);
    	searchunt.queryweight();
    	searchunt.querylen();
    	searchunt.cossimilarity();
    	searchunt.result(docs);
    
    	System.out.println("If you like to retrieve more documents press y else press any key");
    	Scanner scan1=new Scanner(System.in);
    	c=scan1.nextLine();
    	
    	if(c.equals("y"))
    	{
    		Scanner scan2=new Scanner(System.in);
    		System.out.println("Enter the number of documents you would like to retrieve");
    		docnew=scan2.nextInt();
    		searchunt.result(docnew);
    		scan2.close();
    		
    	}
    	else{
    		
    	}
    	scan1.close();
    	
    }
catch (Exception e){
    		
    	}
    	
   
    	scan.close();
    }
}
