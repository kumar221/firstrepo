package searchengine;


import java.sql.*;
public class DocumentLength {

	 static double docveclen=0.0;
	public static void doclength(){
		
		try {
			 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			e.printStackTrace();
			return; 
		}
		
		Connection connection = null;
		
		try{		
		
			connection = DriverManager.getConnection("jdbc:postgresql:practice", "user1","user1");
			Statement stat= connection.createStatement();
			
    	double z=0.0;
    	for (String x:WeightCalculator.weightMap.keySet())
    	{
    		for(String y:WeightCalculator.weightMap.get(x).keySet())
    		{
    			z=Math.pow(WeightCalculator.weightMap.get(x).get(y),2.0);
    			docveclen+=z; 	    		
    		}
    		stat.executeUpdate("INSERT INTO doclength VALUES('"+x+"','"+z+"')");
    		docveclen=0.0;
    	}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception{
		TokenizeTerms tt=new TokenizeTerms();
		tt.Normalize();
		TokenizeTerms.tokenize();
		IdfCaluclator.idf(TokenizeTerms.indexMap);
		Termfrequency.termfrequency(TokenizeTerms.indexMap);
		WeightCalculator.docweight();
		DocumentLength.doclength();
		System.out.println("Its Done");
	}
}
