package ru.rsreu.serovtorzhkova0108.command.teacher;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Lesson;
import ru.rsreu.serovtorzhkova0108.logic.CourseLogic;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class AddLessonCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_DATE = "lessonDate";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(id);
		String stringDate = request.getParameter(PARAM_NAME_DATE);
		Date sqlDate = Date.valueOf(stringDate);
		Lesson lessonWithDate = new Lesson(sqlDate);
		HttpSession session = request.getSession(false);
		if (TeacherLogic.checkIsDateInDatesRangeCourse(sqlDate, id)) {
			Lesson findedLesson = TeacherLogic.findLessonWithDateInCourse(sqlDate, course);
			if (!findedLesson.equals(Lesson.NULL_LESSON)) {
				session.setAttribute("errorLessonDateIsExists",
						Resourcer.getString("message.teacher.existedLessonDate"));
				session.setAttribute("existedDate", stringDate);
			} else {
				TeacherLogic.addLessonToCourse(lessonWithDate, course);
			}
		} else {
			session.setAttribute("errorLessonDateNotInDatesRangeCourse",
					Resourcer.getString("message.teacher.lessonDateNotInDatesRangeCourse"));
			session.setAttribute("existedDate", stringDate);
		}
		course = CourseLogic.getCourseWithAttendanceAndGradesById(id);
		session.setAttribute("course", course);
		return Resourcer.getString("path.page.teacher.journal");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}