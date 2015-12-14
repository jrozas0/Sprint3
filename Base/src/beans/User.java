package beans;

import java.io.Serializable;

import javax.persistence.*;

import beans.managers.UserManager;

import java.util.List;


/**
 * The persistent class for the USER database table.
 * 
 */
@Table(name="USER")
@Entity(name="User")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String address;

	@Column(nullable=false)
	private int age;

	@Column(nullable=false, length=255)
	private String description;

	@Column(nullable=false, length=255)
	private String email;

	@Column(nullable=false, length=45)
	private String name;

	@Column(nullable=false, length=255)
	private String nick;

	@Column(nullable=false, length=255)
	private String password;

	@Column(nullable=false, length=255)
	private String paymentData;

	@Column(nullable=false)
	private int phone;

	@Lob
	private byte[] pic;

	@Column(nullable=false, length=45)
	private String surname;

	@Column(nullable=false, length=45)
	private String type;

	//bi-directional many-to-one association to Certificate
	@OneToMany(mappedBy="user")
	private List<Certificate> certificates;

	//bi-directional many-to-one association to Course
	@OneToMany(mappedBy="user")
	private List<Course> courses;

	//bi-directional many-to-one association to Userattending
	@OneToMany(mappedBy="user")
	private List<Userattending> userattendings;

	//bi-directional many-to-one association to Userinterest
	@OneToMany(mappedBy="user")
	private List<Userinterest> userinterests;

	//bi-directional many-to-one association to Userteaching
	@OneToMany(mappedBy="user")
	private List<Userteaching> userteachings;

	//bi-directional many-to-one association to Userwishing
	@OneToMany(mappedBy="user")
	private List<Userwishing> userwishings;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPaymentData() {
		return this.paymentData;
	}

	public void setPaymentData(String paymentData) {
		this.paymentData = paymentData;
	}

	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public byte[] getPic() {
		return this.pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Certificate> getCertificates() {
		return this.certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Certificate addCertificate(Certificate certificate) {
		getCertificates().add(certificate);
		certificate.setUser(this);

		return certificate;
	}

	public Certificate removeCertificate(Certificate certificate) {
		getCertificates().remove(certificate);
		certificate.setUser(null);

		return certificate;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCours(Course cours) {
		getCourses().add(cours);
		cours.setUser(this);

		return cours;
	}

	public Course removeCours(Course cours) {
		getCourses().remove(cours);
		cours.setUser(null);

		return cours;
	}

	public List<Userattending> getUserattendings() {
		return this.userattendings;
	}

	public void setUserattendings(List<Userattending> userattendings) {
		this.userattendings = userattendings;
	}

	public Userattending addUserattending(Userattending userattending) {
		getUserattendings().add(userattending);
		userattending.setUser(this);

		return userattending;
	}

	public Userattending removeUserattending(Userattending userattending) {
		getUserattendings().remove(userattending);
		userattending.setUser(null);

		return userattending;
	}

	public List<Userinterest> getUserinterests() {
		return this.userinterests;
	}

	public void setUserinterests(List<Userinterest> userinterests) {
		this.userinterests = userinterests;
	}

	public Userinterest addUserinterest(Userinterest userinterest) {
		getUserinterests().add(userinterest);
		userinterest.setUser(this);

		return userinterest;
	}

	public Userinterest removeUserinterest(Userinterest userinterest) {
		getUserinterests().remove(userinterest);
		userinterest.setUser(null);

		return userinterest;
	}

	public List<Userteaching> getUserteachings() {
		return this.userteachings;
	}
	
	public void setUserteachings(List<Userteaching> userteachings) {
		this.userteachings = userteachings;
	}

	public Userteaching addUserteaching(Userteaching userteaching) {
		getUserteachings().add(userteaching);
		userteaching.setUser(this);

		return userteaching;
	}

	public Userteaching removeUserteaching(Userteaching userteaching) {
		getUserteachings().remove(userteaching);
		userteaching.setUser(null);

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
		userwishing.setUser(this);

		return userwishing;
	}

	public Userwishing removeUserwishing(Userwishing userwishing) {
		getUserwishings().remove(userwishing);
		userwishing.setUser(null);

		return userwishing;
	}
	
	public boolean isTeacher() {
			
			if (this.getType().compareTo("teacher") == 0){
				return true;
			
			} else {
				return false;
			}
	}
	
	public boolean isStudent() {
		
		if (this.getType().compareTo("student") == 0){
			return true;
		
		} else {
			return false;
		}
}
	
	public boolean isAttending(Course course){
		
		return UserManager.userIsAttending(this, course);
		
	}
	
	public boolean isTeaching(Course course){
		
		return UserManager.userIsTeaching(this, course);
		
	}


}