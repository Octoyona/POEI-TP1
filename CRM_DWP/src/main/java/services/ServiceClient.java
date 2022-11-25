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
			daoClient.supprimer(id); // pb avec long pour l'instant
		} catch (DaoException e) {
			throw new ServiceException("Le client n'existe pas. Id : " + id);
		}
	}

	// creer ou ajouter ?
	public void ajouter(JsonObject data) throws ServiceException {
		String nom = null, prenom = null, nomSociete = null, mail = null, telephone = null, etat = null, genre = null,
				idAdresse = null;
		Adresse adresse = null;

		try {
			// il me reste � ajouter les regex pour mail et telephone
			nom = Utils.getStringParameter(data, "nom", false, 2, 255); // ajotuer dans Utils avec le boolean isNullable
			prenom = Utils.getStringParameter(data, "prenom", false, 2, 255);
			nomSociete = Utils.getStringParameter(data, "nomSociete", true, 0, 255);
			mail = Utils.getStringParameter(data, "mail", true, 0, 255);
			telephone = Utils.getStringParameter(data, "telephone", true, 0, 2);
			etat = Utils.getStringParameter(data, "etat", true, 0, 2, "^\\d+$");
			genre = Utils.getStringParameter(data, "genre", true, 0, 2, "^\\d+$");
			idAdresse = Utils.getStringParameter(data, "idAdresse", true, 0, 50, "^\\d+$");

			if (idAdresse != null) {
				adresse = daoAdresse.trouver(Long.parseLong(idAdresse));
				if (adresse == null) {
					throw new ServiceException("L'adresse n'existe pas. Id : " + idAdresse);
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

			daoClient.ajouter(client);

		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}

	}

	public void update(JsonObject data) throws ServiceException {
		String id = null, nom = null, prenom = null, nomSociete = null, mail = null, telephone = null, etat = null,
				genre = null, idAdresse = null;
		Adresse adresse = null;

		try {
			id = Utils.getStringParameter(data, "idClient", false, 0, 50, "^\\d+$");
			nom = Utils.getStringParameter(data, "nom", false, 2, 255); // ajotuer dans Utils avec le boolean isNullable
			prenom = Utils.getStringParameter(data, "prenom", false, 2, 255);
			nomSociete = Utils.getStringParameter(data, "nomSociete", true, 0, 255);
			mail = Utils.getStringParameter(data, "mail", true, 0, 255,"^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
			telephone = Utils.getStringParameter(data, "telephone", true, 0, 2,"(0|\\\\+33|0033)[1-9][0-9]{8}");
			etat = Utils.getStringParameter(data, "etat", true, 0, 2, "^\\d+$");
			genre = Utils.getStringParameter(data, "genre", true, 0, 2, "^\\d+$");
			idAdresse = Utils.getStringParameter(data, "idAdresse", true, 0, 50, "^\\d+$");

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

			daoClient.modifier(client); // ne trouve pas update
			
		} catch (NumberFormatException e) {
			throw new ServiceException("Le format du parametre idClient n'est pas bon.");
		} catch (DaoException e) {
			throw new ServiceException("Erreur DAO.");
		}
	}
}
