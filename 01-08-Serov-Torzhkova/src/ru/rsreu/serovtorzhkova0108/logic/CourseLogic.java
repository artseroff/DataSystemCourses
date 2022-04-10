package ru.rsreu.serovtorzhkova0108.logic;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.CourseDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;

public class CourseLogic {
	
	private CourseLogic() {
	}

	public static Course getCourseWithDescriptionById(int id) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getCourseDescriptionById(id);
	}

	public static Course getCourseWithAttendanceAndGradesById(int id) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getCourseWithAttendanceAndGradesById(id);
	}
}