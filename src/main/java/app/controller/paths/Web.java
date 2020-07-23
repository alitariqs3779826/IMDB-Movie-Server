package app.controller.paths;


/**
 * his class contains the UTL fragments of the site. If you are going to login, then the
 * URL is going to be https://someurl.com/login
 *
 * If you want to add pages, you need to add that information here.
 */
public class Web {

    public static final String INDEX = "/";
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String ACCOUNT = "/account";
    public static final String SHOW = "/show/:showid";
    public static final String PERSON = "/person/:personId";
    //M2
    public static final String NEWENTRY = "/newEntry";
    public static final String REVIEWENTRY = "/reviewEntry";
    //M3
    public static final String REGISTER = "/register";
    public static final String REVIEWACCOUNT = "/reviewAccount";

}