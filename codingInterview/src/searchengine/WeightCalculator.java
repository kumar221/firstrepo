package searchengine;

import java.util.HashMap;

public class WeightCalculator {

	static HashMap<String,HashMap<String,Double>> weightMap=new HashMap<String,HashMap<String,Double>>();
	
	public static void docweight(){
    	for(String x:TokenizeTerms.indexMap.keySet())
    	{
    		for(String y:TokenizeTerms.indexMap.get(x).keySet())
    		{
    			double tf=TokenizeTerms.indexMap.get(x).get(y)/Termfrequency.termcountMap.get(y);
    			double weight=tf*IdfCaluclator.idfMap.get(x);
    			if(!weightMap.containsKey(y))
    			{
    				weightMap.put(y,new HashMap<String,Double>());
    				weightMap.get(y).put(x,weight); 
    			}
    			else
    			{ 			
    				if(!weightMap.get(y).containsKey(x))
    				{
    					weightMap.get(y).put(x, weight);		 
    				}
    			}
    		}
    	}
    }
	
}
