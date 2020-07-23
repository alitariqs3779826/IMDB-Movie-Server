package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;

public class ReviewAccountsController {
   
	 public static String[] getAccountInfo(Context x){
	       
		 String[] acc = new String[2];
	        
	        
	        acc[0] = x.formParam("username");
	        
	        acc[1] = x.formParam("Review");
	        
	        return acc;
	    }
	 
	
	public static Handler displayPendingAccounts = x -> {
       
		Map<String, Object> base = ViewUtil.baseModel(x);
        
        base.put("type", x.sessionAttribute("type"));
        
        base.put("pendingAccounts", AccountDAO.getPendingAccounts());
        
        x.render(Template.REVIEWACCOUNT, base);
        
    };

    public static Handler reviewAccount = x -> {
       
    	String[] r = getAccountInfo(x);
        
        AccountDAO.reviewAccount(r);
        
        x.redirect(Web.INDEX);
        
    };

   
}
