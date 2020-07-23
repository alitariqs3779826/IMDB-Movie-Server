package app.model;


import java.util.List;


public class Show {
    private int showid;
    private String showTitle;
    private double length;
    private boolean isMovie;
    private boolean isSeries;
    private String genre;
    private int year;
    private String status;
    private String author;
    private int proco_id;

    private List<UserReview> userReviewList;
    private ProductionCompany productionCompany;

    private List<CreditsRoll> creditsRolls;
    public Show(){};

    
    public Show(int showID, String title, double len, boolean isMov, boolean isSer, String gen, int y) {
    	showid = showID;
    	showTitle = title;
    	length = len;
    	isMovie = isMov;
    	isSeries = isSer;
    	genre = gen;
    	year = y;
    }
    
    public Show(int showID, String title, double len, boolean isMov, boolean isSer, String gen, int y,String status,String author,int proco_id) {
    	showid = showID;
    	showTitle = title;
    	length = len;
    	isMovie = isMov;
    	isSeries = isSer;
    	genre = gen;
    	year = y;
    	this.status = status;
    	this.author = author;
    	this.proco_id = proco_id;
    }


	public int getShowid() {
    	return showid;
    }
    
    public String showTitle() {
    	return showTitle;
    }
    
    public double getLength() {
    	return length;
    }
    
    public boolean isMovie() {
    	return isMovie;
    }
    
    public boolean isSeries() {
    	return isSeries;
    }
    
    public String getGenre() {
    	return genre;
    }
    
    public int getYear() {
    	return year;
    }
    
    public List<UserReview> getUserReviewList() {
    	return userReviewList;
    }
    
    public String getImage() {
    	return "/img/shows/" + Integer.toString(this.showid) +".jpg";
    }
    
}
