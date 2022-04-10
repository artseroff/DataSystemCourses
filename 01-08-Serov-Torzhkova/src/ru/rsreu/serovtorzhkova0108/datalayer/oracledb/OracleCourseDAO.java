package ru.rsreu.serovtorzhkova0108.datalayer.oracledb;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.serovtorzhkova0108.datalayer.dao.CourseDAO;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Announcement;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Course;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.Lesson;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.FinalGrade;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.FinalGradeEnum;
import ru.rsreu.serovtorzhkova0108.datalayer.data.course.grades.MidtermGrade;
import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

public class OracleCourseDAO implements CourseDAO {

	private Connection connection;

	public OracleCourseDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Course> getAllCoursesByTeacher(User teacher) {
		PreparedStatement preparedStatement = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.all.byTeacher"));
			preparedStatement.setInt(1, teacher.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course course;
				course = new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						new User(resultSet.getInt(4), resultSet.getString(5)), resultSet.getDate(6),
						resultSet.getDate(7), resultSet.getInt(8));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courses;
	}

	@Override
	public Course getCourseDescriptionById(int id) {
		PreparedStatement preparedStatement = null;
		Course course = Course.NULL_COURSE;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.withDescription"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				course = new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						new User(resultSet.getInt(4), resultSet.getString(5)), resultSet.getDate(6),
						resultSet.getDate(7), resultSet.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return course;
	}

	@Override
	public void updateAbsentStudentsWithMidtermGrades(Lesson lesson) {
		deleteAllAbsentStudentsFromLesson(lesson);
		deleteAllMidtermGradesFromLesson(lesson);
		addAbsentStudents(lesson);
		addMidtermGrades(lesson);
	}

