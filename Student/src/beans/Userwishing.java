package beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERWISHING database table.
 * 
 */
@Entity
@Table(name="USERWISHING")
@NamedQuery(name="Userwishing.findAll", query="SELECT u FROM Userwishing u")
public class Userwishing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="User_id", nullable=false)
	private User user;

	//bi-directional many-to-one association to Course
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="Course_id", nullable=false)
	private Course course;

	public Userwishing() {
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