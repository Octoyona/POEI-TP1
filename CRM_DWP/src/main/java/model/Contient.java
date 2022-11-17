package model;

public class Contient {
	private Long   id;
    private Produit produit;
    private int quantité;
    
    //Constructeurs
	public Contient(Produit produit, int quantité) {
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
		return getId() + " : " + getProduit() + " : " + getQuantité();
	}
    
  
    
}

