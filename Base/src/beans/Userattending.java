package beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERATTENDING database table.
 * 
 */
@Entity
@Table(name="USERATTENDING")
@NamedQuery(name="Userattending.findAll", query="SELECT u FROM Userattending u")
public class Userattending implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="User_id")
	private User user;

	//bi-directional many-to-one association to Course
	@ManyToOne
	@JoinColumn(name="Course_id")
	private Course course;

	public Userattending() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}