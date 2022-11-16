package dao;

import java.util.List;

import dao.DaoException;
import model.Produit;

public interface ProduitDao {
	
	//bien verifier toutes les orthographes par rappot aux modeles, les variables etc
	void 			creer(Produit produit) throws DaoException;
	
	Produit 		trouver(long id) throws DaoException;
	
	List<Produit> 	lister() throws DaoException;
	
	void 			supprimer(long id) throws DaoException;
	
	void 			update (Produit produit) throws DaoException;
	

}
