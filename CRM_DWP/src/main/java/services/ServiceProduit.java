package services;

import com.google.gson.JsonObject;

import dao.DaoException;
import daoImpl.DaoProduit;
import model.Produit;
import utils.Utils;


public class ServiceProduit {
	protected DaoProduit daoProduit;
	
	public ServiceProduit() {
		daoProduit = new DaoProduit();
	}
	
	public String trouver(long id) throws ServiceException{
		Produit produit;
		try {
			produit = daoProduit.trouver(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return Utils.getSuperJson().toJson(produit);
	}
	
	public String lister(){
		return Utils.getSuperJson().toJson(daoProduit.lister());
	}
	
	public void ajouter(JsonObject data) throws ServiceException{
		try {
			String nom = Utils.getStringParameter(data, "nom", false, 2, 20);
			String description = Utils.getStringParameter(data, "description", true, 0, 20);
			String prixString = Utils.getStringParameter(data, "prix", false, 1, 255, "^\\d+$");

			Produit produit = new Produit();
			produit.setNom(nom);
			produit.setDescription(description);
			produit.setPrix(Float.parseFloat(prixString));
			
			daoProduit.ajouter(produit);
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifier(JsonObject data) throws ServiceException{
		try {
			String id = Utils.getStringParameter(data, "id", false, 0, 50, "^\\d+$");
			String nom = Utils.getStringParameter(data, "nom", false, 2, 20);
			String description = Utils.getStringParameter(data, "description", true, 0, 20);
			String prixString = Utils.getStringParameter(data, "prix", false, 1, 255, "^\\d+$");

			Produit produit = daoProduit.trouver(Long.parseLong(id));
			produit.setNom(nom);
			produit.setDescription(description);
			produit.setPrix(Float.parseFloat(prixString));

			//Sauvegarde de l'auteur
			daoProduit.modifier(produit);
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre id n'est pas bon.");
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void supprimer(long id) throws ServiceException{
		try {
			daoProduit.supprimer(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
