package beans.managers;

import java.util.List;

import beans.Category;

public class CategoryManager {

	public static Category getById(Long id){
		return DataSource.em().find(Category.class, id);
	}
	
	public static List<Category> getAll() {
		return DataSource.em().createNamedQuery("Category.findAll").getResultList();
	}
	
}
