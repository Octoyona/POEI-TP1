package dao;

import java.util.List;

import model.Panier;

public interface PanierDao {
	
	void         creer( Panier panier ) throws DaoException;

    Panier       trouver( long id ) throws DaoException;
    
    Panier       trouverClient( long id ) throws DaoException;

    List<Panier> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void         update( Panier panier ) throws DaoException;
	
	
}
