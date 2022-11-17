package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DaoException;
import dao.DaoFactory;
import model.Contient;
import model.Produit;
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
       	
		String quantiteString = request.getParameter("quantite");
		ProduitForm produitForm = new ProduitForm();
		Produit produit = produitForm.saveProduit(request, ProduitForm.CREATION);
			
		long idContient;
		Contient contient = new Contient();
		int quantite=0;
		
		long idClient = Long.parseLong(request.getParameter("idClient"));
		
		if(this.choix==MODIFICATION) {
			idContient = Long.parseLong(request.getParameter("id"));
			try {
				contient = contientDao.trouver(idContient);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
		//Ajout des contrôles

		if(isNumeric(quantiteString)) {
			quantite = Integer.parseInt(quantiteString);
			if(quantite>1000000) {
				erreurs.put("quantiteProduit", "Vous êtes trop gourmand, trop grande quantité");
			} else if (quantite<0){
				erreurs.put("quantiteProduit", "Ce serait bien d'ajouter au moins 1 produit");
			}else {
				contient.setQuantite(quantite);
			}
		} else {
			erreurs.put("quantiteProduit", "La quantité doit être un nombre");
		}
		
		contient.setProduit(produit);
				
		if(isValid()) {
			try {
				if(this.choix==CREATION) this.contientDao.creer(contient,idClient);
				else if (this.choix==MODIFICATION) this.contientDao.update(contient,idClient);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
    	return contient;
    }
	
	private boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(strNum);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
}
