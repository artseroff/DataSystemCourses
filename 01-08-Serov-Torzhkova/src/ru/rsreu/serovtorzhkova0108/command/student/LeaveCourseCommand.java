package ru.rsreu.serovtorzhkova0108.command.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.StudentLogic;

public class LeaveCourseCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(id);
		HttpSession session = request.getSession(false);
		User student = (User) session.getAttribute("user");
		StudentLogic.expelStudentFromCourse(course, student);
		return GroupEnum.STUDENT.getMainPageAndSetRequestAttributes(request);
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}