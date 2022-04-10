package ru.rsreu.serovtorzhkova0108.datalayer.dao;

import java.sql.Date;
import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Lesson;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

/**
 * Ñontains methods for obtaining data and editing them for the entity Course
 * 
 * @author Serov and Torzhkova
 *
 */
public interface CourseDAO {

	/**
	 *  Gets a list of courses of the teacher
	 * 
	 * @param teacher teacher
	 * @return list of courses of the teacher
	 */
	List<Course> getAllCoursesByTeacher(User teacher);

	/**
	 * Gets course with description by course id
	 * 
	 * @param id course id
	 * @return course with description or NULL_COURSE
	 */
	Course getCourseDescriptionById(int id);
	
	/**
	 * Gets list of students by course
	 * 
	 * @param course course
	 * @return list of students
	 */
	List<User> getStudentsParticipatedInCourse(Course course);

	/**
	 * Gets course with attendance and midterm and final grades by course id
	 * 
	 * @param id course id
	 * @return course with attendance and midterm and final grades or NULL_COURSE
	 */
	Course getCourseWithAttendanceAndGradesById(int id);

	/**
	 * Deletes old values of absent students and midterm grades from lesson and inserts new values
	 * 
	 * @param lesson edited lesson
	 */
	void updateAbsentStudentsWithMidtermGrades(Lesson lesson);

	/**
	 * Gets lesson by date in course
	 * 
	 * @param date lesson date 
	 * @param course course
	 * @return lesson by date and course or NULL_LESSON
	 */
	Lesson getLessonByDateInCourse(Date date, Course course);

	/**
	 * Add lesson to course
	 * 
	 * @param lesson added lesson
	 * @param course course
	 */
	void addLessonToCourse(Lesson lesson, Course course);

	/**
	 * Deletes lesson with all its information
	 * 
	 * @param lesson lesson
	 */
	void deleteLesson(Lesson lesson);

	/**
	 * Gets course with announcements by course id
	 * 
	 * @param id course id
	 * @return course with announcements or NULL_COURSE
	 */
	Course getCourseWithAnnouncementsById(int id);

	/**
	 * Gets course with students and final grades by course id
	 * 
	 * @param id course id
	 * @return course with students and final grades or NULL_COURSE
	 */
	Course getCourseWithStudentsAndFinalGradesById(int id);

	/**
	 * Adds announcement to course
	 * 
	 * @param announcement added announcement
	 * @param course       course
	 */
	void addAnnouncementToCourse(Announcement announcement, Course course);

	/**
	 * Deletes announcement with all its information
	 * 
	 * @param announcement deleted announcement
	 */
	void deleteAnnouncement(Announcement announcement);
	
	/**
	 * Gets announcement by its id
	 * 
	 * @param announcementId announcementId
	 * @return announcement or NULL_ANNOUNCEMENT 
	 */
	Announcement getAnnouncementById(int announcementId);
	
	/**
	 * Deletes old values of final grades from course and inserts new values
	 * 
	 * @param course updated course
	 */
	void updateFinalGradesInCourse(Course course);

	/**
	 * Gets course by its name and dates
	 * 
	 * @param name      name of searched course
	 * @param startDate start date of searched course
	 * @param endDate   end date of searched course
	 * @return searched course or NULL_COURSE
	 */
	Course getCourseByNameAndDates(String name, Date startDate, Date endDate);

	/**
	 * Adds new course with its description
	 * 
	 * @param course added course
	 */
	void addCourse(Course course);

	/**
	 * Deletes course with all its information
	 * 
	 * @param course deleted course
	 */
	void deleteCourse(Course course);

	/**
	 * Gets a list of courses with their information the student is enrolled in
	 * 
	 * @param student student
	 * @return list of student's courses
	 */
	List<Course> getStudentCourses(User student);

	/**
	 * Gets a list of courses with their information for which the student is not
	 * enrolled
	 * 
	 * @param student student
	 * @return list of not student's courses
	 */
	List<Course> getNotStudentCourses(User student);

	/**
	 * Adds a record that the student is participating in the course
	 * 
	 * @param course  course
	 * @param student enrolled student
	 */
	void enrollStudentToCourse(Course course, User student);

	/**
	 * Deletes the student's final grade for the course (if it exists) and adds an
	 * "expelled" grade
	 * 
	 * @param course  course
	 * @param student expelled student
	 */
	void expelStudentFromCourse(Course course, User student);

	/**
	 * Gets a list of course announcements that the student has not yet viewed
	 * 
	 * @param course  course
	 * @param student student participating in the course
	 * @return list of not viewed announcements
	 */
	List<Announcement> getNotViewedByStudentAnnouncementsInCourse(Course course, User student);

	/**
	 * Gets a list of course announcements that the student has already viewed
	 * 
	 * @param course  course
	 * @param student student participating in the course
	 * @return list of viewed announcements
	 */
	List<Announcement> getViewedByStudentAnnouncementsInCourse(Course course, User student);

	/**
	 * Adds a record that the student viewed the announcement
	 * 
	 * @param announcement announcement
	 * @param student      student
	 */
	void setAnnouncementAsViewedByStudent(Announcement announcement, User student);

	/**
	 * Deletes all courses, which are taught by the teacher, with all their information
	 * 
	 * @param teacher teacher
	 */
	void deleteTeacherCourses(User teacher);

	/**
	 * Deletes information about a student from all courses for which he is enrolled
	 * 
	 * @param student student
	 */
	void deleteStudentCourseInformation(User student);
}