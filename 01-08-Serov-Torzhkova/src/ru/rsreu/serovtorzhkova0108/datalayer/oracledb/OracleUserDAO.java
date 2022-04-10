package ru.rsreu.serovtorzhkova0108.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.datalayer.dao.UserDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.AuthorizationStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.LockStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class OracleUserDAO implements UserDAO {

	private Connection connection;

	public OracleUserDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<User> getAllUsersForAdministrator() {
		Statement statement = null;
		List<User> users = new ArrayList<User>();
		try {
			statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(Resourcer.getString("sql.user.select.all.forAdministrator"));
			while (resultSet.next()) {
				User user;
				user = new User(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
						AuthorizationStatusEnum.findAuthorizationStatusByName(resultSet.getString(7)),
						LockStatusEnum.findLockStatusByName(resultSet.getString(8)));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public List<User> getAllUsersForModerator() {
		Statement statement = null;
		List<User> users = new ArrayList<User>();
		try {
			statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(Resourcer.getString("sql.user.select.all.forModerator"));
			while (resultSet.next()) {
				User user;
				user = new User(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
						AuthorizationStatusEnum.findAuthorizationStatusByName(resultSet.getString(7)),
						LockStatusEnum.findLockStatusByName(resultSet.getString(8)));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public void addUser(User user) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.user.insert"));
			GroupEnum group = GroupEnum.findGroupByName(user.getGroupName());
			preparedStatement.setInt(1, group.ordinal() + 1);
			preparedStatement.setString(2, user.getLogin());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getFullName());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateUser(User user) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.user.update"));
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getFullName());
			preparedStatement.setInt(4, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User findUserByLoginAndPassword(String login, String password) {
		PreparedStatement preparedStatement = null;
		User user = User.NULL_USER;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.user.select.byLoginAndPassword"));
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
						AuthorizationStatusEnum.findAuthorizationStatusByName(resultSet.getString(7)),
						LockStatusEnum.findLockStatusByName(resultSet.getString(8)));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void changeUserLockStatus(User user) {
		LockStatusEnum lockstatus = user.getLockStatus();
		if (lockstatus == LockStatusEnum.LOCKED) {
			lockstatus = LockStatusEnum.UNLOCKED;
		} else {
			lockstatus = LockStatusEnum.LOCKED;
		}
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.user.update.lockStatus"));
			preparedStatement.setString(1, lockstatus.getName());
			preparedStatement.setInt(2, user.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteUser(User user) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.user.delete"));
			preparedStatement.setInt(1, user.getId());
			preparedStatement.executeUpdate();
	
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User findUserByIdWithLockStatus(int id) {
		PreparedStatement preparedStatement = null;
		User user = User.NULL_USER;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.user.select.byId.WithLockStatus"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User(resultSet.getInt(1), LockStatusEnum.findLockStatusByName(resultSet.getString(2)));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void updateUserAuthorizationStatus(User user, AuthorizationStatusEnum authorizationStatus) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.user.update.authorizationStatus"));
			preparedStatement.setString(1, authorizationStatus.getName());
			preparedStatement.setInt(2, user.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User getUserByLogin(String login) {
		PreparedStatement preparedStatement = null;
		User user = User.NULL_USER;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.user.select.byLogin"));
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
						AuthorizationStatusEnum.findAuthorizationStatusByName(resultSet.getString(7)),
						LockStatusEnum.findLockStatusByName(resultSet.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
}