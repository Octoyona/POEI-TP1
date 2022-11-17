package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DaoException;
import dao.DaoFactory;
import model.Adresse;
import model.Client;
import dao.AdresseDao;
import dao.ClientDao;

public class ClientForm {
       
	ClientDao clientDao;
	Map<String, String> erreurs;
	int choix;
	public static final int CREATION = 0, MODIFICATION = 1;
	
    public ClientForm() {
    	clientDao = DaoFactory.getInstance().getClientDao();
    	erreurs = new HashMap<String,String>();
    }    

    public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public int getChoix() {
		return choix;
	}

	public void setChoix(int choix) {
		this.choix = choix;
	}

	public boolean isValid() {
		if(erreurs.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	

	public Client saveClient(HttpServletRequest request, int choix) {
       	
		String nom = request.getParameter("nomClient");
		String prenom = request.getParameter("prenomClient");
		String telephone = request.getParameter("telephoneClient");
		String nomsociete = request.getParameter("nomSociete");
		String mail = request.getParameter("mailClient");
		int genre = Integer.parseInt(request.getParameter("genreClient"));
		
		AdresseForm adresseForm = new AdresseForm();
		Adresse adresse = adresseForm.saveAdresse(request, AdresseForm.CREATION);
		
		long idClient;
		Client client = new Client();
		
		if(this.choix==MODIFICATION) {
			idClient = Long.parseLong(request.getParameter("id"));
			try {
				client = clientDao.trouver(idClient);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
		//Ajout des contrôles
		if(nom != null) {
			if(nom.length() < 2 || nom.length() > 20) {
				erreurs.put("nomClient", "Votre nom doit contenir entre 2 et 20 caractères.");
			}
		} else {
			erreurs.put("nomClient", "Merci d'entrer un nom.");
		}
		
		if(prenom != null) {
			if(prenom.length() > 20) {
				erreurs.put("prenomClient", "Votre prénom doit avoir maximum 20 caractères.");
			} 
		} else {
			erreurs.put("prenomClient", "Merci d'entrer un prénom.");
		}
		
		if(telephone != null) {
			if(telephone.length() > 10) {
				erreurs.put("telephoneClient", "Un numéro de téléphone doit avoir maximum 10 caractères.");
			}
			if(!telephone.matches("^\\d+$")) {
				erreurs.put("telephoneClient", "Un numéro de téléphone doit contenir uniquement des chiffres.");
			}
		} else {
			erreurs.put("telephoneClient", "Merci d'entrer un numéro de téléphone.");
		}
		
		if(nomsociete != null) {
			if(nomsociete.length() > 20) {
				erreurs.put("nomSocieteClient", "Le nom de votre société doit avoir maximum 20 caractères.");
			}
		} else {
			erreurs.put("nomSocieteClient", "Merci d'entrer un nom de société.");
		}
			
		if(mail != null) {
			if(mail.length() > 60) {
				erreurs.put("mailClient", "Un mail doit avoir maximum 60 caractères.");
			}
			if(!mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				erreurs.put("mailClient", "Merci d'entrer une adresse mail valide.");
			}
		} else {
			erreurs.put("mailClient", "Merci d'entrer un email.");
		}
		
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setTelephone(telephone);
		client.setMail(mail);
		client.setNom_societe(nomsociete);
		client.setGenre(genre);
		client.setAdresse(adresse);

		if(isValid() && adresseForm.isValid()) {
			try {
				AdresseDao adresseDao = DaoFactory.getInstance().getAdresseDao();
				
				if(this.choix==CREATION) {
					long idAdresse = adresseDao.creer(adresse);
					adresse.setId(idAdresse);
					this.clientDao.creer(client);
				}
				else if (this.choix==MODIFICATION) {
					this.clientDao.update(client);
					adresseDao.update(adresse);
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
    	return client;
    }
}
