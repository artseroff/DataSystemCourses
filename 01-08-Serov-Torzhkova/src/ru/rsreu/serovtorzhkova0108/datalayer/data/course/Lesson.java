package ru.rsreu.serovtorzhkova0108.datalayer.data.course;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.MidtermGrade;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

/**
 * A class is an entity of the lesson with date, list of absent students and
 * list of grades for the lesson
 * 
 * @author Serov and Torzhkova
 *
 */
public class Lesson {

	/** Lesson id */
	private int id;
	/** Lesson date */
	private Date date;
	/** Students absent from lesson */
	private List<User> absentStudents;
	/** Student grades received in the lesson */
	private List<MidtermGrade> midtermGrades;

	/**
	 * Constructor, creates and initializes the entity of the lesson
	 * 
	 * @param id             lesson id
	 * @param date           lesson date
	 * @param absentStudents students absent from lesson
	 * @param midtermGrades  student grades received in the lesson
	 */
	public Lesson(int id, Date date, List<User> absentStudents, List<MidtermGrade> midtermGrades) {
		super();
		this.id = id;
		this.date = date;
		this.absentStudents = absentStudents;
		this.midtermGrades = midtermGrades;
	}

	/**
	 * Constructor, creates and initializes the entity of the lesson
	 * 
	 * @param id             lesson id
	 * @param absentStudents students absent from lesson
	 * @param midtermGrades  student grades received in the lesson
	 */
	public Lesson(int id, List<User> absentStudents, List<MidtermGrade> midtermGrades) {
		super();
		this.id = id;
		this.absentStudents = absentStudents;
		this.midtermGrades = midtermGrades;
	}

	/**
	 * Constructor, creates and initializes the entity of the lesson
	 * 
	 * @param date lesson date
	 */
	public Lesson(Date date) {
		super();
		this.date = date;
	}

	/**
	 * Constructor, creates and initializes the entity of the lesson
	 * 
	 * @param id lesson id
	 */
	public Lesson(int id) {
		this.id = id;
	}

	/**
	 * @return lesson id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return lesson date
	 */
	public final Date getDate() {
		return date;
	}

	/**
	 * @return students absent from lesson
	 */
	public final List<User> getAbsentStudents() {
		return absentStudents;
	}

	/**
	 * @return student grades received in the lesson
	 */
	public final List<MidtermGrade> getMidtermGrades() {
		return midtermGrades;
	}

	/**
	 * Returns true if the student with the given id was absent from the lesson,
	 * false otherwise
	 * 
	 * @param id student's id
	 * @return true if student is absent
	 */
	public boolean isAbsentStudentWithId(int id) {
		for (User student : absentStudents) {
			if (student.getId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets student's grade from the lesson by his id. If student has no grade,
	 * returns null
	 * 
	 * @param id student's id
	 * @return midterm grade or null
	 */
	public Integer getStudentWithIdGradeOrNull(int id) {
		for (MidtermGrade grade : midtermGrades) {
			if (grade.getStudent().getId() == id) {
				return grade.getGrade();
			}
		}
		return null;
	}

	/**
	 * Implementing the Null Object Pattern for lesson
	 */
	public static final Lesson NULL_LESSON = new Lesson(0, new Date(0), new ArrayList<User>(),
			new ArrayList<MidtermGrade>()) {
	};
}