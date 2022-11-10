package model;

public class Produits {
	private Long   id;
    private String nom;
    private String description;
    private Float prix;
    
       //Constructeur 
	public Produits() {
	
	}
	
	public Produits(String nom, String description, Float prix) {
		this.nom = nom;
		this.description = description;
		this.prix = prix;
	}

	
	//Getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getDescription() + " - " + getPrix();
	}
   
    
    
}
