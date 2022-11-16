package model;

public class Panier {
	private Long   id;
    private Client clients;
    
    //Constructeurs
	public Panier() {
		
	}
	
	public Panier(Client clients) {
		this.clients = clients;
	}

	
	//Getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


