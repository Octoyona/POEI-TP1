package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DaoFactory;
import dao.PaiementDao;
import model.Paiement;

public class PaiementForm {

	PaiementDao paiementDao;
	Map<String, String> erreurs;
	
	public PaiementForm() {
		paiementDao = DaoFactory.getInstance().getPaiementDao();
		erreurs = new HashMap<String, String>();
	}

	public PaiementDao getPaiementDao() {
		return paiementDao;
	}

	public void setPaiementDao(PaiementDao paiementDao) {
		this.paiementDao = paiementDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}
	
	
	public boolean isValid() {
		
		if(erreurs.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public Paiement savePaiement(HttpServletRequest request) {
		
		String numero_carte = request.getParameter("numeroCarte");
		String code_confidentiel = request.getParameter("codeConfidentiel");
		String banque = request.getParameter("banque");
		
		Paiement paiement = new Paiement();
		
		//Ajout des contrôles
		if(numero_carte != null) {
			if(numero_carte.length() < 2 || numero_carte.length() > 255) {
				erreurs.put("numeroCartePaiement", "Merci d'entrer un  numéro de carte compris entre 2 et 255 caractères");
			}
		}else {
			erreurs.put("numeroCartePaiement", "Merci d'entrer un numéro de carte");
		}
		
		if(code_confidentiel != null) {
			if(code_confidentiel.length() < 2 || code_confidentiel.length() > 4) {
				erreurs.put("codeConfidentielPaiement", "Le code confidentiel doit contenir au maximum 4 chiffres");
			}
		}else {
			erreurs.put("codeConfidentielPaiement", "Merci d'entrer un code confidentiel");
		}
		
		if(banque != null) {
			if(banque.length() < 2 || banque.length() > 255) {
				erreurs.put("banquePaiement", "La banque doit contenir entre 2 et 255 caractères");
			}
		}else {
			erreurs.put("banquePaiement", "Merci d'entrer votre banque");
		}
		
		return paiement;
	}
	
	
}
