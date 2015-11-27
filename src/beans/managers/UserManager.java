package beans.managers;

import beans.User;
import java.util.Optional;

public class UserManager extends Manager {

	public User getByEmail(String email) {
		return (User) em().createQuery("SELECT user FROM User user WHERE user.email = " + email)
				.getSingleResult();
	}
	
    public Optional<User> get(int id) {
        return Optional.ofNullable(em().find(User.class, id));
    }
	
}
