package services;

import com.google.gson.JsonObject;

import dao.DaoException;
import daoImpl.DaoActeur;
import models.Acteur;
import utils.Utils;


public class ServiceActeur {
	protected DaoActeur daoActeur;
	
	public ServiceActeur() {
		daoActeur = new DaoActeur();
	}
	
	public String trouver(long id) throws ServiceException{
		String s = null;
		
		try {
			Acteur acteur = daoActeur.trouver(id);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return s;
	}
	
	public String lister(){
		return Utils.getSuperJson().toJson(daoActeur.lister());
	}
	
	public void ajouter(JsonObject data) throws ServiceException{
		try {
			String nom = Utils.getStringParameter(data, "nom", 2, 20);
			String prenom = Utils.getStringParameter(data, "prenom", 2, 20);
			int age = 0;
			
			if(nom !=null) {
				nom = data.get("nom").getAsString();
			} else {
				throw new ServiceException("Le champ nom est obligatoire");
			}
						

			Acteur a = new Acteur();
			a.setNom(nom);
			a.setPrenom(prenom);
			a.setAge(age);
			
			daoActeur.ajouter(a);
			
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifier(JsonObject data) throws ServiceException{
		
	}
	
	public void supprimer(long id) throws ServiceException{
		
	}
}
