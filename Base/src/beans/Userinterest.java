package beans;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERINTEREST database table.
 * 
 */
@Entity
@Table(name="USERINTEREST")
@NamedQuery(name="Userinterest.findAll", query="SELECT u FROM Userinterest u")
public class Userinterest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER_id")
	private User user;

	//bi-directional many-to-one association to Interest
	@ManyToOne
	@JoinColumn(name="INTEREST_id")
	private Interest interest;

	public Userinterest() {
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

	public Interest getInterest() {
		return this.interest;
	}

	public void setInterest(Interest interest) {
		this.interest = interest;
	}

}