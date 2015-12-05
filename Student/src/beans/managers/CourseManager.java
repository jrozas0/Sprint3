package beans.managers;

import java.util.List;


import beans.Course;


public class CourseManager {

	public static List<Course> getCourses(){
		return DataSource.em().createNamedQuery("Course.findAll").getResultList();
	}
	
	
}
