package ru.rsreu.serovtorzhkova0108.datalayer.data.user;

/**
 * A class is an entity of the user with information about him: login, password,
 * group, name, authorization and lock status
 * 
 * @author Serov and Torzhkova
 *
 */
public class User {

	/** User id */
	private int id;
	/** User group id */
	private int groupId;
	/** User group name */
	private String groupName;
	/** User login */
	private String login;
	/** User password */
	private String password;
	/** User full name */
	private String fullName;
	/** User authorization status */
	private AuthorizationStatusEnum authorizationStatus;
	/** User lock status */
	private LockStatusEnum lockStatus;

	/**
	 * Constructor, creates and initializes the entity of the user
	 * 
	 * @param userId              user id
	 * @param groupId             user group id
	 * @param groupName           user group name
	 * @param login               user login
	 * @param password            user password
	 * @param fullName            user full name
	 * @param authorizationStatus user authorization status
	 * @param lockStatus          user lock status
	 */
	public User(int userId, int groupId, String groupName, String login, String password, String fullName,
			AuthorizationStatusEnum authorizationStatus, LockStatusEnum lockStatus) {
		super();
		this.id = userId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.login = login;
		this.password = password;
		this.fullName = fullName;
		this.authorizationStatus = authorizationStatus;
		this.lockStatus = lockStatus;
	}

	/**
	 * Constructor, creates and initializes the entity of the user
	 * 
	 * @param userId    user id
	 * @param groupName user group name
	 * @param fullName  user full name
	 */
	public User(int userId, String groupName, String fullName) {
		super();
		this.id = userId;
		this.groupName = groupName;
		this.fullName = fullName;
	}

	/**
	 * Constructor, creates and initializes the entity of the user
	 * 
	 * @param userId     user id
	 * @param lockStatus user lock status
	 */
	public User(int userId, LockStatusEnum lockStatus) {
		super();
		this.id = userId;
		this.lockStatus = lockStatus;
	}

	/**
	 * Constructor, creates and initializes the entity of the user
	 * 
	 * @param userId   user id
	 * @param fullName user full name
	 */
	public User(int userId, String fullName) {
		super();
		this.id = userId;
		this.fullName = fullName;
	}

	/**
	 * Constructor, creates and initializes the entity of the user
	 * 
	 * @param userId user id
	 */
	public User(int userId) {
		super();
		this.id = userId;
	}

	/**
	 * Constructor, creates and initializes the entity of the user
	 * 
	 * @param fullName user full name
	 */
	public User(String fullName) {
		super();
		this.fullName = fullName;
	}

	/**
	 * @return user id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return user group id
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @return user group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @return user login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return user password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return user full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return user authorization status
	 */
	public AuthorizationStatusEnum getAuthorizationStatus() {
		return authorizationStatus;
	}

	/**
	 * @return user lock status
	 */
	public LockStatusEnum getLockStatus() {
		return lockStatus;
	}

	/**
	 * Implementing the Null Object Pattern for user
	 */
	public static final User NULL_USER = new User(0, 0, "Null user group name", "Null user login", "Null user password",
			"Null user full name", AuthorizationStatusEnum.NOT_AUTHORIZED, LockStatusEnum.UNLOCKED) {
	};
}