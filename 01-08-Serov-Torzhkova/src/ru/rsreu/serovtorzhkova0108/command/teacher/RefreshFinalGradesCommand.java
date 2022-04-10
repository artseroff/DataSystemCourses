package ru.rsreu.serovtorzhkova0108.command.teacher;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.AdressingMethodEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.FinalGrade;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.FinalGradeEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

public class RefreshFinalGradesCommand implements ActionCommand {

	private static final String PARAM_NAME_COURSE_ID = "courseId";
	private static final String PARAM_NAME_STUDENTS_AMOUNT = "studentsAmount";
	private static final String PARAM_NAME_STUDENT = "student";
	private static final String PARAM_GRADE_VALUE = "gradeValue";
	private static final String PARAM_DATE = "date";

	@Override
	public String execute(HttpServletRequest request) {
		int courseId = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE_ID));
		int studentsAmount = Integer.parseInt(request.getParameter(PARAM_NAME_STUDENTS_AMOUNT));
		List<FinalGrade> finalGrade = new ArrayList<FinalGrade>();
		List<User> students = new ArrayList<User>();
		String[] errorMessages = new String[studentsAmount];
		Course course = new Course(courseId, students, finalGrade);
		try {
			for (int i = 1; i <= studentsAmount; i++) {
				String gradeValue = request.getParameter(String.format("%s%d", PARAM_GRADE_VALUE, i));
				if (!"".equals(gradeValue)) {
					int studentId = Integer
							.parseInt(request.getParameter(String.format("%s%d", PARAM_NAME_STUDENT, i)));
					User student = new User(studentId);
					String stringDate = request.getParameter(String.format("%s%d", PARAM_DATE, i));
					if (!"".equals(stringDate)) {
						Date date = Date.valueOf(stringDate);
						if (TeacherLogic.checkIsDateInDatesRangeCourse(date, courseId)) {
							finalGrade.add(new FinalGrade(student, date, FinalGradeEnum.findGradeByName(gradeValue)));
						} else {
							errorMessages[i - 1] = String.format(
									Resourcer.getString("message.teacher.finalGradeDateNotInDatesRangeCourse"),
									stringDate);
						}
					}
				}
			}
			TeacherLogic.updateFinalGradesInCourse(course);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		course = TeacherLogic.getCourseWithStudentsAndFinalGradesById(courseId);
		HttpSession session = request.getSession(false);
		session.setAttribute("errorMessages", errorMessages);
		session.setAttribute("course", course);
		return Resourcer.getString("path.page.teacher.finalGrades");
	}

	@Override
	public AdressingMethodEnum getAdressingMethod() {
		return AdressingMethodEnum.SEND_REDIRECT;
	}
}