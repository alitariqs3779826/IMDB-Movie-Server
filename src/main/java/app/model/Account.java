package app.model;



public class Account {
    private String firstName;
    private String lastName;
    private String address;
    private String username;

    /**
     * Only stores hashed passwords.
     */
    private String password;

    private String country;
    private String gender;
    private String email;
    private String type;
    private int proco_id;
    private String status;



    public Account(String un, String p) {
        username = un;
        password = p;
    }


    public Account(String fn, String ln, String c, String g, String email,String type) {
      firstName = fn;
      lastName = ln;
   
      country = c;
      gender = g;
      this.email = email;
      this.type = type;
      
    	
    	
    	// TODO fill in here
        /* You should use this constructor when you are showing the account page,
        hence, the user is already logged in. Therefore, the username Should be used
        to fetch this information from the database. You may have to tweek some stuff
        here and there.
        You should NEVER show the current password in the form. NEVER!
        And if you want to change the password, you need to ask for current password as well.
         */
    }


    public Account(String username, String fn, String ln, String c, String g, String email, String type,int proco_id){
        this(fn, ln,c, g, email, type);
        this.username = username;
        this.proco_id = proco_id;
    }

    public void updateDetails() {
        // TODO
    }
    
    public String getType() {
        return type;
    }



    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public String getCountry() {
    	return country;
    }
    
    public String getStatus() {
    	return status;
    }
    
    public int getProcoId() {
    	return proco_id;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setProcoId(int id) {
    	this.proco_id = id;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    
    
}
