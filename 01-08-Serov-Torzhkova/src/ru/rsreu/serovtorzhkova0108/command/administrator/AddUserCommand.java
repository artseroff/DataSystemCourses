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

public class AddUserCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";
	private static final String PARAM_NAME_GROUP = "group";
	private static final String PARAM_NAME_FULLNAME = "fullName";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		String group = request.getParameter(PARAM_NAME_GROUP);
		String fullName = request.getParameter(PARAM_NAME_FULLNAME);
		if (AdministratorLogic.isLoginExisted(login)) {
			HttpSession session = request.getSession(false);
			session.setAttribute("login", login);
			session.setAttribute("password", password);
			session.setAttribute("userGroup", group);
			session.setAttribute("fullName", fullName);
			session.setAttribute("existedLogin", Resourcer.getString("message.admin.existedLogin"));
			page = Resourcer.getString("path.page.admin.addUser");
		} else {
			User user = new User(0, 0, group, login, password, fullName, AuthorizationStatusEnum.NOT_AUTHORIZED,
					LockStatusEnum.UNLOCKED);
			AdministratorLogic.addUser(user);
			page = GroupEnum.ADMINISTRATOR.getMainPageAndSetRequestAttributes(request);
		}
		return page;
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}