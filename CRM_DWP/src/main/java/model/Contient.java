package model;

public class Contient {
	private Long   id;
    private Paniers paniers;
    private Produits produits;
    private int quantité;
    
    //Constructeurs
	public Contient(Paniers paniers, Produits produits, int quantité) {
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

	public Paniers getPaniers() {
		return paniers;
	}

	public void setPaniers(Paniers paniers) {
		this.paniers = paniers;
	}

	public Produits getProduits() {
		return produits;
	}

	public void setProduits(Produits produits) {
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

