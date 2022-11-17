package model;
import java.util.ArrayList;
import java.util.List;

public class Panier {
	private Long   id;
    private Client clients;
    private List<Contient> contients;
    private float prixTotal;
    
    //Constructeurs
	public Panier() {
		
	}
	
	public Panier(Client clients) {
		this.clients = clients;
		this.contients = new ArrayList<Contient>();
		this.prixTotal = 0;
	}
	
	//Getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Contient> getContients() {
		return contients;
	}

	public void setContients(List<Contient> contients) {
		this.contients = contients;
	}

	public Client getClients() {
		return clients;
	}

	public void setClients(Client clients) {
		this.clients = clients;
	}

	public float getPrixTotal() {
		for(Contient contient : contients) {
			this.prixTotal += contient.getPrixTotal();
		}
		return this.prixTotal;
	}

	@Override
	public String toString() {
		return getId() + " : " + getClients();
	}
	
    
}


