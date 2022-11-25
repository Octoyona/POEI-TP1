package daoImpl;

import model.Client;

import dao.DaoObject;

public class DaoClient extends DaoObject<Client> {

	public DaoClient() {
		super(Client.class);
	}
	
	public void inverserNom(Client c) { //à vérifier
		
		String temp="";
		for(int i=c.getNom().length()-1; i>=0; i--) {
			temp+=c.getNom().charAt(i);
		}
		c.setNom(temp);
		factory.getEntityManager().merge(c);
		
	}
	
}
