package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Map;

public class ReviewEntryController {
	
    public static String[] getShowInfo(Context x) {
        
    	String[] det = new String[3];
   
        
    	det[0] = x.formParam("showid");
        
    	det[1] = x.formParam("showTitle");
        
    	det[2] = x.formParam("Review");
        
    	return det;
    }
	
    public static Handler displaySubmittedEntry = x -> {
    	
        Map<String, Object> base = ViewUtil.baseModel(x);
        
        base.put("type", x.sessionAttribute("type"));
        
        base.put("underInvestigatedShows", ShowDAO.searchUnderInvestigationShows());
       
        x.render(Template.REVIEWENTRY, base);
        
    };
 
    public static Handler reviewEntry = x -> {
    
    	String[] r = getShowInfo(x);
        
    	ShowDAO.reviewEntry(r);
        
    	x.redirect(Web.INDEX);
    
    };
    

}
