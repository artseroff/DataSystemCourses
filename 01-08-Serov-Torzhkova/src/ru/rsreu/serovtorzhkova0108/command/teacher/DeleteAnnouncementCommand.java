package ru.rsreu.serovtorzhkova0108.command.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class DeleteAnnouncementCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_ANNOUNCEMENT_ID = "announcementId";

	@Override
	public String execute(HttpServletRequest request) {
		int announcementId = Integer.parseInt(request.getParameter(PARAM_NAME_ANNOUNCEMENT_ID));
		Announcement announcement = new Announcement(announcementId);
		TeacherLogic.deleteAnnouncement(announcement);
		int courseId = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = TeacherLogic.getCourseWithAnnouncementsById(courseId);
		HttpSession session = request.getSession(false);
		session.setAttribute("course", course);
		return Resourcer.getString("path.page.teacher.announcements");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}