package ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades;

import java.sql.Date;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

/**
 * A class is an entity of the final grade with the value of this grade, the
 * student who received it and the date the grade was given
 * 
 * @author Serov and Torzhkova
 *
 */
public class FinalGrade {

	/** Student who received the final grade */
	private User student;
	/** Date of receipt of the final grade */
	private Date date;
	/** The value of the final grade */
	private FinalGradeEnum grade;

	/**
	 * Constructor, creates and initializes the entity of the final grade
	 * 
	 * @param student student who received the final grade
	 * @param date    date of receipt of the final grade
	 * @param grade   value of the final grade
	 */
	public FinalGrade(User student, Date date, FinalGradeEnum grade) {
		super();
		this.student = student;
		this.date = date;
		this.grade = grade;
	}

	/**
	 * @return student who received the final grade
	 */
	public final User getStudent() {
		return student;
	}

	/**
	 * @return date of receipt of the final grade
	 */
	public final Date getDate() {
		return date;
	}

	/**
	 * @return value of the final grade
	 */
	public final FinalGradeEnum getGrade() {
		return grade;
	}

	/**
	 * Implementing the Null Object Pattern for final grade
	 */
	public static final FinalGrade NULL_FINAL_GRADE = new FinalGrade(User.NULL_USER, new Date(0),
			FinalGradeEnum.EXPELLED) {
	};
}