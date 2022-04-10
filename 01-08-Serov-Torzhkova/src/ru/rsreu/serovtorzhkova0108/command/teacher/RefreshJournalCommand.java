package ru.rsreu.serovtorzhkova0108.command.teacher;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Lesson;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.MidtermGrade;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.CourseLogic;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class RefreshJournalCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_STUDENTS_AMOUNT = "studentsAmount";
	private static final String PARAM_NAME_LESSONS_AMOUNT = "lessonsAmount";
	private static final String PARAM_NAME_STUDENT = "student";
	private static final String PARAM_NAME_LESSON = "lesson";
	private static final String PARAM_NAME_ATTENDANCE_OR_GRADE = "attendanceOrGrade";

	@Override
	public String execute(HttpServletRequest request) {
		int courseId = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		Course course = new Course(courseId);
		int studentsAmount = Integer.parseInt(request.getParameter(PARAM_NAME_STUDENTS_AMOUNT));
		int lessonsAmount = Integer.parseInt(request.getParameter(PARAM_NAME_LESSONS_AMOUNT));
		for (int j = 1; j <= lessonsAmount; j++) {
			int lessonId = Integer.parseInt(request.getParameter(String.format("%s%d", PARAM_NAME_LESSON, j)));
			Lesson lesson = new Lesson(lessonId, new ArrayList<User>(), new ArrayList<MidtermGrade>());
			for (int i = 1; i <= studentsAmount; i++) {
				int studentId = Integer.parseInt(request.getParameter(String.format("%s%d", PARAM_NAME_STUDENT, i)));
				User student = new User(studentId);
				String valueAttendanceOrGrade = request
						.getParameter(String.format("%s%d%d", PARAM_NAME_ATTENDANCE_OR_GRADE, i, j));
				if (valueAttendanceOrGrade.matches("[2-5]{1}")) {
					int grade = Integer.parseInt(valueAttendanceOrGrade);
					lesson.getMidtermGrades().add(new MidtermGrade(student, grade));
				} else {
					if (!"".equals(valueAttendanceOrGrade)) {
						lesson.getAbsentStudents().add(student);
					}
				}
			}
			TeacherLogic.updateAbsentStudentsWithMidtermGrades(lesson);
		}
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