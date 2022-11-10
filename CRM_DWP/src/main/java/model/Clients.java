package model;

public class Clients {
	private Long   id;
    private String nom;
    private String prenom;
    private Adresses adresses;
    private String nom_societe;
    private String mail;
    private String telephone;
    private int etat;
    private int genre;
    
     //Constructeur
	public Clients() {
	
	}
	
	public Clients(String nom, String prenom, Adresses adresses, String nom_societe, String mail, String telephone,
			int etat, int genre) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresses = adresses;
		this.nom_societe = nom_societe;
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

	public Adresses getAdresses() {
		return adresses;
	}

	public void setAdresses(Adresses adresses) {
		this.adresses = adresses;
	}

	public String getNom_societe() {
		return nom_societe;
	}

	public void setNom_societe(String nom_societe) {
		this.nom_societe = nom_societe;
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
    
	@Override
	public String toString() {
		return getId() + " : " + getNom() + " " + getPrenom() + " - " + getAdresses() + " - " + getNom_societe() + " - " 
	+ getMail() + " - " + getTelephone() + " - " + getEtat() + " - " + getGenre();
	}
 
    
	
}