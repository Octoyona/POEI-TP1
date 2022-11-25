package services;

import org.hibernate.service.spi.ServiceException;

import com.google.gson.JsonObject;

import dao.DaoException;
import daoImpl.DaoPaiement;
import model.Client;
import model.Paiement;
import utils.Utils;
import daoImpl.DaoClient;


public class ServicePaiement {
	private DaoPaiement daoPaiement;
	private DaoClient daoClient;
	
	public ServicePaiement() {
		daoPaiement = new DaoPaiement();
		daoClient = new DaoClient();
	}
	
	public String trouver(long id) throws ServiceException{
		Paiement paiement;
		
		try {
			paiement = daoPaiement.trouver(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return Utils.getSuperJson().toJson(paiement);
	}
	
	public String lister(){
		return Utils.getSuperJson().toJson(daoPaiement.lister());
	}
	
	public void ajouter(JsonObject data) throws ServiceException{
		Client client = null; String numeroCarte = null; String codeConfidentiel = null; String banque = null;
		String idClient = null; 
		
		try {
			numeroCarte = Utils.getStringParameter(data, "numeroCarte", false, 16, 16, "^\\d+$");
			codeConfidentiel = Utils.getStringParameter(data, "codeConfidentiel", false, 4, 4,"/[0-9]{4}/g");
			banque = Utils.getStringParameter(data, "codeConfidentiel",false, 2, 20);
			idClient  = Utils.getStringParameter(data, "idClient", false, 0, 50, "^\\d+$");
			
			if(numeroCarte !=null) {
				numeroCarte = data.get("numeroCarte").getAsString();
			} else {
				throw new ServiceException("Le numéro de carte est obligatoire");
			}
			
			if(codeConfidentiel !=null) {
				codeConfidentiel = data.get("codeConfidentiel").getAsString();
			} else {
				throw new ServiceException("Le code confidentiel est obligatoire");
			}
			
			if(banque !=null) {
				banque = data.get("Banque").getAsString();
			} else {
				throw new ServiceException("La banque est obligatoire");
			}
			
			if(idClient != null) {
				client = daoClient.trouver(Long.parseLong(idClient));
				if(client == null)
					throw new ServiceException("Le client n'existe pas. Id : "+idClient);
			}

			Paiement p = new Paiement();
			p.setCodeConfidentiel(codeConfidentiel);
			p.setNumeroCarte(numeroCarte);
			p.setBanque(banque);
			p.setClient(client);
			
			daoPaiement.ajouter(p);
			
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}
	
	
	
	public void modifier(JsonObject data) throws ServiceException{
		Client client = null; String numeroCarte = null; String codeConfidentiel = null; String banque = null;
		String idClient = null; String id =null;
		
		try {
			id = Utils.getStringParameter(data, "id", false, 0, 50, "^\\d+$");
			numeroCarte = Utils.getStringParameter(data, "numeroCarte", false, 16, 16, "^\\d+$");
			codeConfidentiel = Utils.getStringParameter(data, "codeConfidentiel", false, 4, 4,"/[0-9]{4}/g");
			banque = Utils.getStringParameter(data, "codeConfidentiel",false, 2, 20);
			idClient  = Utils.getStringParameter(data, "idClient", false, 0, 50, "^\\d+$");
			
			if(numeroCarte !=null) {
				numeroCarte = data.get("numeroCarte").getAsString();
			} else {
				throw new ServiceException("Le numéro de carte est obligatoire");
			}
			
			if(codeConfidentiel !=null) {
				codeConfidentiel = data.get("codeConfidentiel").getAsString();
			} else {
				throw new ServiceException("Le code confidentiel est obligatoire");
			}
			
			if(banque !=null) {
				banque = data.get("Banque").getAsString();
			} else {
				throw new ServiceException("La banque est obligatoire");
			}
			
			if(idClient != null) {
				client = daoClient.trouver(Long.parseLong(idClient));
				if(client == null)
					throw new ServiceException("Le client n'existe pas. Id : "+idClient);
			}
			
			
			Paiement p = daoPaiement.trouver(Long.parseLong(id));
			
			if(p == null)
				throw new ServiceException("Le paiement n'existe pas. Id : "+id);
			
			p.setCodeConfidentiel(codeConfidentiel);
			p.setNumeroCarte(numeroCarte);
			p.setBanque(banque);
			p.setClient(client);
			
			daoPaiement.modifier(p);
			
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre idPaiement n'est pas bon.");
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}
	

	public void supprimer(long id) throws ServiceException{
		try {
			daoPaiement.supprimer(id);
		} catch (DaoException e) {
			throw new ServiceException("Le paiement n'existe pas. Id : "+id);
		}
	}
	
	
}
