package ru.rsreu.serovtorzhkova0108.command.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.CourseLogic;
import ru.rsreu.serovtorzhkova0108.logic.StudentLogic;

public class EnrollInCourseCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_MAX_STUDENTS_AMOUNT = "maxStudentsAmount";
	private static final String PARAM_NAME_COURSE_NUMBER = "courseNumber";

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		int maxStudentsAmount = Integer.parseInt(request.getParameter(PARAM_NAME_MAX_STUDENTS_AMOUNT));
		Course course = new Course(id, maxStudentsAmount);
		HttpSession session = request.getSession(false);
		User student = (User) session.getAttribute("user");
		if (StudentLogic.isMaximumStudentsAmountReachedInCourse(course)) {
			session.setAttribute("errorNumber", Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_NUMBER)));
			session.setAttribute("courses", StudentLogic.getNotStudentCourses(student));
			return Resourcer.getString("path.page.student.otherCourses");
		} else {
			session.setAttribute("errorNumber", 0);
			StudentLogic.enrollStudentToCourse(course, student);
			course = CourseLogic.getCourseWithDescriptionById(id);
			session.setAttribute("course", course);
			return Resourcer.getString("path.page.student.courseDescription");
		}
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}