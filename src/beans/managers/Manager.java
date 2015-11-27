package beans.managers;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Manager {

	private static Manager self;
	
	private EntityManager em;
	
	public static Manager factory() {
		if (self != null) self = new Manager();
		return self;
	}
		
	public Manager() {
		this.em = Persistence.createEntityManagerFactory("Dokku").createEntityManager();
	}
	
	public EntityManager em() {
		return em;
	}
	
}
