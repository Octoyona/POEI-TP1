package dao;

import java.util.List;

import model.Paiement;

public interface PaiementDao {

	void         creer( Paiement paiement ) throws DaoException;

    Paiement       trouver( long id ) throws DaoException;

    List<Paiement> lister() throws DaoException;

    void         supprimer( long id ) throws DaoException;

    void         update( Paiement paiement ) throws DaoException;
}
