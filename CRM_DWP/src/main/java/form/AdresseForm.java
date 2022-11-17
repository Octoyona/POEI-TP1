package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DaoException;
import dao.DaoFactory;
import model.Adresse;
import dao.AdresseDao;

import java.util.regex.*;

public class AdresseForm {
       
	AdresseDao adresseDao;
	Map<String, String> erreurs;
	int choix;
	public static final int CREATION = 0, MODIFICATION = 1;

    public AdresseForm() {
    	adresseDao = DaoFactory.getInstance().getAdresseDao();
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

	public Adresse saveAdresse(HttpServletRequest request, int choix) {
       		
		String rue = request.getParameter("rueAdresse");
		String codePostal = request.getParameter("codePostalAdresse");
		String ville = request.getParameter("villeAdresse");
		String pays = request.getParameter("paysAdresse");

		long idAdresse;
		Adresse adresse = new Adresse();
			
		if(this.choix==MODIFICATION) {
			idAdresse = Long.parseLong(request.getParameter("id"));
			try {
				adresse = this.adresseDao.trouver(idAdresse);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
		//Ajout des contrôles
		if(rue != null) {
			if(rue.length() < 2) {
				erreurs.put("rueAdresse", "Adresse trop courte");
			}
		}
		
		if(codePostal != null) {
			if(Pattern.matches("/[0-9]{5}/g", codePostal)) {
				erreurs.put("codePostalAdresse", "Code Postal invalide");
			}
		}
		adresse.setRue(rue);
		adresse.setCode_postal(codePostal);
		adresse.setVille(ville);
		adresse.setPays(pays);
		
    	return adresse;
    }
}
