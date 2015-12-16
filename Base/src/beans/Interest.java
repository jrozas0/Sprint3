package beans;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the INTEREST database table.
 * 
 */
@Entity
@Table(name="INTEREST")
@NamedQuery(name="Interest.findAll", query="SELECT i FROM Interest i")
public class Interest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String interest;

	//bi-directional many-to-one association to Userinterest
	@OneToMany(mappedBy="interest")
	private List<Userinterest> userinterests;

	public Interest() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInterest() {
		return this.interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public List<Userinterest> getUserinterests() {
		return this.userinterests;
	}

	public void setUserinterests(List<Userinterest> userinterests) {
		this.userinterests = userinterests;
	}

	public Userinterest addUserinterest(Userinterest userinterest) {
		getUserinterests().add(userinterest);
		userinterest.setInterest(this);

		return userinterest;
	}

	public Userinterest removeUserinterest(Userinterest userinterest) {
		getUserinterests().remove(userinterest);
		userinterest.setInterest(null);

		return userinterest;
	}

}