package daoImpl;

import dao.DaoObject;
import model.Client;

public class DaoClient extends DaoObject<Client> {

	public DaoClient() {
		super(Client.class);
	}

//	public void inverserNom(Client c) {
//
//		String temp="";
//		for(int i=c.getNom().length()-1; i>=0; i--) {
//			temp+=c.getNom().charAt(i);
//		}
//		c.setNom(temp);
//		factory.getEntityManager().merge(c);
//
//	}

}
