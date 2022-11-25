package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paiements")
public class Paiement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	@Column(nullable = false, length = 16)
    private String numeroCarte;
	
	@Column(nullable = false, length = 4)
    private String codeConfidentiel;
	
	@Column(nullable = false, length = 20)
    private String banque;
	
	@ManyToOne( fetch=FetchType.LAZY )
    private Client client;
    
  //Constructeur
    
    
    public Paiement() {  
    	
    }
    

    //Getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public String getCodeConfidentiel() {
		return codeConfidentiel;
	}

	public void setCodeConfidentiel(String codeConfidentiel) {
		this.codeConfidentiel = codeConfidentiel;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	@Override
	public String toString() {
		return getId() + " : " + getNumeroCarte() + " " + getCodeConfidentiel() + " - " + getBanque() + " - " + getClient().getNom() + " " + getClient().getPrenom();
	}
    
    
}

