package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.*;
import app.model.Account;
import app.model.Show;
import app.model.UserReview;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowController {

    public static Handler displayShowDetail = x -> {
        Map<String, Object> base = ViewUtil.baseModel(x);
        
        Show show = ShowDAO.searchShowById(getParamShowid(x));
        
        base.put("show", show);
        
        ArrayList<UserReview> reviews = ReviewDAO.getReviewe(show);
        
        int avgRate = getAvgRate(reviews);
        
        base.put(getStr(avgRate, 0), 1);
        
        int lastRate = getRate(reviews, getCurrentAccount(x), show);
        
        base.put(getStr(lastRate, 1), 1);
        
        ArrayList<String> comments = getComments(reviews);
        
        if (comments.size() > 0) {
        
        	base.put("comments", comments);
        
        }
        
        String lastComment = getComment(reviews, getCurrentAccount(x), show);
        
        base.put("lastComment", lastComment);
        
        base.put("type", x.sessionAttribute("type"));
        
        x.render(Template.SHOW, base);
    };
    

    
    private static ArrayList<String> getComments(List<UserReview> reviews) {
    
    	ArrayList<String> comments = new ArrayList<String>();
        
    	for (UserReview review:reviews) {
        
    		comments.add(review.getReview());
        }
    	
        
    	return comments;
    }
    

    
    
    private static int getRate(List<UserReview> reviews, Account currentAccount, Show show) {
    
    	if (currentAccount == null)
        
    		return 0;
        for 
        (UserReview review:reviews) {
        
        	if (review.getShow_id() == show.getShowid() && currentAccount.getUsername().equals(review.getUserName()))
            
        		return review.getRating();
        }
        
        
        return 0;
    }
    

    
    
    private static int getAvgRate(List<UserReview> reviews) {
    
    	int sum = 0, num = 0;
        
    	for (UserReview review:reviews) {
        
    		sum += review.getRating();
            
    		num++;
        }
    	
        
    	if (num > 0)
        
    		sum /= num;
        return sum;
    }
    

    
    
    public static Handler submit = x -> {
    
    	Map<String, Object> model = ViewUtil.baseModel(x);
        
    	Show show = ShowDAO.searchShowById(getParamShowid(x));
        
    	if (x.formParamMap().containsKey("delButton")) {
        
    		ReviewDAO.deleteReviews(show);
            
    		ShowDAO.deleteShow(show);
            
    		model.put("showDeleted" , 1);
        } else {
        
        	if(x.formParamMap().containsKey("subButton")){
            
        		ShowDAO.editData(show, x.formParamMap());
                
        		show = ShowDAO.searchShowById(getParamShowid(x));
            } else {
            
            	int val = Integer.parseInt(x.formParam("rate"));
                
            	String comment = x.formParam("comment");
                ReviewDAO.submitReview( show,getCurrentAccount(x), comment, val );
            }
        	
            
        	model.put("type", x.sessionAttribute("type"));
            
        	model.put("show", show);
            
        	ArrayList<UserReview> reviews = ReviewDAO.getReviewe(show);
            
        	int avgRate = getAvgRate(reviews);
            
        	model.put(getStr(avgRate, 0), 1);
            
        	int lastRate = getRate(reviews, getCurrentAccount(x), show);
            
        	model.put(getStr(lastRate, 1), 1);
            
        	String lastComment = getComment(reviews, getCurrentAccount(x), show);
            
        	
        	model.put("lastComment", lastComment);
            
        	List<String> comments = getComments(reviews);
            
        	if (comments.size() > 0) {
            
        		model.put("comments", comments);
            }
        	
        }
    	
        
    	x.render(Template.SHOW, model);
    };
    

    
    private static String getComment(ArrayList<UserReview> reviews, Account currentAccount, Show show) {
    
    	
    	if (currentAccount == null)
        
    		return null;
        String ret = "you can comment here";
        
        for (UserReview review:reviews) {
        
        	if (review.getShow_id() == show.getShowid() && currentAccount.getUsername().equals(review.getUserName()))
            
        		ret = "Your last comment: " + review.getReview();
        }
        
        return ret;
    }

    public static String getParamShowid(Context x) {
        
    	return x.pathParam("showid");
    }

    public static Account getCurrentAccount(Context x) {
        
    	Account account = null;
        try {
        
        	account = AccountDAO.getUserByUsername(x.sessionAttributeMap().get("currentUser").toString());
        }
        catch (Exception e){}
        
        return account;
    }

    public static String getStr (int val, int type) {
        String rev = "";
        
        if (val == 1)
        	rev = "One-Star";
        
        else if (val == 2)
        
        	rev = "Two-Star";
        else if (val == 3)
        	
        	rev = "Three-Star";
        
        else if (val == 4)
        
        	rev = "Four-Star";
        
        else if (val == 5)
       
        	rev = "Five-Star";
       
        if (type == 0)
       
        	rev = "a" + rev;
        
        return rev;
    
    }
}
