package app.model;



import java.util.Date;



public class Person {
    private int personId;
    private String fullName;
    private String role;
    private String bio;
    private Date birthdate;




    public Person(int i, String name, String roles, Date date, String bi) {
        personId = i;
        fullName = name;
        role = roles;
        birthdate = date;
        bio = bi;
    }
    



    public String getRole() {
        return role;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPersonId() {
        return personId;
    }

    public String getBio() {
        return bio;
    }
    
    public String getImage() {
    	return "img/people/" + Integer.toString(this.personId) + ".jpg";
    }
}
