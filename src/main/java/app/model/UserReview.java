package app.model;

import java.util.Date;





public class UserReview {
    private int reviewID;
    private String username;
    private String review;
    private int rating;
    private Date date;
    private int show_Id;






    public UserReview(String r, int v) {
        review = r;
        rating = v;
        date = new Date();
    }
    
    public UserReview(String username,int showId,String r, int v) {
        this.username = username;
        this.show_Id = showId;
    	review = r;
        rating = v;
        date = new Date();
    }





    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }
    
    public String getUserName() {
        return username;
    }
    
    public int getShow_id() {
        return show_Id;
    }
}
