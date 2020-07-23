package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;


public class LoginController {



    public static Handler serveLoginPage = x -> {
        Map<String, Object> model = ViewUtil.baseModel(x);
        model.put("loggedOut", removeSessionAttrLoggedOut(x));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(x));
        x.render(Template.LOGIN, model);
    };




    public static Handler handleLoginPost = x -> {
        Map<String, Object> model = ViewUtil.baseModel(x);
        if (!UserController.authenticate(getQueryUsername(x), getQueryPassword(x))) {
            model.put("authenticationFailed", true);
            x.render(Template.LOGIN, model);
        } else {
            x.sessionAttribute("currentUser", getQueryUsername(x));
            //Add user type to the session attribute
            x.sessionAttribute("type", AccountDAO.getDetailUsername(getQueryUsername(x)).getType());
            model.put("authenticationSucceeded", true);
            model.put("currentUser", getQueryUsername(x));
            //Add the type of the account to the model
            model.put("type", AccountDAO.getDetailUsername(getQueryUsername(x)).getType());
            if (RequestUtil.getQueryLoginRedirect(x) != null) {
                x.redirect(RequestUtil.getQueryLoginRedirect(x));
            }
            x.render(Template.LOGIN, model);
        }
    };





    public static Handler handleLogoutPost = x -> {
        x.sessionAttribute("currentUser", null);
        x.sessionAttribute("loggedOut", "true");
        x.redirect(Web.LOGIN);
    };




       



    public static String getQueryUsername(Context x) {
        return x.formParam("username");
    }

    public static String getQueryPassword(Context x) {
        return x.formParam("password");
    }




    public static boolean removeSessionAttrLoggedOut(Context x) {
        String loggedOut = x.sessionAttribute("loggedOut");
        x.sessionAttribute("loggedOut", null);
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Context x) {
        String loginRedirect = x.sessionAttribute("loginRedirect");
        x.sessionAttribute("loginRedirect", null);
        return loginRedirect;
    }
}
