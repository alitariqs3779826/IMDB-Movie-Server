package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Map;

public class PersonController {

    public static Handler displayPersonDetail = x -> {
        
    	Map<String, Object> base = ViewUtil.baseModel(x);
        
        base.put("person", PersonDAO.searchPersonById(getParamPersonid(x)));
    
        x.render(Template.PERSON, base);
    };

    public static String getParamPersonid(Context x) {
      
    	return x.pathParam("personId");
    
    }
}
