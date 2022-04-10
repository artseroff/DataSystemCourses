package ru.rsreu.serovtorzhkova0108.logic;

import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.CourseDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class StudentLogic {
	
	private StudentLogic() {
	}

	public static List<Course> getStudentCourses(User student) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getStudentCourses(student);
	}

	public static List<Course> getNotStudentCourses(User student) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getNotStudentCourses(student);
	}

	public static boolean isMaximumStudentsAmountReachedInCourse(Course course) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getStudentsParticipatedInCourse(course).size() == course.getMaxStudentsAmount();
	}

	public static void enrollStudentToCourse(Course course, User student) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.enrollStudentToCourse(course, student);
	}

	public static void expelStudentFromCourse(Course course, User student) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.expelStudentFromCourse(course, student);
	}

	public static List<Announcement> getNotViewedByStudentAnnouncementsInCourse(Course course, User student) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getNotViewedByStudentAnnouncementsInCourse(course, student);
	}

	public static List<Announcement> getViewedByStudentAnnouncementsInCourse(Course course, User student) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getViewedByStudentAnnouncementsInCourse(course, student);
	}

	public static void setAnnouncementAsViewedByStudent(Announcement announcement, User student) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.setAnnouncementAsViewedByStudent(announcement, student);
	}
}