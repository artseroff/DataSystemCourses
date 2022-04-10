package ru.rsreu.serovtorzhkova0108.logic;

import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.CourseDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.UserDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class AdministratorLogic {

	private AdministratorLogic() {
	}
	
	public static List<User> getAllUsers() {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		return userDAO.getAllUsersForAdministrator();
	}

	public static void addUser(User user) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		userDAO.addUser(user);
	}

	public static void updateUser(User user) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		userDAO.updateUser(user);
	}

	public static void deleteUser(User user) {
		GroupEnum group = GroupEnum.findGroupByName(user.getGroupName());
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		if (group == GroupEnum.TEACHER) {
			courseDAO.deleteTeacherCourses(user);
		} else if (group == GroupEnum.STUDENT) {
			courseDAO.deleteStudentCourseInformation(user);
		}
		UserDAO userDAO = factory.getUserDAO();
		userDAO.deleteUser(user);
	}

	public static boolean isLoginExisted(String login) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDAO = factory.getUserDAO();
		return userDAO.getUserByLogin(login) != User.NULL_USER;
	}
}