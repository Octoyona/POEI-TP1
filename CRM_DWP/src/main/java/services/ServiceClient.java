package services;

import com.google.gson.JsonObject;

import dao.DaoException;
import daoImpl.DaoAdresse;
import daoImpl.DaoClient;
import model.Adresse;
import model.Client;
import utils.Utils;

public class ServiceClient {

	private DaoClient daoClient;
	private DaoAdresse daoAdresse;

	public ServiceClient() {
		daoClient = new DaoClient();
		daoAdresse = new DaoAdresse();

	}

	public String trouver(long id) throws ServiceException {
		Client client;

		try {
			client = daoClient.trouver(id);
		} catch (DaoException e) {
			throw new ServiceException("Le client n'existe pas " + id);
		}
		return Utils.getSuperJson().toJson(client);
	}

	public String lister() throws ServiceException {
		return Utils.getSuperJson().toJson(daoClient.lister());
	}

	public void supprimer(Long id) throws ServiceException {
		try {
			daoClient.supprimer(id);
		} catch (DaoException e) {
			throw new ServiceException("Le client n'existe pas. Id : " + id);
		}
	}

	public void ajouter(JsonObject data) throws ServiceException {

		try {
			String nom = Utils.getStringParameter(data, "nom", false, 2, 255); 
			String prenom = Utils.getStringParameter(data, "prenom", false, 2, 255);
			String nomSociete = Utils.getStringParameter(data, "nomSociete", true, 0, 255);
			String mail = Utils.getStringParameter(data, "mail", true, 0, 255,"^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
			String telephone = Utils.getStringParameter(data, "telephone", true, 0, 20,"(0|\\\\+33|0033)[1-9][0-9]{8}");		
			String etat = Utils.getStringParameter(data, "etat", true, 0, 2, "^\\d+$");
			String genre = Utils.getStringParameter(data, "genre", true, 0, 2, "^\\d+$");
			String idAdresse = Utils.getStringParameter(data, "idAdresse", true, 0, 50, "^\\d+$");

			Adresse adresse = new Adresse();
			DaoAdresse daoAdresse = new DaoAdresse();
			if (idAdresse != null) {
				adresse = daoAdresse.trouver(Long.parseLong(idAdresse));
				//System.out.println(adresse.getRue());
				if (adresse == null) {
					throw new ServiceException("L'adresse n'existe pas. Id : " + idAdresse);
				}
				if(adresse.getClient() != null) {
					throw new ServiceException("L'adresse est déja associee au client d'id : "+adresse.getClient().getId());
				}
			}

			Client client = new Client();
			client.setNom(nom);
			client.setPrenom(prenom);
			client.setNomSociete(nomSociete);
			client.setMail(mail);
			client.setTelephone(telephone);
			client.setEtat(Integer.parseInt(etat));
			client.setGenre(Integer.parseInt(genre));
			client.setAdresse(adresse);
			//System.out.println(client.getAdresse().getRue());

			daoClient.ajouter(client);

			if(adresse != null) {
				adresse.setClient(client);
				daoAdresse.modifier(adresse);		
			}

		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}

	}

	public void update(JsonObject data) throws ServiceException {

		try {
			String id = Utils.getStringParameter(data, "id", false, 0, 50, "^\\d+$");
			String nom = Utils.getStringParameter(data, "nom", false, 2, 255);
			String prenom = Utils.getStringParameter(data, "prenom", false, 2, 255);
			String nomSociete = Utils.getStringParameter(data, "nomSociete", true, 0, 255);
			String mail = Utils.getStringParameter(data, "mail", true, 0, 255,"^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
			String telephone = Utils.getStringParameter(data, "telephone", true, 0, 20,"(0|\\\\+33|0033)[1-9][0-9]{8}");
			String etat = Utils.getStringParameter(data, "etat", true, 0, 2, "^\\d+$");
			String genre = Utils.getStringParameter(data, "genre", true, 0, 2, "^\\d+$");
			String idAdresse = Utils.getStringParameter(data, "idAdresse", true, 0, 50, "^\\d+$");

			Adresse adresse = new Adresse();
			DaoAdresse daoAdresse = new DaoAdresse();
			if (idAdresse != null) {
				adresse = daoAdresse.trouver(Long.parseLong(idAdresse));
				if (adresse == null) {
					throw new ServiceException("L'adresse n'existe pas. Id : " + idAdresse);
				}
			}

			Client client = daoClient.trouver(Long.parseLong(id));
			if (client == null) {
				throw new ServiceException("Le client n'existe pas. Id : " + id);
			}

			client.setNom(nom);
			client.setPrenom(prenom);
			client.setNomSociete(nomSociete);
			client.setMail(mail);
			client.setTelephone(telephone);
			client.setEtat(Integer.parseInt(etat));
			client.setGenre(Integer.parseInt(genre));
			client.setAdresse(adresse);

			daoClient.modifier(client); 

		} catch (NumberFormatException e) {
			throw new ServiceException("Le format du parametre idClient n'est pas bon.");
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}
}
