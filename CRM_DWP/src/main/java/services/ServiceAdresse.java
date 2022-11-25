package services;

import com.google.gson.JsonObject;
import dao.DaoException;
import daoImpl.DaoAdresse;
import model.Adresse;
import utils.Utils;


public class ServiceAdresse {
	protected DaoAdresse daoAdresse;
	
	public ServiceAdresse() {
		daoAdresse = new DaoAdresse();
	}
	
	public String trouver(long id) throws ServiceException{
		Adresse adresse;
		try {
			adresse = daoAdresse.trouver(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return Utils.getSuperJson().toJson(adresse);
	}
	
	public String lister(){
		return Utils.getSuperJson().toJson(daoAdresse.lister());
	}
	
	public void ajouter(JsonObject data) throws ServiceException{
		try {
			String rue = Utils.getStringParameter(data, "rue", false, 2, 20);
			String ville = Utils.getStringParameter(data, "ville", false,  2, 20);
			String codePostal = Utils.getStringParameter(data, "code_postal", false, 2, 20,"/[0-9]{5}/g");
			String pays = Utils.getStringParameter(data, "pays", false, 2, 20);
		
			
			if(rue !=null) {
				rue = data.get("rue").getAsString();
			} else {
				throw new ServiceException("Le champ rue est obligatoire");
			}
			
			if(codePostal !=null) {
				codePostal = data.get("code_postal").getAsString();
			} else {
				throw new ServiceException("Le champ code postal est obligatoire");
			}
						

			Adresse a = new Adresse();
			a.setRue(rue);
			a.setVille(ville);
			a.setCode_postal(codePostal);
			a.setPays(pays);
			
			daoAdresse.ajouter(a);
			
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifier(JsonObject data) throws ServiceException{
		try {		
			String id = Utils.getStringParameter(data, "id", false, 0, 50, "^\\d+$");
			String rue = Utils.getStringParameter(data, "rue", false,  2, 20);
			String ville = Utils.getStringParameter(data, "ville", false, 2, 20);
			String codePostal = Utils.getStringParameter(data, "code_postal", false,  2, 20,"/[0-9]{5}/g");
			String pays = Utils.getStringParameter(data, "pays", false, 2, 20);
			
			//Création de l'auteur
			Adresse adresse = daoAdresse.trouver(Long.parseLong(id));
			adresse.setRue(rue);
			adresse.setVille(ville);
			adresse.setCode_postal(codePostal);
			adresse.setPays(pays);
	
			//Sauvegarde de l'auteur
			daoAdresse.modifier(adresse);
		} catch(NumberFormatException e) {
			throw new ServiceException("Le format du paramètre id n'est pas bon.");
		} catch(DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void supprimer(long id) throws ServiceException{
		try {
			daoAdresse.supprimer(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
