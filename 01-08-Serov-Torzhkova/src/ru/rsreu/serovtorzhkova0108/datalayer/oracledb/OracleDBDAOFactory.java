package ru.rsreu.serovtorzhkova0108.datalayer.oracledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.datalayer.dao.CourseDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.dao.UserDAO;

public class OracleDBDAOFactory extends DAOFactory {

	private static volatile OracleDBDAOFactory instance;
	private Connection connection;

	private OracleDBDAOFactory() {
	}

	public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
		OracleDBDAOFactory factory = instance;
		if (instance == null) {
			synchronized (OracleDBDAOFactory.class) {
				instance = factory = new OracleDBDAOFactory();
				factory.connected();
			}
		}
		return factory;
	}

	private void connected() throws ClassNotFoundException, SQLException {
		Locale.setDefault(Locale.ENGLISH);
		String url = Resourcer.getString("db.url");
		String user = Resourcer.getString("db.user");
		String password = Resourcer.getString("db.password");
		this.connection = DriverManager.getConnection(url, user, password);
	}

	@Override
	public UserDAO getUserDAO() {
		return new OracleUserDAO(this.connection);
	}

	@Override
	public CourseDAO getCourseDAO() {
		return new OracleCourseDAO(this.connection);
	}
}