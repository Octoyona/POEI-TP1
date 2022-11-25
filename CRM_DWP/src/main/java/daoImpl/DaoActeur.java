package daoImpl;

import dao.DaoObject;
import models.Acteur;

public class DaoActeur extends DaoObject<Acteur>{

	public DaoActeur() {
		super(Acteur.class);
	}
	
	public void inverserNom(Acteur a) {
		
		String temp="";
		for(int i=a.getNom().length()-1;i>=0;i--) {
			temp+=a.getNom().charAt(i);
		}
		a.setNom(temp);
		factory.getEntityManager().merge(a);
	}
}
