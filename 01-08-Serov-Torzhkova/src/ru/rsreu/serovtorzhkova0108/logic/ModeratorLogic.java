package ru.rsreu.serovtorzhkova0108.logic;

import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.UserDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class ModeratorLogic {
	
	private ModeratorLogic() {
	}

	public static List<User> getAllUsers() {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		return userDAO.getAllUsersForModerator();
	}

	public static void changeUserLockStatus(User user) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		userDAO.changeUserLockStatus(user);
	}
}