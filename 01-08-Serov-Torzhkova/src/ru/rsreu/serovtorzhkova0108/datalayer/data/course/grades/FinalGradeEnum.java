package ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades;

import java.util.Arrays;

/**
 * Enumeration of final grade values
 * 
 * @author Serov and Torzhkova
 *
 */
public enum FinalGradeEnum {

	PASSED("зачтено"), NOT_PASSED("незачтено"), EXPELLED("отчислен");

	/** The string value of the final grade */
	private final String name;

	/**
	 * Constructor, creates and initializes the value of the final grade
	 * 
	 * @param name string value of the final grade
	 */
	FinalGradeEnum(String name) {
		this.name = name;
	}

	/**
	 * @return string value of the final grade
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Searches for the value of the final grade by its string representation
	 * 
	 * @param name string value of the final grade
	 * @return value of final grade
	 * @throws IllegalArgumentException is throwing if were passed illegal
	 *                                  parameters
	 */
	public static FinalGradeEnum findGradeByName(String name) throws IllegalArgumentException {
		return Arrays.stream(FinalGradeEnum.values()).filter(v -> v.getName().equals(name)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown final grade"));
	}
}