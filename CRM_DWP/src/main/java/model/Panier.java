package model;
import java.util.ArrayList;
import java.util.List;

public class Panier {
	private Long   id;
    private Client clients;
    private List<Contient> contients;
    
    //Constructeurs
	public Panier() {
		
	}
	
	public Panier(Client clients) {
		this.clients = clients;
		this.contients = new ArrayList<Contient>();
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
  
	@Override
	public String toString() {
		return getId() + " : " + getClients();
	}
	
    
}


