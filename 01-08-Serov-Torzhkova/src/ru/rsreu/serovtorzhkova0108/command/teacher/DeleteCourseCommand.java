package ru.rsreu.serovtorzhkova0108.command.teacher;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class DeleteCourseCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(id);
		TeacherLogic.deleteCourse(course);
		return GroupEnum.TEACHER.getMainPageAndSetRequestAttributes(request);
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}