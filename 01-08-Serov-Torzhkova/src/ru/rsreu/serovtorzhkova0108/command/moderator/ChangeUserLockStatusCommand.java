package ru.rsreu.serovtorzhkova0108.command.moderator;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.LockStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.ModeratorLogic;

public class ChangeUserLockStatusCommand implements ActionCommand {

	private static final String PARAM_NAME_ID = "id";
	private static final String PARAM_NAME_LOCK_STATUS = "lockStatus";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
		LockStatusEnum lockStatus = LockStatusEnum.findLockStatusByName(request.getParameter(PARAM_NAME_LOCK_STATUS));
		User user = new User(id, lockStatus);
		ModeratorLogic.changeUserLockStatus(user);
		return GroupEnum.MODERATOR.getMainPageAndSetRequestAttributes(request);
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}