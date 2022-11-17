package dao;

import java.util.List;

import model.Adresse;

public interface AdresseDao {
	
	long         creer( Adresse adresse ) throws DaoException;

	Adresse       trouver( long id ) throws DaoException;

    List<Adresse> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void         update( Adresse adresse ) throws DaoException;
}
