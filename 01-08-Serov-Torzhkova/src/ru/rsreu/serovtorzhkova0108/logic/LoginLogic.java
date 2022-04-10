package ru.rsreu.serovtorzhkova0108.logic;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.UserDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.AuthorizationStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.LockStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class LoginLogic {
	
	private LoginLogic() {
	}

	public static User findUserByLoginAndPassword(String enterLogin, String enterPass) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		return userDAO.findUserByLoginAndPassword(enterLogin, enterPass);
	}

	public static LockStatusEnum getCurrentLockStatusByUserId(int id) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		return userDAO.findUserByIdWithLockStatus(id).getLockStatus();
	}

	public static void updateUserStatusAuthorizated(User user) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		userDAO.updateUserAuthorizationStatus(user, AuthorizationStatusEnum.AUTHORIZED);
	}
}