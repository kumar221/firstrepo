package searchengine;

import java.util.HashMap;

public class Termfrequency {

	static HashMap<String,Double>termcountMap=new HashMap<String,Double>();
	
	public static void termfrequency(HashMap<String,HashMap<String,Double>> indexMap){
		for(String xy:indexMap.keySet())
    		for(String y:indexMap.get(xy).keySet())
    		{ 
    			if(!termcountMap.containsKey(y))
    			{
    				termcountMap.put(y,indexMap.get(xy).get(y));
    			}
    			if(termcountMap.get(y)<indexMap.get(xy).get(y)){
    				termcountMap.put(y,indexMap.get(xy).get(y));
    			}
    			else
    			{
    				continue;
    			}
    		}
	}
}
