package beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SECTION database table.
 * 
 */
@Entity
@Table(name="SECTION")
@NamedQuery(name="Section.findAll", query="SELECT s FROM Section s")
public class Section implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String description;

	//bi-directional many-to-one association to Lesson
	@OneToMany(mappedBy="section")
	private List<Lesson> lessons;

	//bi-directional many-to-one association to Course
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="COURSE_id", nullable=false)
	private Course course;

	public Section() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Lesson> getLessons() {
		return this.lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Lesson addLesson(Lesson lesson) {
		getLessons().add(lesson);
		lesson.setSection(this);

		return lesson;
	}

	public Lesson removeLesson(Lesson lesson) {
		getLessons().remove(lesson);
		lesson.setSection(null);

		return lesson;
	}

	public Course getCourse() {
		return this.course;
	}
	
	public static Course getThisCourse(){
		return getCourse();
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}