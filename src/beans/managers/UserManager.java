package beans.managers;

import beans.User;

public class UserManager extends Manager<User> {

	public User getByEmail(String email) {
		return (User) em().createQuery("SELECT user FROM User user WHERE user.email = " + email)
				.getSingleResult();
	}
	    
    public boolean validate(String email, String password) {
        return em().createQuery("SELECT user FROM User user WHERE user.email = '" + email + "' AND user.password = " + password)
                .getResultList().size() == 1;
    }
	
}
