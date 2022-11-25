package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="contient")
public class Contient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	@Column()
    private int quantite = 1;
        
    @ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private Panier panier;
    
    @ManyToOne( fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private Produit produit;
    
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
	
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
		
	public float getPrixTotal() {
		return this.produit.getPrix()*this.quantite;
	}

	@Override
	public String toString() {
		return getId() + " : " + getProduit() + " : x" + getQuantite();
	}
}

