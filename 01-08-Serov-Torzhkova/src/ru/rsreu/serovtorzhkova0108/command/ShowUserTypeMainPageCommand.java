package ru.rsreu.serovtorzhkova0108.command;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class ShowUserTypeMainPageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		User currentUser = (User) request.getSession(false).getAttribute("user");
		GroupEnum userType = GroupEnum.findGroupByName(currentUser.getGroupName());
		return userType.getMainPageAndSetRequestAttributes(request);
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}