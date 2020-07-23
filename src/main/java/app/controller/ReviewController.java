package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ReviewController {
	
	public static Handler displaySubmitedEntry = x -> {
       
		Map<String, Object> model = ViewUtil.baseModel(x);
        
		model.put("type", x.sessionAttribute("type"));
        
		model.put("underInvestidatedShows", ShowDAO.searchUnderInvestigationShows());
        
        x.render(Template.REVIEWENTRY, model);
    
	};
    
    public static Handler reviewEntry = x -> {
        
    	String[] result = getShowInfo(x);
        
    	ShowDAO.reviewEntry(result);
        
    	x.render(Web.INDEX);
    
    };

	private static String[] getShowInfo(Context x) {
		
		String[] details = new String[3];
		
		
		details[0] = x.formParam("showid");
		
		details[1] = x.formParam("showTitle");
		
		details[2] = x.formParam("showReviiew");
		
				
		return details;
	}

}
