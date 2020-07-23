package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.Person;

public class PersonDAO {

	
	public static Person searchPersonById(String id) {
		Person p = null;
		
		try {
			
			String query = "SELECT * FROM imbd.person WHERE person_id = '" + id + "'";
			
			Connection c = DatabaseUtils.connectToDatabase();
            
			Statement s = c.createStatement();
            
			ResultSet r = s.executeQuery(query);
           
            while(r.next()) {
            	
            	p = new Person(r.getInt("person_id"), r.getString("fullname"), r.getString("role"),r.getDate("birthdate"),r.getString("bio"));
            }
           
            DatabaseUtils.closeConnection(c);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		
		if( p != null) {
			return p;
		}
		
		return null;
		
	}
	
	
	public static List<Person> searchPersonByName(String name){
		List<Person> p = new ArrayList<Person>();
		
		try {
			String sql = "SELECT * from imbd.person WHERE fullname like '%" + name + "%'";
		
			  Connection c = DatabaseUtils.connectToDatabase();
			  
	          Statement s = c.createStatement();
	          
	          ResultSet r = s.executeQuery(sql);

	           
	            while(r.next()) {
	            	
	            	p.add(new Person(r.getInt("person_id"), r.getString("fullname"), r.getString("role"),r.getDate("birthdate"),r.getString("bio")));
	            	
	            }
		
	            DatabaseUtils.closeConnection(c);
		}
        
		catch (Exception e) {
            e.printStackTrace();
        }
	
		if(!p.isEmpty()) {
		
			return p;
		
		}
		
		return null;
	
	}
	
}
