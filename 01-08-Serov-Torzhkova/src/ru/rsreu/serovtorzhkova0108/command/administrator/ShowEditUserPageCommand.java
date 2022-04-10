package ru.rsreu.serovtorzhkova0108.command.administrator;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;

public class ShowEditUserPageCommand implements ActionCommand {

	private static final String PARAM_EDITED_USER_ID = "editedUserId";
	private static final String PARAM_EDITED_USER_LOGIN = "editedUserLogin";
	private static final String PARAM_EDITED_USER_PASSWORD = "editedUserPassword";
	private static final String PARAM_EDITED_USER_FULLNAME = "editedUserFullName";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_EDITED_USER_ID));
		String login = request.getParameter(PARAM_EDITED_USER_LOGIN);
		String password = request.getParameter(PARAM_EDITED_USER_PASSWORD);
		String fullName = request.getParameter(PARAM_EDITED_USER_FULLNAME);
		request.setAttribute("editedUserId", id);
		request.setAttribute("editedUserLogin", login);
		request.setAttribute("oldLogin", login);
		request.setAttribute("editedUserPassword", password);
		request.setAttribute("editedUserFullName", fullName);
		return Resourcer.getString("path.page.admin.editUser");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}