package ru.rsreu.serovtorzhkova0108.listeners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.LockStatusEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.LoginLogic;

public class LockStatusSessionAttributeListener implements HttpSessionAttributeListener {

	private void processAttribute(HttpSessionBindingEvent event) {
		if (event.getName().equals("lockStatus")) {
			HttpSession session = event.getSession();
			User user = (User) session.getAttribute("user");
			LockStatusEnum databaseLockStatus = LoginLogic.getCurrentLockStatusByUserId(user.getId());
			String valueDisable = "";
			if (databaseLockStatus == LockStatusEnum.LOCKED) {
				valueDisable = "disabled";
			}
			session.setAttribute("disableElement", valueDisable);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
	}

	public void attributeAdded(HttpSessionBindingEvent event) {
		processAttribute(event);
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		processAttribute(event);
	}
}