package beans.managers;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager<T> {

	private static Manager self;
	
	private EntityManagerFactory factory;
	
	public static <T> Manager<T> factory() {
		if (self != null) self = new Manager<T>();
		return self;
	}
		
	public Manager() {
		this.factory = Persistence.createEntityManagerFactory("Dokku");
	}
	
	public EntityManager em() {
		return factory.createEntityManager();
	}
	
    public Optional<T> get(int id, Class<T> clazz) {
        return Optional.ofNullable(em().find(clazz, id));
    }

	
}
