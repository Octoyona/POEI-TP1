package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DaoFactory;
import dao.PaiementDao;

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
		
		//A faire une fois qu'on est d'accord sur le paiementPanier servlet
		
		return paiement;
	}
	
	
}
