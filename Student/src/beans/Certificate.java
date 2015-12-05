package beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CERTIFICATES database table.
 * 
 */
@Entity
@Table(name="CERTIFICATES")
@NamedQuery(name="Certificate.findAll", query="SELECT c FROM Certificate c")
public class Certificate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=255)
	private String certificateHash;

	@Column(length=45)
	private String courseSeed;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="User_id", nullable=false)
	private User user;

	public Certificate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCertificateHash() {
		return this.certificateHash;
	}

	public void setCertificateHash(String certificateHash) {
		this.certificateHash = certificateHash;
	}

	public String getCourseSeed() {
		return this.courseSeed;
	}

	public void setCourseSeed(String courseSeed) {
		this.courseSeed = courseSeed;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}