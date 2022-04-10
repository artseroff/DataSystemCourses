package ru.rsreu.serovtorzhkova0108.logic;

import java.sql.Date;
import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.CourseDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Lesson;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class TeacherLogic {
	
	private TeacherLogic() {
	}

	public static List<Course> getAllCoursesWithTeacher(User teacher) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getAllCoursesByTeacher(teacher);
	}

	public static void updateAbsentStudentsWithMidtermGrades(Lesson lesson) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.updateAbsentStudentsWithMidtermGrades(lesson);
	}

	public static void addLessonToCourse(Lesson lesson, Course course) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.addLessonToCourse(lesson, course);
	}

	public static void deleteLessonFromCourse(Lesson lesson) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.deleteLesson(lesson);
	}

	public static Lesson findLessonWithDateInCourse(Date date, Course course) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getLessonByDateInCourse(date, course);
	}

	public static void addAnnouncementToCourse(Announcement announcement, Course course) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.addAnnouncementToCourse(announcement, course);
	}

	public static void deleteAnnouncement(Announcement announcement) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.deleteAnnouncement(announcement);
	}

	public static Announcement getAnnouncementById(int announcementId) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getAnnouncementById(announcementId);
	}

	public static boolean checkIsDateInDatesRangeCourse(Date sqlDate, int courseId) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		Course course = courseDAO.getCourseDescriptionById(courseId);
		return sqlDate.after(course.getStartDate()) && sqlDate.before(course.getEndDate());
	}

	public static Course getCourseWithStudentsAndFinalGradesById(int id) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getCourseWithStudentsAndFinalGradesById(id);
	}

	public static void updateFinalGradesInCourse(Course course) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.updateFinalGradesInCourse(course);
	}

	public static boolean checkIfCourseWithNameAndDatesExists(String name, Date startDate, Date endDate) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getCourseByNameAndDates(name, startDate, endDate) != Course.NULL_COURSE;
	}

	public static void addCourse(Course course) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.addCourse(course);
	}

	public static void deleteCourse(Course course) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		courseDAO.deleteCourse(course);
	}

	public static Course getCourseWithAnnouncementsById(int id) {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getCourseWithAnnouncementsById(id);
	}
}