package model;

public class Contient {
	private Long   id;
    private Produit produit;
    private int quantite;
    
    //Constructeurs
	public Contient(Produit produit, int quantite) {
		this.produit = produit;
		this.quantite = quantite;
	}
	
	public Contient() {		
	}

	//Getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	@Override
	public String toString() {
		return getId() + " : " + getProduit() + " : " + getQuantite();
	}
    
  
    
}

