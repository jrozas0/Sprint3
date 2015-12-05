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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to User
	@ManyToOne(cascade={CascadeType.REMOVE})
	@JoinColumn(name="USER_id", nullable=false)
	private User user;

	//bi-directional many-to-one association to Interest
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="INTEREST_id", nullable=false)
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