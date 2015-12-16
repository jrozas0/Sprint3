package beans.managers;

import java.util.List;

import beans.Category;
import beans.Lesson;

public class LessonManager {

	public static Lesson getById(Long id){
		return DataSource.em().find(Lesson.class, id);
	}
	
	public static List<Category> getAll() {
		return DataSource.em().createNamedQuery("Category.findAll").getResultList();
	}
	
}