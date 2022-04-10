package ru.rsreu.serovtorzhkova0108.datalayer.data.user;

import java.util.Arrays;

/**
 * Enumeration of user lock status
 * 
 * @author Serov and Torzhkova
 *
 */
public enum LockStatusEnum {

	LOCKED("заблокирован"), UNLOCKED("не заблокирован");

	/** The string value of the lock status */
	private final String name;

	/**
	 * Constructor, creates and initializes the value of the lock status
	 * 
	 * @param name string value of the lock status
	 */
	LockStatusEnum(String name) {
		this.name = name;
	}

	/**
	 * @return string value of the lock status
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Searches for the value of the lock status by its string representation
	 * 
	 * @param name string value of the lock status
	 * @return value of lock status
	 * @throws IllegalArgumentException is throwing if were passed illegal
	 *                                  parameters
	 */
	public static LockStatusEnum findLockStatusByName(String name) throws IllegalArgumentException {
		return Arrays.stream(LockStatusEnum.values()).filter(v -> v.getName().equals(name)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown lock status"));
	}
}