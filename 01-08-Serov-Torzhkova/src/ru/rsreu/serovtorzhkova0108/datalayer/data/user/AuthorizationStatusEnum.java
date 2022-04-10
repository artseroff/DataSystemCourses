package ru.rsreu.serovtorzhkova0108.datalayer.data.user;

import java.util.Arrays;

/**
 * Enumeration of user authorization status
 * 
 * @author Serov and Torzhkova
 *
 */
public enum AuthorizationStatusEnum {

	AUTHORIZED("авторизован"), NOT_AUTHORIZED("не авторизован");

	/** The string value of the authorization status */
	private final String name;

	/**
	 * Constructor, creates and initializes the value of the authorization status
	 * 
	 * @param name string value of the authorization status
	 */
	AuthorizationStatusEnum(String name) {
		this.name = name;
	}

	/**
	 * @return string value of the authorization status
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Searches for the value of the authorization status by its string
	 * representation
	 * 
	 * @param name string value of the authorization status
	 * @return value of authorization status
	 * @throws IllegalArgumentException is throwing if were passed illegal
	 *                                  parameters
	 */
	public static AuthorizationStatusEnum findAuthorizationStatusByName(String name) throws IllegalArgumentException {
		return Arrays.stream(AuthorizationStatusEnum.values()).filter(v -> v.getName().equals(name)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown authorization status"));
	}
}