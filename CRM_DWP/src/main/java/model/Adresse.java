package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;

	
 @Entity
 @Table(name= "adresse")
 public class Adresse {
	 	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long   id;
	 
	 @Column(nullable = false)
	 private String rue;
	 
	 @Column
	 private String ville;
	 
	 @Column(nullable = false)
	 private String code_postal;
	 
	 @Column
	 private String pays;
	     
	 @OneToOne( mappedBy="adresse", fetch=FetchType.LAZY )
		private Client client;
	 
	 @PreRemove
		private void preRemove() {
		    if(this.client != null) {
		    	this.client.setAdresse(null);
		    }
		}
	 
	 public Adresse() {
	
	}
	 
	 public Adresse(String rue, String ville, String code_postal, String pays) {
		 this.rue = rue;
		 this.ville = ville;
		 this.code_postal = code_postal;
		 this.pays = pays;
	 }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public boolean equals(Object obj) {
		if(((Adresse) obj).getId() != this.id) {
			return false;
		}

		return true;
	}
	
 }
	
	
	
    
	
    
