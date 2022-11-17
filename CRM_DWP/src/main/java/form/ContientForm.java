package form;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import dao.DaoException;
import dao.DaoFactory;
import model.Adresse;
import model.Client;
import model.Contient;
import model.Produit;
import dao.ClientDao;
import dao.ContientDao;

public class ContientForm {
       
	ContientDao contientDao;
	Map<String, String> erreurs;
	int choix;
	public static final int CREATION = 0, MODIFICATION = 1;
	
    public ContientForm() {
    	contientDao = DaoFactory.getInstance().getContientDao();
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
			return false;
		} else {
			return true;
		}
	}
	

	public Contient saveContient(HttpServletRequest request, int choix) {
       	
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		ProduitForm produitForm = new ProduitForm();
		Produit produit = produitForm.saveProduit(request, ProduitForm.CREATION);
		
		long idContient;
		Contient contient = new Contient();
		
		if(this.choix==MODIFICATION) {
			idContient = Long.parseLong(request.getParameter("id"));
			try {
				contient = contientDao.trouver(idContient);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
		//Ajout des contrôles

		if(quantite != null) {
			if(Pattern.matches("/[0-9]/g", quantite)) {
				erreurs.put("quantiteProduit", "Quantité invalide");
			}
		}
		
		contient.setQuantite(quantite);
		contient.setProduit(produit);
				
		if(isValid()) {
			try {
				if(this.choix==CREATION) this.contientDao.creer(contient);
				else if (this.choix==MODIFICATION) this.contientDao.update(contient);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
    	return contient;
    }
}
