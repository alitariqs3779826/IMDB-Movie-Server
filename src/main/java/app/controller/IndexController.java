package app.controller;

import app.controller.paths.Template;

import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.dao.ShowDAO;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;

public class IndexController {

	  private static String getQuerySearchById(Context x) {
	    	
			return x.formParam("showTitleSearch");
		}
	  
	public static String getQuerySearchByTitle(Context x) {
			
			return x.formParam("showTitleSearch");
		
		}

	public static String getQuerySearchByName(Context x) {
		
			return x.formParam("showActorSearch");
		
		}

	 
	public static Handler searchPost = x -> {
			
			Map<String, Object> base = ViewUtil.baseModel(x);
			
			if (x.body().startsWith("showTitleSearch")) {
			
				base.put("shows", ShowDAO.searchShowBytitle(getQuerySearchByTitle(x)));
			}
			else {
			
				base.put("persons", PersonDAO.searchPersonByName(getQuerySearchByName(x)));
			}
		
			x.render(Template.INDEX, base);
		
		};
	  
	  public static Handler serveIndexPage = x -> {
		
		Map<String, Object> base = ViewUtil.baseModel(x);
		
		base.put("type", x.sessionAttribute("type"));
		
		
		if (x.sessionAttribute("type") != null) {
		
			base.put("currentUser", x.sessionAttribute("currentUser"));
			
			base.put("type", x.sessionAttribute("type"));
		}

		x.render(Template.INDEX, base);
	};

	


}
