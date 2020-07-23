package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.Account;
import app.model.Person;
import app.model.Show;
import app.model.UserReview;

public class ReviewDAO {

	
	public static ArrayList<UserReview> getReviewe(Show show){
		
		ArrayList<UserReview> revs = new ArrayList<UserReview>();
		
		try {
			
			String query = "SELECT * FROM imbd.user_review WHERE show_id = '"  + Integer.toString(show.getShowid()) + "'";
		
			 Connection c = DatabaseUtils.connectToDatabase();
			 
	         Statement s = c.createStatement();
	         
	         ResultSet r = s.executeQuery(query);

	         while(r.next()) {
	        	 UserReview userReview = new UserReview(r.getString("user_id"),
	        			 r.getInt("show_id"),
	        			 r.getString("review"),
	        			 r.getInt("rating"));
	        	 		revs.add(userReview);
	         }
	         
	         DatabaseUtils.closeConnection(c);
	         
		}
		catch(Exception e){
			
			e.printStackTrace();
		
		}
		
		return revs;
	}
	
	
	
	public static void submitReview(Show show,Account account,String review,int number) {
	
		
		try {
			
			String query = "DELETE FROM imbd.user_review WHERE show_id = '" + 
			Integer.toString(show.getShowid()) + 
			"' AND user_id = '" + 
			account.getUsername() + 
			"'";
			
			Connection c = DatabaseUtils.connectToDatabase();
			
            Statement s = c.createStatement();
            
             s.executeQuery(query);
           
            query = "INSERT INTO imbd.user_review (show_id,user_id,rating,review,date) VALUES ('" + 
            Integer.toString(show.getShowid()) + 
            "', '" + account.getUsername() + 
            "', '" + 
            Integer.toString(number) + 
            "', '" + 
            review + 
            "', '" + 
            LocalDateTime.now().toString() + 
            "')";
           
            s.executeUpdate(query);
            
            DatabaseUtils.closeConnection(c);
        
		}
        
		catch (Exception e) {
           
			e.printStackTrace();
        
		}
		
	}
	
	public static void deleteReviews(Show show) {
		try {
			String sql = "DELETE FROM imbd.user_review WHERE show_id = '" + Integer.toString(show.getShowid()) + "'";
				

			Connection c = DatabaseUtils.connectToDatabase();		
            Statement s = c.createStatement();
            s.executeQuery(sql);
            
            DatabaseUtils.closeConnection(c);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
