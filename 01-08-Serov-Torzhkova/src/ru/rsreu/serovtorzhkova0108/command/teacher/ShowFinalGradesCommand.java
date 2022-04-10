package ru.rsreu.serovtorzhkova0108.command.teacher;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class ShowFinalGradesCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = TeacherLogic.getCourseWithStudentsAndFinalGradesById(id);
		request.setAttribute("course", course);
		return Resourcer.getString("path.page.teacher.finalGrades");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}