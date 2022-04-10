package ru.rsreu.serovtorzhkova0108.command.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.StudentLogic;

public class SetAnnouncementAsViewedByStudentCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_ANNOUNCEMENT_ID = "announcementId";

	@Override
	public String execute(HttpServletRequest request) {
		int announcementId = Integer.parseInt(request.getParameter(PARAM_NAME_ANNOUNCEMENT_ID));
		Announcement announcement = new Announcement(announcementId);
		HttpSession session = request.getSession(false);
		User student = (User) session.getAttribute("user");
		StudentLogic.setAnnouncementAsViewedByStudent(announcement, student);
		int courseId = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(courseId);
		List<Announcement> notViewedAnnouncements = StudentLogic.getNotViewedByStudentAnnouncementsInCourse(course,
				student);
		List<Announcement> viewedAnnouncements = StudentLogic.getViewedByStudentAnnouncementsInCourse(course, student);
		session.setAttribute("notViewedAnnouncements", notViewedAnnouncements);
		session.setAttribute("viewedAnnouncements", viewedAnnouncements);
		session.setAttribute("course", course);
		return Resourcer.getString("path.page.student.announcements");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}