package dao;

import java.util.List;

import model.Contient;

public interface ContientDao {

	void         	creer( Contient contient, long idPanier) throws DaoException;

    Contient       	trouver( long id ) throws DaoException;

    List<Contient> 	lister() throws DaoException;

    void         	supprimer( long id ) throws DaoException;

    void         	update( Contient contient, long idPanier) throws DaoException;
	
}
