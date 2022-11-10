package model;

public class Paniers {
	private Long   id;
    private Clients clients;
    
    //Constructeurs
	public Paniers() {
		
	}
	
	public Paniers(Clients clients) {
		this.clients = clients;
	}

	
	//Getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clients getClients() {
		return clients;
	}

	public void setClients(Clients clients) {
		this.clients = clients;
	}
  
	@Override
	public String toString() {
		return getId() + " : " + getClients();
	}
	
    
}


