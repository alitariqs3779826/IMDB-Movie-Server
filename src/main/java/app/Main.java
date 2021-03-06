package app;

import app.controller.*;
import app.controller.paths.Web;
import app.controller.RegisterController;
import app.controller.utils.ViewUtil;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import static io.javalin.apibuilder.ApiBuilder.*;





public class Main {





    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(getHerokuAssignedPort());

        app.routes(() -> {
            // You will have to update this, to limit who can see the reviews
            // before(LoginController.ensureLoginBeforeViewing);

            get(Web.INDEX, IndexController.serveIndexPage);

            get(Web.LOGIN, LoginController.serveLoginPage);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);

            get(Web.ACCOUNT, AccountController.serveAccountPage);

            // Add new actions here
            // Seeing pages (get) and sending information in forms (post)
            post(Web.INDEX, IndexController.searchPost);
            get(Web.SHOW, ShowController.displayShowDetail);
            post(Web.SHOW, ShowController.submit);
            get(Web.PERSON, PersonController.displayPersonDetail);
            //M2
            get(Web.NEWENTRY, NewEntryController.displayNewEntryPage);
            post(Web.NEWENTRY,NewEntryController.createNewEntry);
            post(Web.REVIEWENTRY,ReviewEntryController.reviewEntry);
            get(Web.REVIEWENTRY,ReviewEntryController.displaySubmittedEntry);
            //M3
            get(Web.REGISTER, RegisterController.displayRegister);
            post(Web.REGISTER, RegisterController.createAccount);
            get(Web.REVIEWACCOUNT,ReviewAccountsController.displayPendingAccounts);
            post(Web.REVIEWACCOUNT,ReviewAccountsController.reviewAccount);

        });

        app.error(404, ViewUtil.notFound);
    }






    public static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }



}
