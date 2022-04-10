package ru.rsreu.serovtorzhkova0108.datalayer.data.course;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.FinalGrade;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

/**
 * A class is an entity of the course with information about it, lists of
 * students, classes, announcements and final grades
 * 
 * @author Serov and Torzhkova
 *
 */
public class Course {

	/** Course id */
	private int id;
	/** Course name */
	private String name;
	/** Course description */
	private String description;
	/** The teacher who teaches the course */
	private User teacher;
	/** Course start date */
	private Date startDate;
	/** Course end date */
	private Date endDate;
	/** Maximum number of students who can enroll in a course */
	private int maxStudentsAmount;
	/** Students participating in the course */
	private List<User> students;
	/** Course lessons */
	private List<Lesson> lessons;
	/** Final student grades for the course */
	private List<FinalGrade> finalGrades;
	/** Course announcements */
	private List<Announcement> announcements;

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id                course id
	 * @param name              course name
	 * @param description       course description
	 * @param teacher           teacher who teaches the course
	 * @param startDate         course start date
	 * @param endDate           course end date
	 * @param maxStudentsAmount maximum number of students who can enroll in a
	 *                          course
	 * @param students          students participating in the course
	 * @param lessons           course lessons
	 * @param finalGrades       final student grades for the course
	 * @param announcements     course announcements
	 */
	public Course(int id, String name, String description, User teacher, Date startDate, Date endDate,
			int maxStudentsAmount, List<User> students, List<Lesson> lessons, List<FinalGrade> finalGrades,
			List<Announcement> announcements) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.teacher = teacher;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxStudentsAmount = maxStudentsAmount;
		this.students = students;
		this.lessons = lessons;
		this.finalGrades = finalGrades;
		this.announcements = announcements;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id                course id
	 * @param name              course name
	 * @param description       course description
	 * @param teacher           teacher who teaches the course
	 * @param startDate         course start date
	 * @param endDate           course end date
	 * @param maxStudentsAmount maximum number of students who can enroll in a
	 *                          course
	 */
	public Course(int id, String name, String description, User teacher, Date startDate, Date endDate,
			int maxStudentsAmount) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.teacher = teacher;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxStudentsAmount = maxStudentsAmount;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param name              course name
	 * @param description       course description
	 * @param teacher           teacher who teaches the course
	 * @param startDate         course start date
	 * @param endDate           course end date
	 * @param maxStudentsAmount maximum number of students who can enroll in a
	 *                          course
	 */
	public Course(String name, String description, User teacher, Date startDate, Date endDate, int maxStudentsAmount) {
		super();
		this.name = name;
		this.description = description;
		this.teacher = teacher;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maxStudentsAmount = maxStudentsAmount;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id       course id
	 * @param students students participating in the course
	 */
	public Course(int id, List<User> students) {
		super();
		this.id = id;
		this.students = students;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id          course id
	 * @param students    students participating in the course
	 * @param lessons     course lessons
	 * @param finalGrades final student grades for the course
	 */
	public Course(int id, List<User> students, List<Lesson> lessons, List<FinalGrade> finalGrades) {
		super();
		this.id = id;
		this.students = students;
		this.lessons = lessons;
		this.finalGrades = finalGrades;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id            course id
	 * @param name          course name
	 * @param announcements course announcements
	 */
	public Course(int id, String name, List<Announcement> announcements) {
		super();
		this.id = id;
		this.announcements = announcements;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id                course id
	 * @param students          students participating in the course
	 * @param finalGrades       final student grades for the course
	 */
	public Course(int id, List<User> students, List<FinalGrade> finalGrades) {
		super();
		this.id = id;
		this.students = students;
		this.finalGrades = finalGrades;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id                course id
	 * @param maxStudentsAmount maximum number of students who can enroll in a
	 *                          course
	 */
	public Course(int id, int maxStudentsAmount) {
		super();
		this.id = id;
		this.maxStudentsAmount = maxStudentsAmount;
	}

	/**
	 * Constructor, creates and initializes the entity of the course
	 * 
	 * @param id course id
	 */
	public Course(int id) {
		super();
		this.id = id;
	}

	/**
	 * @return course id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return course name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @return course description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @return teacher who teaches the course
	 */
	public final User getTeacher() {
		return teacher;
	}

	/**
	 * @return course start date
	 */
	public final Date getStartDate() {
		return startDate;
	}

	/**
	 * @return course end date
	 */

	public final Date getEndDate() {
		return endDate;
	}

	/**
	 * @return maximum number of students who can enroll in a course
	 */
	public final int getMaxStudentsAmount() {
		return maxStudentsAmount;
	}

	/**
	 * @return students participating in the course
	 */
	public final List<User> getStudents() {
		return students;
	}

	/**
	 * @return course lessons
	 */
	public final List<Lesson> getLessons() {
		return lessons;
	}

	/**
	 * @return final student grades for the course
	 */
	public final List<FinalGrade> getFinalGrades() {
		return finalGrades;
	}

	/**
	 * @return course announcements
	 */
	public final List<Announcement> getAnnouncements() {
		return announcements;
	}

	/**
	 * Gets student's final grade by his id. If student has no final grade, returns
	 * null
	 * @param id student id
	 * @return student's final grade or null
	 */
	public FinalGrade getFinalGradeStudentWithId(int id) {
		for (FinalGrade grade : finalGrades) {
			if (grade.getStudent().getId() == id) {
				return grade;
			}
		}
		return null;
	}

	/**
	 * Implementing the Null Object Pattern for course
	 */
	public static final Course NULL_COURSE = new Course(0, "Null course name", "Null course description",
			User.NULL_USER, new Date(0), new Date(0), 0, new ArrayList<User>(), new ArrayList<Lesson>(),
			new ArrayList<FinalGrade>(), new ArrayList<Announcement>()) {
	};
}