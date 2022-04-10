package ru.rsreu.serovtorzhkova0108.command.administrator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.AuthorizationStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.LockStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.AdministratorLogic;

public class EditUserCommand implements ActionCommand {

	private static final String PARAM_NAME_EDITED_USER_ID = "editedUserId";
	private static final String PARAM_NAME_EDITED_USER_LOGIN = "editedUserLogin";
	private static final String PARAM_NAME_EDITED_USER_PASSWORD = "editedUserPassword";
	private static final String PARAM_NAME_EDITED_USER_FULLNAME = "editedUserFullName";
	private static final String PARAM_NAME_OLD_LOGIN = "oldLogin";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_EDITED_USER_ID));
		String login = request.getParameter(PARAM_NAME_EDITED_USER_LOGIN);
		String oldLogin = request.getParameter(PARAM_NAME_OLD_LOGIN);
		String password = request.getParameter(PARAM_NAME_EDITED_USER_PASSWORD);
		String fullName = request.getParameter(PARAM_NAME_EDITED_USER_FULLNAME);
		if (!login.equals(oldLogin) && AdministratorLogic.isLoginExisted(login)) {
			HttpSession session = request.getSession(false);
			session.setAttribute("editedUserId", id);
			session.setAttribute("editedUserLogin", login);
			session.setAttribute("oldLogin", oldLogin);
			session.setAttribute("editedUserPassword", password);
			session.setAttribute("editedUserFullName", fullName);
			session.setAttribute("existedLogin", Resourcer.getString("message.admin.existedLogin"));
			page = Resourcer.getString("path.page.admin.editUser");
		} else {
			User user = new User(id, 0, null, login, password, fullName, AuthorizationStatusEnum.NOT_AUTHORIZED,
					LockStatusEnum.UNLOCKED);
			AdministratorLogic.updateUser(user);
			page = GroupEnum.ADMINISTRATOR.getMainPageAndSetRequestAttributes(request);
		}
		return page;
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}