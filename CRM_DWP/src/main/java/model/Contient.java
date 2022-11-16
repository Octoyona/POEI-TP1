package model;

public class Contient {
	private Long   id;
    private Panier panier;
    private Produit produit;
    private int quantité;
    
    //Constructeurs
	public Contient(Panier panier, Produit produit, int quantité) {
		this.panier = panier;
		this.produit = produit;
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

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantité() {
		return quantité;
	}

	public void setQuantité(int quantité) {
		this.quantité = quantité;
	}
	
	@Override
	public String toString() {
		return getId() + " : " + getPanier() + " : " + getProduit() + " : " + getQuantité();
	}
    
  
    
}

