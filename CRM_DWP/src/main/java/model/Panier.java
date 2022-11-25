package model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "panier")
public class Panier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	@ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private Client client;
    
    @OneToMany(mappedBy = "panier")
	private List<Contient> contients = new ArrayList<Contient>();
    
    //Constructeurs
	public Panier() {
		
	}
	
	public Panier(Client client) {
		this.client = client;
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
	
	public void addContient(Contient c) {
		this.contients.add(c);
	}
	
	public void removeContient(Contient c) {
		this.contients.remove(c);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public float getPrixTotal() {
		float prixTotal=0;
		for(Contient contient : contients) {
			prixTotal += contient.getPrixTotal();
		}
		return prixTotal;
	}

	@Override
	public String toString() {
		return getId() + " : " + getClient();
	}
	
    
}


