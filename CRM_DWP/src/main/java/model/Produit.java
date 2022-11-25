package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long   id;
	
	@Column(nullable = false, length = 20)
    private String nom;
    
	@Column(length = 200)
    private String description;
    
	@Column()
    private Float prix;
	
	@OneToMany(mappedBy = "produit")
	private List<Contient> contients = new ArrayList<Contient>();
    
       //Constructeur 
	public Produit() {
	
	}
	
	public Produit(String nom, String description, Float prix) {
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

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getDescription() + " - " + getPrix();
	}

}
