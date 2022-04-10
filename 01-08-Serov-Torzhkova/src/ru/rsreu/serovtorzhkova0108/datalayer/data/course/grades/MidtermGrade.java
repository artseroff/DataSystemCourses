package ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

/**
 * A class is an entity of the midterm grade with the value of this grade and
 * the student who received
 * 
 * @author Serov and Torzhkova
 *
 */
public class MidtermGrade {

	/** Student who received the midterm grade */
	private User student;
	/** The value of the midterm grade */
	private int grade;

	/**
	 * Constructor, creates and initializes the entity of the midterm grade
	 * 
	 * @param student student who received the midterm grade
	 * @param grade   value of the midterm grade
	 */
	public MidtermGrade(User student, int grade) {
		super();
		this.student = student;
		this.grade = grade;
	}

	/**
	 * @return student who received the midterm grade
	 */
	public final User getStudent() {
		return student;
	}

	/**
	 * @return value of the midterm grade
	 */
	public final int getGrade() {
		return grade;
	}

	/**
	 * Implementing the Null Object Pattern for midterm grade
	 */
	public static final MidtermGrade NULL_MIDTERM_GRADE = new MidtermGrade(User.NULL_USER, 0) {
	};
}