package beans;

import java.io.Serializable;

import javax.persistence.*;

import beans.managers.CourseManager;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@Table(name="COURSE")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private byte denied;

	@Column(nullable=false, length=255)
	private String description;

	@Column(nullable=false)
	private BigInteger difficulty;

	@Column(nullable=false)
	private BigInteger duration;

	@Column(nullable=false)
	private byte highlighted;

	@Column(nullable=false, length=45)
	private String picture;

	@Column(nullable=false)
	private int price;

	@Column(nullable=false)
	private int promotionPrice;

	@Column(nullable=false, length=255)
	private String syllabus;

	@Column(nullable=false, length=45)
	private String title;

	@Column(nullable=false)
	private byte valid;

	//bi-directional many-to-one association to Category
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="Category_id", nullable=false)
	private Category category;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="owner", nullable=false)
	private User user;

	//bi-directional many-to-one association to Section
	@OneToMany(mappedBy="course")
	private List<Section> sections;

	//bi-directional many-to-one association to Userattending
	@OneToMany(mappedBy="course")
	private List<Userattending> userattendings;

	//bi-directional many-to-one association to Userteaching
	@OneToMany(mappedBy="course")
	private List<Userteaching> userteachings;

	//bi-directional many-to-one association to Userwishing
	@OneToMany(mappedBy="course")
	private List<Userwishing> userwishings;

	public Course() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getDenied() {
		return this.denied;
	}
	
	public boolean isDenied() {
		return getDenied() == 0;
	}

	public void setDenied(byte denied) {
		this.denied = denied;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigInteger getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(BigInteger difficulty) {
		this.difficulty = difficulty;
	}

	public BigInteger getDuration() {
		return this.duration;
	}

	public void setDuration(BigInteger duration) {
		this.duration = duration;
	}

	public byte getHighlighted() {
		return this.highlighted;
	}

	public boolean isHighlited(){
		return getHighlighted() == 0;
	}
	
	public void setHighlighted(byte highlighted) {
		this.highlighted = highlighted;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPromotionPrice() {
		return this.promotionPrice;
	}

	public void setPromotionPrice(int promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getSyllabus() {
		return this.syllabus;
	}

	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte getValid() {
		return this.valid;
	}
	
	public boolean isValid() {
		return getValid() == 0;
	}

	public void setValid(byte valid) {
		this.valid = valid;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Section addSection(Section section) {
		getSections().add(section);
		section.setCourse(this);

		return section;
	}

	public Section removeSection(Section section) {
		getSections().remove(section);
		section.setCourse(null);

		return section;
	}

	public List<Userattending> getUserattendings() {
		return this.userattendings;
	}

	public void setUserattendings(List<Userattending> userattendings) {
		this.userattendings = userattendings;
	}

	public Userattending addUserattending(Userattending userattending) {
		getUserattendings().add(userattending);
		userattending.setCourse(this);

		return userattending;
	}

	public Userattending removeUserattending(Userattending userattending) {
		getUserattendings().remove(userattending);
		userattending.setCourse(null);

		return userattending;
	}

	public List<Userteaching> getUserteachings() {
		return this.userteachings;
	}

	public void setUserteachings(List<Userteaching> userteachings) {
		this.userteachings = userteachings;
	}

	public Userteaching addUserteaching(Userteaching userteaching) {
		getUserteachings().add(userteaching);
		userteaching.setCourse(this);

		return userteaching;
	}

	public Userteaching removeUserteaching(Userteaching userteaching) {
		getUserteachings().remove(userteaching);
		userteaching.setCourse(null);

		return userteaching;
	}

	public List<Userwishing> getUserwishings() {
		return this.userwishings;
	}

	public void setUserwishings(List<Userwishing> userwishings) {
		this.userwishings = userwishings;
	}

	public Userwishing addUserwishing(Userwishing userwishing) {
		getUserwishings().add(userwishing);
		userwishing.setCourse(this);

		return userwishing;
	}

	public Userwishing removeUserwishing(Userwishing userwishing) {
		getUserwishings().remove(userwishing);
		userwishing.setCourse(null);
		return userwishing;
	}

}