package beans.managers;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

import beans.Course;
import beans.User;


public class CourseManager {

	public static List<Course> getCourses() {
		return DataSource.em().createNamedQuery("Course.findAll").getResultList();
	}
	
    public static Optional<Course> getById(int id) {
        return Optional.ofNullable(DataSource.em().find(Course.class, id));
    }	
	
	public static void save(Course course) {
		EntityManager em = DataSource.em();
		em.getTransaction().begin();
		em.persist(course);
		em.getTransaction().commit();
	}
}
