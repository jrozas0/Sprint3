package beans.managers;

import beans.Material;

public class MaterialManager {

	public static Material getById(Long id){
		return DataSource.em().find(Material.class, id);
	}
	
}
