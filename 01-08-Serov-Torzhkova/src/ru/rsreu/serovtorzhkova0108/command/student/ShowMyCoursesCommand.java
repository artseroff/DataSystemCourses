package ru.rsreu.serovtorzhkova0108.command.student;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.StudentLogic;

public class ShowMyCoursesCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		User user = (User) request.getSession(false).getAttribute("user");
		request.setAttribute("listCourses", StudentLogic.getStudentCourses(user));
		return Resourcer.getString("path.page.student.myCourses");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}