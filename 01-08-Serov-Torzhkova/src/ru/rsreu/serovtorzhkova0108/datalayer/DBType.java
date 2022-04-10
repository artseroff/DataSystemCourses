package ru.rsreu.serovtorzhkova0108.datalayer;

import java.sql.SQLException;

import ru.rsreu.serovtorzhkova0108.datalayer.dao.DAOFactory;
import ru.rsreu.serovtorzhkova0108.datalayer.oracledb.OracleDBDAOFactory;

public enum DBType {

	ORACLE {
		@Override
		public DAOFactory getDAOFactory() {
			DAOFactory oracleDBDAOFactory = null;
			try {
				oracleDBDAOFactory = OracleDBDAOFactory.getInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return oracleDBDAOFactory;
		}
	};

	public static DBType getTypeByName(String dbType) {
		try {
			return DBType.valueOf(dbType.toUpperCase());
		} catch (Exception e) {
			throw new DBTypeException();
		}
	}

	public abstract DAOFactory getDAOFactory();
}