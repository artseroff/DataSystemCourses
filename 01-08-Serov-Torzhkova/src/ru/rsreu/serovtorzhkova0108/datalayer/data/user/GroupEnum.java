package ru.rsreu.serovtorzhkova0108.datalayer.data.user;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.logic.AdministratorLogic;
import ru.rsreu.serovtorzhkova0108.logic.ModeratorLogic;
import ru.rsreu.serovtorzhkova0108.logic.StudentLogic;
import ru.rsreu.serovtorzhkova0108.logic.TeacherLogic;

/**
 * Enumeration of user groups
 * 
 * @author Serov and Torzhkova
 *
 */
public enum GroupEnum {

	ADMINISTRATOR("администратор", Resourcer.getString("path.page.admin.main")) {
		@Override
		public void setRequestAttributes(HttpServletRequest request) {
			request.getSession(false).setAttribute("listUsers", AdministratorLogic.getAllUsers());
		}
	},
	MODERATOR("модератор", Resourcer.getString("path.page.moderator.main")) {
		@Override
		public void setRequestAttributes(HttpServletRequest request) {
			request.getSession(false).setAttribute("listUsers", ModeratorLogic.getAllUsers());
		}
	},
	TEACHER("преподаватель", Resourcer.getString("path.page.teacher.main")) {
		@Override
		public void setRequestAttributes(HttpServletRequest request) {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");
			session.setAttribute("listCourses", TeacherLogic.getAllCoursesWithTeacher(user));
		}
	},
	STUDENT("студент", Resourcer.getString("path.page.student.myCourses")) {
		@Override
		public void setRequestAttributes(HttpServletRequest request) {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("user");
			session.setAttribute("listCourses", StudentLogic.getStudentCourses(user));
		}
	};

	/** The string value of the group */
	private final String group;
	/** The path to user group main page */
	private final String mainPage;

	/**
	 * Constructor, creates and initializes the value of the user group
	 * 
	 * @param group    string value of group
	 * @param mainPage path to user group main page
	 */
	GroupEnum(String group, String mainPage) {
		this.group = group;
		this.mainPage = mainPage;
	}

	/**
	 * @return string value of group
	 */
	public final String getGroup() {
		return this.group;
	}

	/**
	 * @return path to user group main page
	 */
	public final String getMainPage() {
		return this.mainPage;
	}

	/**
	 * Searches for the value of the user group by its string representation
	 * 
	 * @param name string value of group
	 * @return user group
	 * @throws IllegalArgumentException is throwing if were passed illegal
	 *                                  parameters
	 */
	public static GroupEnum findGroupByName(String name) throws IllegalArgumentException {
		return Arrays.stream(GroupEnum.values()).filter(v -> v.getGroup().equals(name)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Unknown user"));
	}

	/**
	 * Prepares the page for display by setting attributes to the session using a
	 * request and returns the address of the main page for the user group
	 * 
	 * @param request http request
	 * @return main page for user group
	 */
	public String getMainPageAndSetRequestAttributes(HttpServletRequest request) {
		setRequestAttributes(request);
		return getMainPage();
	}

	/**
	 * Prepares the page for display by setting attributes to the session using a
	 * request
	 * 
	 * @param request http request
	 */
	public abstract void setRequestAttributes(HttpServletRequest request);
}