package services;

import com.google.gson.JsonObject;

import dao.DaoException;
import daoImpl.DaoContient;
import daoImpl.DaoPanier;
import daoImpl.DaoProduit;
import model.Contient;
import model.Panier;
import model.Produit;
import utils.Utils;

public class ServiceContient {
	protected DaoContient daoContient;
	
	public ServiceContient() {
		daoContient = new DaoContient();
	}
	
	public String trouver(long id) throws ServiceException{
		Contient contient;
		try {
			contient = daoContient.trouver(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return Utils.getSuperJson().toJson(contient);
	}
	
	public String lister(){
		return Utils.getSuperJson().toJson(daoContient.lister());
	}
	
	public void ajouter(JsonObject data) throws ServiceException{
		try {
			String idProduit = Utils.getStringParameter(data, "idProduit", false, 0, 50, "^\\d+$");
			String idPanier = Utils.getStringParameter(data, "idClient", false, 0, 50, "^\\d+$");
			Contient contient = new Contient();
			
			DaoProduit daoProduit = new DaoProduit();
			Produit produit = daoProduit.trouver(Long.parseLong(idProduit));
			contient.setProduit(produit);
			
			DaoPanier daoPanier = new DaoPanier();
			Panier panier = daoPanier.trouver(Long.parseLong(idPanier));
			contient.setPanier(panier);
			
			
			daoContient.ajouter(contient);
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifier(JsonObject data) throws ServiceException{
		try {
			String id = Utils.getStringParameter(data, "id", false, 0, 50, "^\\d+$");
			String idProduit = Utils.getStringParameter(data, "idProduit", false, 0, 50, "^\\d+$");
			String idPanier = Utils.getStringParameter(data, "idClient", false, 0, 50, "^\\d+$");

			Contient contient = daoContient.trouver(Long.parseLong(id));
			
			DaoProduit daoProduit = new DaoProduit();
			Produit produit = daoProduit.trouver(Long.parseLong(idProduit));
			contient.setProduit(produit);
			
			DaoPanier daoPanier = new DaoPanier();
			Panier panier = daoPanier.trouver(Long.parseLong(idPanier));
			contient.setPanier(panier);
			
			daoContient.modifier(contient);
			
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre id n'est pas bon.");
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void supprimer(long id) throws ServiceException{
		try {
			daoContient.supprimer(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
