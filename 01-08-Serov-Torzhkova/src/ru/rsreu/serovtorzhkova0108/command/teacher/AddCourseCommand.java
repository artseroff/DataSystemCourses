package ru.rsreu.serovtorzhkova0108.command.teacher;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class AddCourseCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_NAME = "name";
	private static final String PARAM_NAME_COURSE_DESCRIPTION = "description";
	private static final String PARAM_NAME_COURSE_START_DATE = "startDate";
	private static final String PARAM_NAME_COURSE_END_DATE = "endDate";
	private static final String PARAM_NAME_MAX_STUDENTS_AMOUNT = "maxStudentsAmount";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		User teacher = (User) request.getSession(false).getAttribute("user");
		Date startDate = Date.valueOf(request.getParameter(PARAM_NAME_COURSE_START_DATE));
		Date endDate = Date.valueOf(request.getParameter(PARAM_NAME_COURSE_END_DATE));
		int maxStudentAmount = Integer.parseInt(request.getParameter(PARAM_NAME_MAX_STUDENTS_AMOUNT));
		Course course = new Course(request.getParameter(PARAM_NAME_COURSE_NAME),
				request.getParameter(PARAM_NAME_COURSE_DESCRIPTION), teacher, startDate, endDate, maxStudentAmount);
		if (!TeacherLogic.checkIfCourseWithNameAndDatesExists(course.getName(), course.getStartDate(),
				course.getEndDate())) {
			TeacherLogic.addCourse(course);
			page = GroupEnum.TEACHER.getMainPageAndSetRequestAttributes(request);
		} else {
			HttpSession session = request.getSession(false);
			session.setAttribute("errorMessage", Resourcer.getString("message.teacher.existedCourse"));
			session.setAttribute("course", course);
			page = Resourcer.getString("path.page.teacher.addCourse");
		}
		return page;
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}