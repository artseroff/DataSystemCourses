package ru.rsreu.serovtorzhkova0108.datalayer.data.course;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.serovtorzhkova0108.datalayer.data.user.User;

/**
 * A class is an entity of the announcement for the course with the date and
 * text of the announcement and a list of students who viewed it
 * 
 * @author Serov and Torzhkova
 *
 */
public class Announcement {

	/** Announcement id */
	private int id;
	/** Announcement text */
	private String description;
	/** Announcement date */
	private Date date;
	/** Students who viewed the announcement */
	private List<User> viewers;

	/**
	 * Constructor, creates and initializes the entity of the announcement
	 * 
	 * @param id          announcement id
	 * @param description announcement text
	 * @param date        announcement date
	 * @param viewers     students who viewed the announcement
	 */
	public Announcement(int id, String description, Date date, List<User> viewers) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.viewers = viewers;
	}

	/**
	 * Constructor, creates and initializes the entity of the announcement
	 * 
	 * @param id          announcement id
	 * @param description announcement text
	 * @param date        announcement date
	 */
	public Announcement(int id, String description, Date date) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
	}

	/**
	 * Constructor, creates and initializes the entity of the announcement
	 * 
	 * @param description announcement text
	 * @param date        announcement date
	 */
	public Announcement(String description, Date date) {
		super();
		this.description = description;
		this.date = date;
	}

	/**
	 * Constructor, creates and initializes the entity of the announcement
	 * 
	 * @param id announcement id
	 */
	public Announcement(int id) {
		super();
		this.id = id;
	}

	/**
	 * @return announcement id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @return announcement text
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @return announcement date
	 */
	public final Date getDate() {
		return date;
	}

	/**
	 * @return students who viewed the announcement
	 */
	public final List<User> getViewers() {
		return viewers;
	}

	/**
	 * Implementing the Null Object Pattern for announcement
	 */
	public static final Announcement NULL_ANOUNCEMENT = new Announcement(0, "Null anouncement description", new Date(0),
			new ArrayList<User>()) {
	};
}