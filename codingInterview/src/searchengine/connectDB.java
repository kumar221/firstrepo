package searchengine;

import java.sql.*;

public class connectDB {

	public static void main(String args[]) throws Exception{
		
		TokenizeTerms tt=new TokenizeTerms();
		tt.Normalize();
		TokenizeTerms.tokenize();
		IdfCaluclator.idf(TokenizeTerms.indexMap);
		Termfrequency.termfrequency(TokenizeTerms.indexMap);
		WeightCalculator.docweight();
		
		
		try {
			 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			e.printStackTrace();
			return; 
		}
		
		System.out.println("PostgreSQL JDBC Driver Registered!");
		
		Connection connection = null;
		 
		try {
 
			connection = DriverManager.getConnection("jdbc:postgresql:practice", "user1","user1");
			
			System.out.println("It came here");
			
			Statement stat= connection.createStatement();
			
			for(String x:WeightCalculator.weightMap.keySet()){
				for(String y:WeightCalculator.weightMap.get(x).keySet()){
					
					stat.executeUpdate("INSERT INTO weight VALUES('"+x+"','"+y+"',"+WeightCalculator.weightMap.get(x).get(y)+")");
						
				}
			}
			
			
			
			
			
			stat.close();
			connection.close();
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
 
		}
 
	}
}
