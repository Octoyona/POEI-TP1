package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="clients")
public class Client {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	private Long   id;
	
	@Column(nullable = false, length = 255)
    private String nom;
	
	@Column(nullable = false, length = 255)
    private String prenom;
	
	@OneToOne(mappedBy = "client", cascade=CascadeType.ALL) //il faut faire l'inverse vu qu'on a l'id adresse dans client?
    private Adresse adresse;
    
	@Column(nullable = true, name = "nom_societe") //par defaut = null a  implementer
    private String nomSociete;
	
	@Column(nullable = true) //par defaut= null a implementer
    private String mail;
	
	@Column(nullable = true) //par defaut = null a  implementer
    private String telephone;
	
	@Column(nullable = true) //par defaut =0
    private int etat=0;
	
	@Column(nullable = true) //par defaut =0
    private int genre;
    
	@OneToMany(mappedBy = "client", cascade=CascadeType.ALL) //pas sure du cascade
	private List<Paiement> paiements = new ArrayList<>();

	@OneToOne(mappedBy = "client", cascade=CascadeType.ALL) //pas sure du cascade
	private Panier panier;
	
     //Constructeur
	public Client() {

	}

	public Client(String nom, String prenom, Adresse adresse, String nom_societe, String mail, String telephone,
			int etat, int genre) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.nomSociete = nom_societe;
		this.mail = mail;
		this.telephone = telephone;
		this.etat = etat;
		this.genre = genre;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getNomSociete() {
		return nomSociete;
	}

	public void setNomSociete(String nom_societe) {
		this.nomSociete = nom_societe;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	
	public List<Paiement> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getPrenom() + " - " + getAdresse() + " - " + getNomSociete() + " - "
	+ getMail() + " - " + getTelephone() + " - " + getEtat() + " - " + getGenre();
	}



}
