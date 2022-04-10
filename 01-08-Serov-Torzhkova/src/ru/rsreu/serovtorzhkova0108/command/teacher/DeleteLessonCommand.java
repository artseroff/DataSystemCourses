package ru.rsreu.serovtorzhkova0108.command.teacher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Lesson;
import ru.rsreu.serovtorzhkova0108.logic.CourseLogic;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class DeleteLessonCommand implements ActionCommand {

	private static final String PARAM_NAME_LESSON_ID = "lessonId";
	private static final String PARAM_NAME_COURSE_ID = "courseId";

	@Override
	public String execute(HttpServletRequest request) {
		int lessonId = Integer.parseInt(request.getParameter(PARAM_NAME_LESSON_ID));
		Lesson lesson = new Lesson(lessonId);
		int courseId = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(courseId);
		TeacherLogic.deleteLessonFromCourse(lesson);
		course = CourseLogic.getCourseWithAttendanceAndGradesById(courseId);
		HttpSession session = request.getSession(false);
		session.setAttribute("course", course);
		return Resourcer.getString("path.page.teacher.journal");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}