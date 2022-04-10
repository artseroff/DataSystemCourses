package ru.rsreu.serovtorzhkova0108.command.teacher;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class ShowAnnouncementViewersCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_ANNOUNCEMENT_ID = "announcementId";

	@Override
	public String execute(HttpServletRequest request) {
		int announcementId = Integer.parseInt(request.getParameter(PARAM_NAME_ANNOUNCEMENT_ID));
		Announcement announcement = TeacherLogic.getAnnouncementById(announcementId);
		request.setAttribute("announcement", announcement);
		int courseId = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(courseId);
		request.setAttribute("course", course);
		return Resourcer.getString("path.page.teacher.announcementViewers");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.FORWARD;
	}
}