package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.PersonDAO;
import app.dao.ShowDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Map;

public class NewEntryController {
    public static Handler displayNewEntryPage = x -> {
        
    	Map<String, Object> base = ViewUtil.baseModel(x);
      
        base.put("type", x.sessionAttribute("type"));
        
        x.render(Template.NEWENTRY, base);
    };

    public static Handler createNewEntry = x -> {
       
    
        
    	String[] result = getShowInfo(x);
        
    	ShowDAO.createNewEntry(result);
        
    	x.redirect(Web.INDEX);
    };
    
    public static String[] getShowInfo(Context x) {
        String[] detail = new String[9];
 
     
        detail[0] = x.formParam("showName");
        
        detail[1] = x.formParam("showGenre");
        
        detail[2] = x.formParam("length");
        
        detail[3] = x.formParam("Movie");
        
        detail[4] = x.formParam("Series");
        
        detail[5] = x.formParam("proco_ID");
        
        detail[6] = x.formParam("year");
   
        if(x.sessionAttribute("type").toString().equals("user")){
        
        	detail[7] = "Under_Investigation";
        }
        else if(x.sessionAttribute("type").toString().equals("PCo")){
        
        	detail[7] = "Submitted";
        }
        else if(x.sessionAttribute("type").toString().equals("admin")){
        
        	detail[7] = "Visible";
        }

        detail[8] = x.sessionAttribute("currentUser").toString();
        
        return detail;
    }
}
