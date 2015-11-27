package beans.managers;

import java.util.Optional;

import beans.User;

public class UserManager {
		
	public static User getByEmail(String email) {
		return (User) DataSource.em().createQuery("SELECT user FROM User user WHERE user.email = " + email)
				.getSingleResult();
	}
	    
    public static boolean validate(String email, String password) {
        return DataSource.em().createQuery("SELECT user FROM User user WHERE user.email = '" + email + "' AND user.password = " + password)
                .getResultList().size() == 1;
    }
    
    public static Optional<User> get(int id) {
        return Optional.ofNullable(DataSource.em().find(User.class, id));
    }
	
}
