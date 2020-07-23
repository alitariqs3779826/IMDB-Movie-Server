package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ReviewAccountController {
	public static Handler displayPendingAccounts = ctx -> {
		Map<String,Object> model = ViewUtil.baseModel(ctx);
		model.put("type",ctx.sessionAttribute("type"));
		model.put("pendingAccounts",AccountDAO.getPendingAccounts());
		ctx.render(Template.REVIEWACCOUNT,model);
	};
	
	public static String[] getAccountInfo(Context ctx) {
		String[] details = new String[2];
		details[0] = ctx.formParam("username");
		details[1] = ctx.formParam("Review");	
		return details;
	}
	
	public static Handler reviewAccounts = ctx -> {
		String[] result = getAccountInfo(ctx);
		AccountDAO.reviewAccount(result);
		ctx.redirect(Web.INDEX);
			
	};
	
	
	

}
