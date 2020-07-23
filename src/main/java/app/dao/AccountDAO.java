package app.dao;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.utils.DatabaseUtils;
import app.model.Account;

import io.javalin.http.Handler;

import org.eclipse.collections.impl.list.mutable.FastList;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class AccountDAO {
    public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";

    List<Account> acc = new ArrayList<>();
    /**
     * Method to fetch users from the database.
     * You should use this as an example for future queries, though the sql statement
     * will change -and you are supposed to write them.
     *
     * Current user: caramel 6, password (the password is "password" without quotes)
     * @param username what the user typed in the log in form.
     * @return Some of the user data to check on the password. Null if there
     *         no matching user.
     */
    public static Account getUserByUsername(String username) {
       
        List<Account> acc = new ArrayList<>();

        try {
        
            String query = "SELECT username, password FROM account WHERE username ='" + username + "'";

        
            Connection c = DatabaseUtils.connectToDatabase();
            
            Statement s = c.createStatement();
            
            ResultSet r = s.executeQuery(query);

           
            while(r.next()) {
              
                acc.add(
             
                  new Account(r.getString("username"),
                          r.getString("password"))
                );
            }

  
            DatabaseUtils.closeConnection(c);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        if(!acc.isEmpty()) return acc.get(0);
       

        return null;
    }
    
    public Iterable<Account> getAllAccounts() {
    	
    	List<Account> acc = new ArrayList<>();
        
    	return acc;
    }
    
    public static Account getDetailUsername(String username) {
        
    	 Account acc = null;

         try {
         
             String sql = "SELECT first_name, last_name,email, country,gender,account_type FROM account WHERE username ='" + username + "'";

          
             Connection c = DatabaseUtils.connectToDatabase();
             
             Statement s = c.createStatement();
             
             ResultSet r = s.executeQuery(sql);

             while(r.next()) {
                 acc = new Account(r.getString("first_name"),
                 r.getString("last_name"),
                 r.getString("country"),
                 r.getString("gender"),
                 r.getString("email"),
                 r.getString("account_type"));
             }

       
             DatabaseUtils.closeConnection(c);
         }
         
         catch (Exception e) {
             e.printStackTrace();
         }

         if(acc == null) return null;
         
         return acc;
     
    }
    
    public static void createAccounts(String[] accountDetails) {
    	try {
    		String sql = "INSERT INTO imbd.account (username,password,first_name,last_name,gender,country, email"
    				+ ",account_type,status,proco_id) " +" VALUES(?,?,?,?,?,?,?,?,?,?)";
    		
    		
    		Connection c = DatabaseUtils.connectToDatabase();
    		
    	
    		PreparedStatement sqlStatement = c.prepareStatement(sql);
    		
    		sqlStatement.setString(1, accountDetails[0]);
    		
    		sqlStatement.setString(2, accountDetails[1]);
    		
    		sqlStatement.setString(3, accountDetails[2]);
    		
    		sqlStatement.setString(4, accountDetails[3]);
    		
    		sqlStatement.setString(5, accountDetails[4]);
    		
    		sqlStatement.setString(6, accountDetails[5]);
    		
    		sqlStatement.setString(7, accountDetails[6]);
    		
    		sqlStatement.setString(8, accountDetails[7]);
    		
    		sqlStatement.setString(9, "");
    		
    		sqlStatement.setInt(10,0);
    		
    		
    		if(accountDetails[7].equals("PCo")){
    		
    			sqlStatement.setString(9, "pending");
    			
    			try {
    				
    			
    				String sqlInsertPco = "INSERT INTO imbd.production_company (proco_name) VALUES (?)";
    				
    				PreparedStatement sqlInsertPcoStatement = c.prepareStatement(sqlInsertPco);
    				
    				sqlInsertPcoStatement.setString(1, accountDetails[8]);
    				
    				sqlInsertPcoStatement.executeUpdate();
    				
    				
    				
    				String sqlProcId = "SELECT proco_id FROM imbd.production_company WHERE proco_name = ?";
    				
    				PreparedStatement sqlProcIDStatement = c.prepareStatement(sqlProcId);
    				
    				sqlProcIDStatement.setString(1, accountDetails[8]);
    				
    				ResultSet result = sqlProcIDStatement.executeQuery();
    				
    				while(result.next()) {
    				
    					int proco_id = result.getInt("proco_id");
    					
    					sqlStatement.setInt(10, proco_id);
    				}
    				
    				
    				
    				
    			}
    			
    			catch(Exception e) {
    			
    				e.printStackTrace();
    			}
    		}
    		
    		
    	
    		sqlStatement.executeUpdate();
        
    		DatabaseUtils.closeConnection(c);
    }
    	catch (Exception e) {
    	
    		e.printStackTrace();
    	
    	}
    
    }
    
    
    public static List<Account> getPendingAccounts(){
    
    	List<Account> accounts = new ArrayList<Account>();
        try {
        
        	
        	String sql = "SELECT * FROM imbd.account WHERE status = ?";
        	
        	Connection c = DatabaseUtils.connectToDatabase();
        	
        	PreparedStatement sqlStatement = c.prepareStatement(sql);
        	
        	sqlStatement.setString(1, "pending");
        	
        	ResultSet result = sqlStatement.executeQuery();
        	
        	
        	while(result.next()){
            
        		accounts.add(new Account(
                
        				result.getString("username"),
                        
        				result.getString("first_name"),
                        
        				result.getString("last_name"),
                        
        				result.getString("country"),
                        
        				result.getString("gender"),
                        
        				result.getString("email"),
                        
        				result.getString("account_type"),
                        
                        result.getInt("proco_id")
                ));
        	}
        	
        }catch(Exception e) {
        	e.printStackTrace();
        }
        
        if(!accounts.isEmpty()) {
        	return accounts;
        }
        return null;
    	
    }
    
    public static void reviewAccount(String[] account) {
    	String status;
    	if(account[1].equals("accept")) {
    	
    		status = "";
    	
    	}else {
    	
    		status = "Reject";
    		
    	}try {
    		
    		String sql = "UPDATE imbd.account SET status = ? WHERE username = ?";
    		
    		Connection c = DatabaseUtils.connectToDatabase();
    		
    		PreparedStatement sqlStatement = c.prepareStatement(sql);
    		
    		sqlStatement.setString(1, status);
    		
    		sqlStatement.setString(2, account[0]);
    		
    		sqlStatement.executeUpdate();
    		
    		
    		
    		DatabaseUtils.closeConnection(c);
    	}
    	
    	
    	catch(Exception e) {
    	
    		e.printStackTrace();
    	}
    	
    }
    
    
    
    public static String getStatusByUserName(String username) {
    	String status = "";
    
    	
    	try {
    	
    		String sql = "Select status from imbd.account WHERE username = ?";
    		
    		Connection connection = DatabaseUtils.connectToDatabase();
            
    		PreparedStatement sqlStatement = connection.prepareStatement(sql);
            
    		sqlStatement.setString(1, username);
            
    		ResultSet result = sqlStatement.executeQuery();
            
    		while(result.next()) {
            
    			status = result.getString("status");
            }
    		
            
    		DatabaseUtils.closeConnection(connection);
    	}
    	
    	
    	catch(Exception e) {
    	
    		e.printStackTrace();
    	}
    	
    	
    	return status;
    }
    
    
    
    
    

    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
