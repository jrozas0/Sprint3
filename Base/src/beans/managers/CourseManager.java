package beans.managers;

import java.util.List;
import java.util.Optional;

import beans.Course;
import beans.User;


public class CourseManager {

	public static List<Course> getCourses() {
		return DataSource.em().createNamedQuery("Course.findAll").getResultList();
	}
	
    public static Optional<Course> getById(int id) {
        return Optional.ofNullable(DataSource.em().find(Course.class, id));
    }	
	
}
