package ru.rsreu.serovtorzhkova0108.datalayer.dao;

import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.AuthorizationStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

/**
 * Ñontains methods for obtaining data and editing them for the entity User 
 * 
 * @author Serov and Torzhkova
 *
 */
public interface UserDAO {

	/**
	 * Gets all users with their information sorted by authorization status, group and login
	 * @return list of users sorted by authorization status, group and login
	 */
	List<User> getAllUsersForAdministrator();

	/**
	 * Gets all users with their information sorted by lock status, group and login
	 * @return list of users sorted by lock status, group and login
	 */
	List<User> getAllUsersForModerator();

	/**
	 * Adds user
	 * 
	 * @param user added user
	 */
	void addUser(User user);

	/**
	 * Update user
	 * 
	 * @param user updated user
	 */
	void updateUser(User user);

	/**
	 * Finds user with login and password
	 * 
	 * @param login    login
	 * @param password password
	 * @return user with all his information or NULL_USER
	 */
	User findUserByLoginAndPassword(String login, String password);

	/**
	 * Changes user lock status
	 * 
	 * @param user edited user
	 */
	void changeUserLockStatus(User user);

	/**
	 * Deletes user with all its information
	 * 
	 * @param user deleted user
	 */
	void deleteUser(User user);

	/**
	 * Finds user with lock status by user id
	 * 
	 * @param id user id
	 * @return user with lock status or NULL_USER
	 */
	User findUserByIdWithLockStatus(int id);

	/**
	 * Set parameter authorizationStatus to user
	 * 
	 * @param user 				  edited user
	 * @param authorizationStatus authorizationStatus
	 */
	void updateUserAuthorizationStatus(User user, AuthorizationStatusEnum authorizationStatus);

	/**
	 * Gets user by login
	 * 
	 * @param login login
	 * @return user or NULL_USER
	 */
	User getUserByLogin(String login);
}