package beans.managers;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import beans.Course;
import beans.User;

public class UserManager {
		
	public static Optional<User> getByEmail(String email) {
		User user = null;
		try {
			user =(User) DataSource.em().createQuery("SELECT user FROM User user WHERE user.email = \"" + email + "\"")
					.getSingleResult();
		} catch (NoResultException e) {}
		return Optional.ofNullable(user);
	}
	    
    public static boolean validate(String email, String password) {
        return DataSource.em().createQuery("SELECT user FROM User user WHERE user.email = \"" + email +"\" AND user.password = " + "\"" + password +  "\"")
                .getResultList().size() == 1;
    }
    
    public static Optional<User> getById(int id) {
        return Optional.ofNullable(DataSource.em().find(User.class, id));
    }
	
	public static Optional<User> getById(Long id) {
		return Optional.ofNullable((User) DataSource.em().createQuery("SELECT user FROM User user WHERE user.id = " + id)
				.getSingleResult());
	}	
	
	public static boolean userIsAttending(User user, Course course){
		return DataSource.em().createQuery("SELECT course FROM Userattending user WHERE user.id = '" + user.getId() + "' AND course.id = " + course.getId())
		.getResultList().size() == 1;
		
	}
	
	public static void save(User user) {
		EntityManager em = DataSource.em();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
    
}
