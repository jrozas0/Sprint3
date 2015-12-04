package beans.managers;

import java.util.List;

import beans.Category;

public class CategoryManager {

	
	public static List<Category> getCategories(){
		return DataSource.em().createNamedQuery("Categorys.findAll").getResultList();
	}
	
	
	
	
}
