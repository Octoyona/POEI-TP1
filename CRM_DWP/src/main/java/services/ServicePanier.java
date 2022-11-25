package services;

import com.google.gson.JsonObject;

import dao.DaoException;
import daoImpl.DaoClient;
import daoImpl.DaoPanier;
import model.Client;
import model.Panier;
import utils.Utils;


public class ServicePanier {
	protected DaoPanier daoPanier;
	
	public ServicePanier() {
		daoPanier = new DaoPanier();
	}
	
	public String trouver(long id) throws ServiceException{
		Panier panier;
		try {
			panier = daoPanier.trouver(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return Utils.getSuperJson().toJson(panier);
	}
	
	public String lister(){
		return Utils.getSuperJson().toJson(daoPanier.lister());
	}
	
	public void ajouter(JsonObject data) throws ServiceException{
		try {
			String idClient = Utils.getStringParameter(data, "idClient", false, 0, 50, "^\\d+$");
			Panier panier = new Panier();
			
			DaoClient daoClient = new DaoClient();
			Client client = daoClient.trouver(Long.parseLong(idClient));
			panier.setClient(client);
			
			daoPanier.ajouter(panier);
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifier(JsonObject data) throws ServiceException{
		try {
			String id = Utils.getStringParameter(data, "id", false, 0, 50, "^\\d+$");
			String idClient = Utils.getStringParameter(data, "idClient", false, 0, 50, "^\\d+$");

			Panier panier = daoPanier.trouver(Long.parseLong(id));
			
			DaoClient daoClient = new DaoClient();
			Client client = daoClient.trouver(Long.parseLong(idClient));
			panier.setClient(client);
			
			daoPanier.modifier(panier);
			
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre id n'est pas bon.");
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void supprimer(long id) throws ServiceException{
		try {
			daoPanier.supprimer(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
