package ru.rsreu.serovtorzhkova0108.command.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.StudentLogic;

public class ShowAnnouncementsForStudentCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(id);
		User student = (User) request.getSession(false).getAttribute("user");
		List<Announcement> notViewedAnnouncements = StudentLogic.getNotViewedByStudentAnnouncementsInCourse(course,
				student);
		List<Announcement> viewedAnnouncements = StudentLogic.getViewedByStudentAnnouncementsInCourse(course, student);
		request.setAttribute("notViewedAnnouncements", notViewedAnnouncements);
		request.setAttribute("viewedAnnouncements", viewedAnnouncements);
		request.setAttribute("course", course);
		return Resourcer.getString("path.page.student.announcements");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}