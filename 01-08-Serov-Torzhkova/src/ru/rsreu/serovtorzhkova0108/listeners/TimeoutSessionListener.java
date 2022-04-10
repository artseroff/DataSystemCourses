package ru.rsreu.serovtorzhkova0108.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.LogoutLogic;

public class TimeoutSessionListener implements HttpSessionListener {

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		User user = (User) event.getSession().getAttribute("user");
		if (user != null) {
			LogoutLogic.updateUserStatusNotAuthorizated(user);
		}
	}
}