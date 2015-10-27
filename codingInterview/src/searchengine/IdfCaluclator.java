package searchengine;

import java.util.HashMap;

public class IdfCaluclator {

	static HashMap<String,Double>idfMap=new HashMap<String,Double>();
	
	public static void idf(HashMap<String,HashMap<String,Double>> indexMap){
		for(String term:indexMap.keySet())
    	{
    		double y=indexMap.get(term).size();
    		double idf=Math.log(3000.0/y);
    		idfMap.put(term, idf);
    	}
	}
}
