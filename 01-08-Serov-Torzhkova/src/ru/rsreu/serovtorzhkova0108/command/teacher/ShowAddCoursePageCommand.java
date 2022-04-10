package ru.rsreu.serovtorzhkova0108.command.teacher;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;

public class ShowAddCoursePageCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return Resourcer.getString("path.page.teacher.addCourse");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}