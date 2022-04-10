package ru.rsreu.serovtorzhkova0108.command.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;
import java.sql.Date;
import java.time.LocalDate;

public class AddAnnouncementCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_DESCRIPTION = "description";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(id);
		Announcement announcement = new Announcement(request.getParameter(PARAM_NAME_DESCRIPTION),
				Date.valueOf(LocalDate.now()));
		TeacherLogic.addAnnouncementToCourse(announcement, course);
		course = TeacherLogic.getCourseWithAnnouncementsById(id);
		HttpSession session = request.getSession(false);
		session.setAttribute("course", course);
		return Resourcer.getString("path.page.teacher.announcements");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}