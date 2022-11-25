package dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


public abstract class DaoObject<T> {
	protected DaoFactory factory;
	private Class<T> className;
	
	public DaoObject(Class<T> className) {
		this.factory = DaoFactory.getInstance();
		this.className = className;
	}
	
	public T trouver(long id) throws DaoException{
		
		T object=null;
		try {
			object = factory.getEntityManager().find(className, id);
		} catch (NoResultException e) {
			e.printStackTrace();
			throw new DaoException("Erreur Dao");
		} finally {
			factory.releaseEntityManager();
		}
		
		return object;
	}
	
	public List<T> lister(){
		TypedQuery<T> query = factory.getEntityManager().createQuery("From "+className.getName(), className);
		List<T> objects = query.getResultList();
		return objects;
	}
	
	public void supprimer(T object) throws DaoException{
		EntityTransaction entityTransaction = null;

		entityTransaction = factory.getEntityManager().getTransaction();
		entityTransaction.begin();
		
		factory.getEntityManager().remove(object);
		
		entityTransaction.commit();
	}
	
	public void ajouter(T object) throws DaoException{
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = factory.getEntityManager().getTransaction();
			entityTransaction.begin();
			
			factory.getEntityManager().persist(object);
			
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
	}
	
	public void modifier(T object) throws DaoException{
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = factory.getEntityManager().getTransaction();
			entityTransaction.begin();
			
			factory.getEntityManager().merge(object);
			
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityTransaction.rollback();
		}
	}
}
