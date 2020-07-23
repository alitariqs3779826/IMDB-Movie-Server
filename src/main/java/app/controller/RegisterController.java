package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.dao.AccountDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;

public class RegisterController {
    public static Handler displayRegister = x -> {
    	
        x.render(Template.REGISTER);
    
    };

    public static Handler createAccount = x -> {
    
    	String[] account = getAccountInfo(x);
        
    	
        
    	AccountDAO.createAccounts(account);
        
    	
        
    	x.redirect(Web.INDEX);
    
    };

    public static String[] getAccountInfo(Context x){
    
    	String[] acc = new String[9];
        
    	acc[0] = x.formParam("userName");
        
    	String pwd = x.formParam("password");
        
    	acc[1] = BCrypt.hashpw(pwd, AccountDAO.SALT);
        
    	acc[2] = x.formParam("FN");
        
    	acc[3] = x.formParam("LN");
        
    	acc[4] = x.formParam("gender");
        
    	acc[5] = x.formParam("country");
        
    	acc[6] = x.formParam("email");
        
    	acc[7] = x.formParam("accountType");
        
    	acc[8] = x.formParam("PCoName");
        
    	return acc;
    }
}

