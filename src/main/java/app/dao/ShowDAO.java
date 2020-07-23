package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.dao.utils.DatabaseUtils;
import app.model.Person;
import app.model.Show;

public class ShowDAO {

	
	
	public static Show searchShowById(String id) {
		Show s = null;
		
		try {
			String query = "SELECT * FROM imbd.show WHERE showid = '" + id + "'";
			
			Connection c = DatabaseUtils.connectToDatabase();
            Statement st = c.createStatement();
            ResultSet r = st.executeQuery(query);
           
            while(r.next()) {
            	s = new Show(r.getInt("showid"), r.getString("show_title"), r.getDouble("length"),r.getBoolean("movie"),
            			r.getBoolean("series"),r.getString("genre"),r.getInt("year"));
            }
           
            DatabaseUtils.closeConnection(c);
        }
        catch (Exception e) {
        	
            
        	e.printStackTrace();
        }
		
		if( s != null) {
			return s;
		}
		
		return null;
		
	}
	
	public static List<Show> searchShowBytitle(String title){
		List<Show> show = new ArrayList<Show>();
		
		try {
			String query = "SELECT * from imbd.show WHERE show_title like ? AND status = ?";
		
			  Connection c = DatabaseUtils.connectToDatabase();

	          PreparedStatement sqlStatement = c.prepareStatement(query);
	          
	          sqlStatement.setString(1,"%" + title+ "%");
	          
	          sqlStatement.setString(2,"Visible");
	          
	          ResultSet r = sqlStatement.executeQuery();
	          


	            while(r.next()) {
	            	
	            	Show shows = new Show(r.getInt("showid"), r.getString("show_title"), r.getDouble("length"),r.getBoolean("movie"),
	            			r.getBoolean("series"),r.getString("genre"),r.getInt("year"));
	            	
	            	show.add(shows);
	            }
		
	            DatabaseUtils.closeConnection(c);
		}
        catch (Exception e) {
            
        	e.printStackTrace();
        }
	
		if(!show.isEmpty()) {
			
			return show;
		
		}
		
		return null;
	
	}
	
	
	public static void createNewEntry(String[] details) {
		int isMovie;
		int isSeries;
		
		if(details[3].equals("Yes")) {
			isMovie = 1;
		}
		else {
			isMovie = 0 ;
		}
		if(details[4].equals("Yes")) {
			isSeries = 1;
		}
		else {
			isSeries = 0 ;
		}
		try{
			 String sql = "INSERT INTO imbd.show (show_title, genre, length, movie, series, proco_id,year,status,author) " +
	                    "VALUES (?,?,?,?,?,?,?,?,?)";
			  Connection c = DatabaseUtils.connectToDatabase();
			  
	          Statement s = c.createStatement();
	          
	          PreparedStatement sqlStatement = c.prepareStatement(sql);
	          
	          sqlStatement.setString(1,details[0]);
	          
	          sqlStatement.setString(2,details[1]);
	          
	          sqlStatement.setFloat(3,Float.parseFloat(details[2]));
	         
	          sqlStatement.setInt(4,isMovie);
	          
	          sqlStatement.setInt(5,isSeries);
	          
	          sqlStatement.setInt(6,Integer.parseInt(details[5]));
	          
	          sqlStatement.setInt(7,Integer.parseInt(details[6]));
	          
	          sqlStatement.setString(8,details[7]);
	          
	          sqlStatement.setString(8,details[8]);
	          
	          sqlStatement.executeUpdate();
	          
	          DatabaseUtils.closeConnection(c);
	          
			 
		}
		catch(Exception e) {
			
			e.printStackTrace();
		
		}
	}
	
	public static List<Show> searchUnderInvestigationShows(){
		
		List<Show> show = new ArrayList<Show>();
		
		try {
			String query = "SELECT * from imbd.show WHERE status = ?";
		
			  Connection c = DatabaseUtils.connectToDatabase();
			  
			  PreparedStatement sqlStatement = c.prepareStatement(query);
	          
			  sqlStatement.setString(1,"Under_Investigation");
	          
			  ResultSet r = sqlStatement.executeQuery();


	            while(r.next()) {
	            	
	            	show.add(new Show(r.getInt("showid"), r.getString("show_title"), r.getDouble("length"),r.getBoolean("movie"),
	            			r.getBoolean("series"),r.getString("genre"),r.getInt("year"),r.getString("status"),r.getString("author"),
	            			r.getInt("proc_id")));
	            	
	            }
		
	            DatabaseUtils.closeConnection(c);
		}
        catch (Exception e) {
           
        	e.printStackTrace();
        }
	
		if(!show.isEmpty()) {
			
			return show;
		}
	
		return null;
	
	}
	
	public static void reviewEntry(String[] details) {
		String status;
		if(details[2].equals("accept")) {
		
			status = "Visible";
		}
		
		else {
			status = "Reject";
					
		}
		try {
			
			String query  = "UPDATE imbd.show SET status = ? WHERE showid = ?";
			
			Connection c = DatabaseUtils.connectToDatabase();
	        
			Statement statement = c.createStatement();
	        
			PreparedStatement sqlStatement = c.prepareStatement(query);
	        
			sqlStatement.setString(1,status);
	        
			sqlStatement.setInt(2,Integer.parseInt(details[0]));
	          
	          DatabaseUtils.closeConnection(c);

			
		}
		catch(Exception e) {
		
			e.printStackTrace();
		}
	}
	
	public static void deleteShow(Show show) {
		try {
		
			String sql = "DELETE FROM imbd.show WHERE showid = '"  + Integer.toString(show.getShowid()) + "'";
			
			Connection c = DatabaseUtils.connectToDatabase();
            
			Statement st = c.createStatement();
            
			st.executeQuery(sql);
            
            
            DatabaseUtils.closeConnection(c);
            
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void editData(Show show, Map<String, List<String>> formParamMap) {
		
		String genre = formParamMap.get("genre").get(0),year = formParamMap.get("year").get(0), 
		
				length = formParamMap.get("length").get(0),movie =  formParamMap.get("movie").get(0);
		
		double dlength = show.getLength();
		try {
			
			double x = Double.parseDouble(length);
			
			dlength = x;
		}catch(Exception e) {
			
		}
		
		int iyear = show.getYear();
		
		try {
			int x = Integer.parseInt(year);
		
			iyear = x;	
		}
		catch(Exception e) {
			
		}
		
		int isMovie,isSeries;
		
		if(movie.equals("Movie")){
			isMovie = 1;
			isSeries = 0;
			
		}
		else {
			isMovie =0;
			isSeries = 1;
		}
		
		try {
			String sql = "UPDATE imbd.show SET length = '" + Double.toString(dlength)+
		
					"', year = '" + Integer.toString(iyear)+ "',genre = '"+
			genre + "', movie = '" + Integer.toString(isMovie) + "', series = '"+
			
			Integer.toString(isSeries) + "' " + "WHERE showid = '"  +
	        
			Integer.toString(show.getShowid()) + "'";

			Connection connection = DatabaseUtils.connectToDatabase();
            
			Statement statement = connection.createStatement();
            
			statement.executeUpdate(sql);
            
            DatabaseUtils.closeConnection(connection);
		
		}
		catch(Exception e) {
			
			e.printStackTrace();
		
		}
	
	}
	
}
