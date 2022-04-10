package ru.rsreu.serovtorzhkova0108.datalayer.dao;

import ru.rsreu.serovtorzhkova0108.datalayer.DBType;

public abstract class DAOFactory {

	public static DAOFactory getInstance(DBType dbType) {
		DAOFactory result = dbType.getDAOFactory();
		return result;
	}

	public abstract UserDAO getUserDAO();

	public abstract CourseDAO getCourseDAO();
}