package dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory{
	private static DaoFactory instanceSingleton = null;
	private static EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;
	
	private DaoFactory() {
	}
	
	public static DaoFactory getInstance() {
		if(DaoFactory.instanceSingleton == null) {
			DaoFactory.instanceSingleton = new DaoFactory();
			DaoFactory.entityManagerFactory = Persistence.createEntityManagerFactory("crm");
		}
		return DaoFactory.instanceSingleton;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		if(this.entityManager==null || !this.entityManager.isOpen()) {
			this.entityManager = entityManagerFactory.createEntityManager();
		}
		return this.entityManager;
	}
	
	public void releaseEntityManager() {
		if(this.entityManager !=null && this.entityManager.isOpen()) {
			this.entityManager.close();
		}
	}
}
