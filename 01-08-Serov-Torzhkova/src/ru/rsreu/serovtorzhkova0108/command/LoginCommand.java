package ru.rsreu.serovtorzhkova0108.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.AuthorizationStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.LoginLogic;

public class LoginCommand implements ActionCommand {

	private static final String PARAM_NAME_LOGIN = "login";
	private static final String PARAM_NAME_PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String pass = request.getParameter(PARAM_NAME_PASSWORD);
		User searchedUser = null;
		searchedUser = LoginLogic.findUserByLoginAndPassword(login, pass);
		HttpSession session = request.getSession(false);
		if (!searchedUser.equals(User.NULL_USER)) {
			if (!searchedUser.getAuthorizationStatus().equals(AuthorizationStatusEnum.AUTHORIZED)) {
				LoginLogic.updateUserStatusAuthorizated(searchedUser);
				User refreshedUserWithCurrentAuthorizationStatus = LoginLogic.findUserByLoginAndPassword(login, pass);
				session.setAttribute("user", refreshedUserWithCurrentAuthorizationStatus);
				session.setAttribute("lockStatus", refreshedUserWithCurrentAuthorizationStatus.getLockStatus());
				GroupEnum userType = GroupEnum.findGroupByName(searchedUser.getGroupName());
				page = userType.getMainPageAndSetRequestAttributes(request);
			} else {
				session.setAttribute("errorAlreadyAuthorized", Resourcer.getString("message.errorAlreadyAuthorized"));
				page = Resourcer.getString("path.page.login");
			}
		} else {
			session.setAttribute("errorLoginPassMessage", Resourcer.getString("message.loginerror"));
			page = Resourcer.getString("path.page.login");
		}
		return page;
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}