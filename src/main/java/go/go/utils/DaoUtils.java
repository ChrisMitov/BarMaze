package go.go.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoUtils {
	private static final String BAR_MAZETO_PERSISTENT_UNIT = "go";
	private static final EntityManagerFactory emfactory = Persistence.createEntityManagerFactory(BAR_MAZETO_PERSISTENT_UNIT);

	public static EntityManager getEmfactory() {
		return emfactory.createEntityManager();
	}
}
