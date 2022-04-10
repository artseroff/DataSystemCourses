package ru.rsreu.serovtorzhkova0108.command.client;

import ru.rsreu.serovtorzhkova0108.command.ActionCommand;
import ru.rsreu.serovtorzhkova0108.command.LoginCommand;
import ru.rsreu.serovtorzhkova0108.command.LogoutCommand;
import ru.rsreu.serovtorzhkova0108.command.ShowUserTypeMainPageCommand;
import ru.rsreu.serovtorzhkova0108.command.administrator.AddUserCommand;
import ru.rsreu.serovtorzhkova0108.command.administrator.DeleteUserCommand;
import ru.rsreu.serovtorzhkova0108.command.administrator.EditUserCommand;
import ru.rsreu.serovtorzhkova0108.command.administrator.ShowAddUserPageCommand;
import ru.rsreu.serovtorzhkova0108.command.administrator.ShowEditUserPageCommand;
import ru.rsreu.serovtorzhkova0108.command.course.ShowCourseDescriptionCommand;
import ru.rsreu.serovtorzhkova0108.command.course.ShowJournalCommand;
import ru.rsreu.serovtorzhkova0108.command.moderator.ChangeUserLockStatusCommand;
import ru.rsreu.serovtorzhkova0108.command.student.SetAnnouncementAsViewedByStudentCommand;
import ru.rsreu.serovtorzhkova0108.command.student.EnrollInCourseCommand;
import ru.rsreu.serovtorzhkova0108.command.student.LeaveCourseCommand;
import ru.rsreu.serovtorzhkova0108.command.student.ShowAnnouncementsForStudentCommand;
import ru.rsreu.serovtorzhkova0108.command.student.ShowMyCoursesCommand;
import ru.rsreu.serovtorzhkova0108.command.student.ShowOtherCoursesCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.AddAnnouncementCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.AddCourseCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.AddLessonCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.DeleteAnnouncementCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.DeleteCourseCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.DeleteLessonCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.RefreshFinalGradesCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.RefreshJournalCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.ShowAddCoursePageCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.ShowAnnouncementViewersCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.ShowAnnouncementsCommand;
import ru.rsreu.serovtorzhkova0108.command.teacher.ShowFinalGradesCommand;

public enum CommandEnum {

	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	},
	SHOW_ADD_USER_PAGE {
		{
			this.command = new ShowAddUserPageCommand();
		}
	},
	ADD_USER {
		{
			this.command = new AddUserCommand();
		}
	},
	SHOW_EDIT_USER_PAGE {
		{
			this.command = new ShowEditUserPageCommand();
		}
	},
	EDIT_USER {
		{
			this.command = new EditUserCommand();
		}
	},
	DELETE_USER {
		{
			this.command = new DeleteUserCommand();
		}
	},
	SHOW_USER_TYPE_MAIN_PAGE {
		{
			this.command = new ShowUserTypeMainPageCommand();
		}
	},
	CHANGE_USER_LOCK_STATUS {
		{
			this.command = new ChangeUserLockStatusCommand();
		}
	},
	SHOW_COURSE_DESCRIPTION {
		{
			this.command = new ShowCourseDescriptionCommand();
		}
	},
	SHOW_JOURNAL {
		{
			this.command = new ShowJournalCommand();
		}
	},
	REFRESH_JOURNAL {
		{
			this.command = new RefreshJournalCommand();
		}
	},
	ADD_LESSON {
		{
			this.command = new AddLessonCommand();
		}
	},
	DELETE_LESSON {
		{
			this.command = new DeleteLessonCommand();
		}
	},
	SHOW_ANNOUNCEMENTS {
		{
			this.command = new ShowAnnouncementsCommand();
		}
	},
	ADD_ANNOUNCEMENT {
		{
			this.command = new AddAnnouncementCommand();
		}
	},
	DELETE_ANNOUNCEMENT {
		{
			this.command = new DeleteAnnouncementCommand();
		}
	},
	SHOW_ANNOUNCEMENT_VIEWERS {
		{
			this.command = new ShowAnnouncementViewersCommand();
		}
	},
	SHOW_FINAL_GRADES {
		{
			this.command = new ShowFinalGradesCommand();
		}
	},
	REFRESH_FINAL_GRADES {
		{
			this.command = new RefreshFinalGradesCommand();
		}
	},
	SHOW_ADD_COURSE_PAGE {
		{
			this.command = new ShowAddCoursePageCommand();
		}
	},
	ADD_COURSE {
		{
			this.command = new AddCourseCommand();
		}
	},
	DELETE_COURSE {
		{
			this.command = new DeleteCourseCommand();
		}
	},
	SHOW_MY_COURSES {
		{
			this.command = new ShowMyCoursesCommand();
		}
	},
	SHOW_OTHER_COURSES {
		{
			this.command = new ShowOtherCoursesCommand();
		}
	},
	ENROLL_IN_COURSE {
		{
			this.command = new EnrollInCourseCommand();
		}
	},
	LEAVE_COURSE {
		{
			this.command = new LeaveCourseCommand();
		}
	},
	SHOW_ANNOUNCEMENTS_FOR_STUDENT {
		{
			this.command = new ShowAnnouncementsForStudentCommand();
		}
	},
	CHECK_ANNOUNCEMENT_IS_VIEWED {
		{
			this.command = new SetAnnouncementAsViewedByStudentCommand();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}