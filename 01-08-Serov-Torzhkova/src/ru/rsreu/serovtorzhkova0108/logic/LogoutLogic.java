package ru.rsreu.serovtorzhkova0108.logic;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.UserDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.AuthorizationStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class LogoutLogic {
	
	private LogoutLogic() {
	}

	public static void updateUserStatusNotAuthorizated(User user) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		userDAO.updateUserAuthorizationStatus(user, AuthorizationStatusEnum.NOT_AUTHORIZED);
	}
}