	private void deleteAllAbsentStudentsFromLesson(Lesson lesson) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.fromLesson.absentStudents"));
			preparedStatement.setInt(1, lesson.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void deleteAllMidtermGradesFromLesson(Lesson lesson) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.fromLesson.midtermGrades"));
			preparedStatement.setInt(1, lesson.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void addAbsentStudents(Lesson lesson) {
		PreparedStatement preparedStatement = null;
		try {
			for (User absentStudent : lesson.getAbsentStudents()) {
				preparedStatement = this.connection
						.prepareStatement(Resourcer.getString("sql.course.insert.absentStudents"));
				preparedStatement.setInt(1, lesson.getId());
				preparedStatement.setInt(2, absentStudent.getId());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void addMidtermGrades(Lesson lesson) {
		PreparedStatement preparedStatement = null;
		try {
			for (MidtermGrade midtermGrade : lesson.getMidtermGrades()) {
				preparedStatement = this.connection
						.prepareStatement(Resourcer.getString("sql.course.insert.midtermGrades"));
				preparedStatement.setInt(1, lesson.getId());
				preparedStatement.setInt(2, midtermGrade.getStudent().getId());
				preparedStatement.setInt(3, midtermGrade.getGrade());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addLessonToCourse(Lesson lesson, Course course) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.insert.lesson"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setDate(2, lesson.getDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteLesson(Lesson lesson) {
		deleteAllAbsentStudentsFromLesson(lesson);
		deleteAllMidtermGradesFromLesson(lesson);
		deleteLessonFromCourse(lesson);
	}

	private void deleteLessonFromCourse(Lesson lesson) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete.lesson"));
			preparedStatement.setInt(1, lesson.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Lesson getLessonByDateInCourse(Date date, Course course) {
		PreparedStatement preparedStatement = null;
		Lesson lesson = Lesson.NULL_LESSON;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.lesson.byDate"));
			preparedStatement.setDate(1, date);
			preparedStatement.setInt(2, course.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				lesson = new Lesson(resultSet.getInt(1));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lesson;
	}

	@Override
	public Course getCourseWithAnnouncementsById(int id) {
		PreparedStatement preparedStatement = null;
		Course course = Course.NULL_COURSE;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.withAnnouncements"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Announcement> announcements = new ArrayList<Announcement>();
			while (resultSet.next()) {
				int announcementId = resultSet.getInt(1);
				Announcement announcement = new Announcement(announcementId, resultSet.getString(2),
						resultSet.getDate(3), getViewersByAnnouncementId(announcementId));
				announcements.add(announcement);
			}
			course = new Course(id, "", announcements);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return course;
	}

	private List<User> getViewersByAnnouncementId(int id) {
		PreparedStatement preparedStatement = null;
		List<User> viewers = new ArrayList<User>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.viewers.byAnnouncement"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User student = new User(resultSet.getString(1));
				viewers.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return viewers;
	}

	@Override
	public void addAnnouncementToCourse(Announcement announcement, Course course) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.insert.announcement"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setDate(2, announcement.getDate());
			preparedStatement.setString(3, announcement.getDescription());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteAnnouncement(Announcement announcement) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.fromAnnouncement.viewers"));
			preparedStatement.setInt(1, announcement.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete.announcement"));
			preparedStatement.setInt(1, announcement.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Announcement getAnnouncementById(int announcementId) {
		PreparedStatement preparedStatement = null;
		Announcement announcement = Announcement.NULL_ANOUNCEMENT;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.select.announcement"));
			preparedStatement.setInt(1, announcementId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				announcement = new Announcement(announcementId, resultSet.getString(1), resultSet.getDate(2),
						getViewersByAnnouncementId(announcementId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return announcement;
	}

	@Override
	public Course getCourseWithAttendanceAndGradesById(int id) {
		PreparedStatement preparedStatement = null;
		Course course = Course.NULL_COURSE;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.withAttendanceAndGrades"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Lesson> lessons = new ArrayList<Lesson>();
			while (resultSet.next()) {
				int lessonId = resultSet.getInt(1);
				lessons.add(new Lesson(lessonId, resultSet.getDate(2), getAbsentStudentsByLessonId(lessonId),
						getMidtermGradesByLessonId(lessonId)));
			}
			course = new Course(id, getStudentsByCourseId(id), lessons, getFinalGradesByCourseId(id));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return course;
	}

	private List<User> getAbsentStudentsByLessonId(int id) {
		PreparedStatement preparedStatement = null;
		List<User> absentStudents = new ArrayList<User>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.absentStudents.byLesson"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User student = new User(resultSet.getInt(1));
				absentStudents.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return absentStudents;
	}

	private List<MidtermGrade> getMidtermGradesByLessonId(int id) {
		PreparedStatement preparedStatement = null;
		List<MidtermGrade> midtermGrades = new ArrayList<MidtermGrade>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.midtermGrades.byLesson"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				MidtermGrade currentMidtermGrade = new MidtermGrade(new User(resultSet.getInt(1)), resultSet.getInt(2));
				midtermGrades.add(currentMidtermGrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return midtermGrades;
	}

	private List<User> getStudentsByCourseId(int id) {
		PreparedStatement preparedStatement = null;
		List<User> students = new ArrayList<User>();
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.select.students"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User student = new User(resultSet.getInt(1), resultSet.getString(2));
				students.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return students;
	}

	private List<FinalGrade> getFinalGradesByCourseId(int id) {
		PreparedStatement preparedStatement = null;
		List<FinalGrade> finalGrades = new ArrayList<FinalGrade>();
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.select.finalGrades"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User(resultSet.getInt(1));
				FinalGradeEnum finalGrade = FinalGradeEnum.findGradeByName(resultSet.getString(3));
				finalGrades.add(new FinalGrade(user, resultSet.getDate(2), finalGrade));
			}
		} catch (SQLException | IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return finalGrades;
	}

	@Override
	public Course getCourseWithStudentsAndFinalGradesById(int id) {
		return new Course(id, getStudentsByCourseId(id), getFinalGradesByCourseId(id));
	}

	@Override
	public void updateFinalGradesInCourse(Course course) {
		deleteAllFinalGradesInCourse(course);
		addFinalGrades(course);
	}

	private void deleteAllFinalGradesInCourse(Course course) {
		deleteFinalGradesFromCourse(course);
	}

	private void deleteFinalGradesFromCourse(Course course) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete.finalGrades"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void addFinalGrades(Course course) {
		PreparedStatement preparedStatement = null;
		try {
			for (FinalGrade finalGrade : course.getFinalGrades()) {
				preparedStatement = this.connection
						.prepareStatement(Resourcer.getString("sql.course.insert.finalGrade"));
				preparedStatement.setInt(1, course.getId());
				preparedStatement.setInt(2, finalGrade.getStudent().getId());
				preparedStatement.setDate(3, finalGrade.getDate());
				preparedStatement.setString(4, finalGrade.getGrade().getName());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Course getCourseByNameAndDates(String name, Date startDate, Date endDate) {
		PreparedStatement preparedStatement = null;
		Course searchedCourse = Course.NULL_COURSE;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.byNameAndDates"));
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, startDate);
			preparedStatement.setDate(3, endDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				searchedCourse = new Course(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return searchedCourse;
	}

	@Override
	public void addCourse(Course course) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.insert"));
			preparedStatement.setString(1, course.getName());
			preparedStatement.setString(2, course.getDescription());
			preparedStatement.setInt(3, course.getTeacher().getId());
			preparedStatement.setDate(4, course.getStartDate());
			preparedStatement.setDate(5, course.getEndDate());
			preparedStatement.setInt(6, course.getMaxStudentsAmount());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteCourse(Course course) {
		deleteFinalGradesFromCourse(course);
		deleteAnnouncementsFromCourse(course);
		deleteLessonsFromCourse(course);
		deleteCourseWithParticipation(course);
	}

	private void deleteAnnouncementsFromCourse(Course course) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.fromAnnouncements.viewers"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.announcements"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void deleteLessonsFromCourse(Course course) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete.midterGrades"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.absentStudents"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete.lessons"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void deleteCourseWithParticipation(Course course) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete.students"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Course> getStudentCourses(User student) {
		PreparedStatement preparedStatement = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.byStudent.withParticipation"));
			preparedStatement.setInt(1, student.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course course;
				course = new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						new User(resultSet.getInt(4), resultSet.getString(5)), resultSet.getDate(6),
						resultSet.getDate(7), resultSet.getInt(8));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courses;
	}

	@Override
	public List<Course> getNotStudentCourses(User student) {
		PreparedStatement preparedStatement = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.byStudent.withoutParticipation"));
			preparedStatement.setInt(1, student.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course course;
				course = new Course(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						new User(resultSet.getInt(4), resultSet.getString(5)), resultSet.getDate(6),
						resultSet.getDate(7), resultSet.getInt(8));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courses;
	}

	@Override
	public List<User> getStudentsParticipatedInCourse(Course course) {
		return getStudentsByCourseId(course.getId());
	}

	@Override
	public void enrollStudentToCourse(Course course, User student) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.insert.student"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void expelStudentFromCourse(Course course, User student) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.delete.finalGrade"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.insert.finalGrade.expelled"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Announcement> getNotViewedByStudentAnnouncementsInCourse(Course course, User student) {
		PreparedStatement preparedStatement = null;
		List<Announcement> announcements = new ArrayList<Announcement>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.announcements.notViewed"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setInt(2, student.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Announcement announcement = new Announcement(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getDate(3));
				announcements.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return announcements;
	}

	@Override
	public List<Announcement> getViewedByStudentAnnouncementsInCourse(Course course, User student) {
		PreparedStatement preparedStatement = null;
		List<Announcement> announcements = new ArrayList<Announcement>();
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.select.announcements.viewed"));
			preparedStatement.setInt(1, course.getId());
			preparedStatement.setInt(2, student.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Announcement announcement = new Announcement(resultSet.getInt(1), resultSet.getString(2),
						resultSet.getDate(3));
				announcements.add(announcement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return announcements;
	}

	@Override
	public void setAnnouncementAsViewedByStudent(Announcement announcement, User student) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement(Resourcer.getString("sql.course.insert.viewer"));
			preparedStatement.setInt(1, announcement.getId());
			preparedStatement.setInt(2, student.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteTeacherCourses(User teacher) {
		List<Course> courses = getAllCoursesByTeacher(teacher);
		for (Course course : courses) {
			deleteCourse(course);
		}
	}

	@Override
	public void deleteStudentCourseInformation(User student) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.finalGrades.byStudent"));
			preparedStatement.setInt(1, student.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.skippingLessons.byStudent"));
			preparedStatement.setInt(1, student.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.midtermGrades.byStudent"));
			preparedStatement.setInt(1, student.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.announcementViews.byStudent"));
			preparedStatement.setInt(1, student.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = this.connection
					.prepareStatement(Resourcer.getString("sql.course.delete.participation.byStudent"));
			preparedStatement.setInt(1, student.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}