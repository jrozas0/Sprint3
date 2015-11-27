package beans.managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataSource {
	
	private static EntityManagerFactory factory;
			
	static {
		factory = Persistence.createEntityManagerFactory("Dokku");
	}
	
	public static EntityManager em() {
		return factory.createEntityManager();
	}
	
}
