package ru.rsreu.serovtorzhkova0108.command.administrator;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.AdministratorLogic;

public class DeleteUserCommand implements ActionCommand {

	private static final String PARAM_NAME_ID = "id";
	private static final String PARAM_NAME_GROUP = "group";
	private static final String PARAM_FULL_NAME = "fullName";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
		String group = request.getParameter(PARAM_NAME_GROUP);
		String fullName = request.getParameter(PARAM_FULL_NAME);
		User user = new User(id, group, fullName);
		AdministratorLogic.deleteUser(user);
		return GroupEnum.ADMINISTRATOR.getMainPageAndSetRequestAttributes(request);
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}