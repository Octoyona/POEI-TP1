package form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.DaoException;
import dao.DaoFactory;
import dao.ProduitDao;
import model.Produit;

public class ProduitForm {
       
	ProduitDao produitDao;
	Map<String, String> erreurs;
	int choix;
	public static final int CREATION = 0, MODIFICATION = 1;

    public ProduitForm() {
    	produitDao = DaoFactory.getInstance().getProduitDao();
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
	

	public Produit saveAdresse(HttpServletRequest request, int choix) {
       		
		String nom = request.getParameter("nomProduit");
		String description = request.getParameter("descriptionProduit");
		Float prix = Float.parseFloat(request.getParameter("prix"));


		long idProduit;
		Produit produit = new Produit();
		
		if(this.choix==MODIFICATION) {
			idProduit = Long.parseLong(request.getParameter("id"));
			try {
				produit = produitDao.trouver(idProduit);
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
		
		if(description != null) {
			if(description.length() < 10 || description.length() > 450) {
				erreurs.put("descriptionProduit", "Votre description doit contenir entre 10 et 450 caractères.");
			}
		} else {
			erreurs.put("descriptionProduit", "Merci d'entrer une description.");
		}
		
		produit.setNom(nom);
		produit.setDescription(description);
		produit.setPrix(prix);
			
		if(isValid()) {
			try {
				if(this.choix==CREATION) this.produitDao.creer(produit);
				else if (this.choix==MODIFICATION) this.produitDao.update(produit);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
    	return produit;
    }
}
