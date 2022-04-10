package ru.rsreu.serovtorzhkova0108.command.course;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.GroupEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.CourseLogic;

public class ShowJournalCommand implements ActionCommand {

	private static final String PARAM_COURSE_ID = "courseId";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_COURSE_ID));
		Course course = CourseLogic.getCourseWithAttendanceAndGradesById(id);
		request.setAttribute("course", course);
		User user = (User) request.getSession(false).getAttribute("user");
		if (user.getGroupName().equals(GroupEnum.TEACHER.getGroup())) {
			return Resourcer.getString("path.page.teacher.journal");
		} else {
			return Resourcer.getString("path.page.student.journal");
		}
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}