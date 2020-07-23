package app.controller;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.ShowDAO;
import io.javalin.http.Handler;

import java.util.Map;



public class AccountController {

    public static Handler serveAccountPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here
        model.put("accountDetail", AccountDAO.getDetailUsername(model.get("currentUser").toString()));
        model.put("type", ctx.sessionAttribute("type"));
        ctx.render(Template.ACCOUNT, model);
    };




}
