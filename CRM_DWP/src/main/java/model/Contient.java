package model;

public class Contient {
	private Long   id;
    private Panier paniers;
    private Produit produits;
    private int quantité;
    
    //Constructeurs
	public Contient(Panier paniers, Produit produits, int quantité) {
		this.paniers = paniers;
		this.produits = produits;
		this.quantité = quantité;
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

	public Panier getPaniers() {
		return paniers;
	}

	public void setPaniers(Panier paniers) {
		this.paniers = paniers;
	}

	public Produit getProduits() {
		return produits;
	}

	public void setProduits(Produit produits) {
		this.produits = produits;
	}

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}
	
	@Override
	public String toString() {
		return getId() + " : " + getPaniers() + " : " + getProduits() + " : " + getQuantité();
	}
    
  
    
}

