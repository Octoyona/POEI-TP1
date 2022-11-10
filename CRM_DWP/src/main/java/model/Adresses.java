package model;

public class Adresses {
	private Long   id;
    private String rue;
    private String ville;
    private String code_postal;
    private String pays;
    
  //Constructeur
    
	public Adresses() {
		
	}
	public Adresses(String rue, String ville, String code_postal, String pays) {
		this.rue = rue;
		this.ville = ville;
		this.code_postal = code_postal;
		this.pays = pays;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	@Override
	public String toString() {
		return getId() + " : " + getRue() + " " + getVille() + " - " + getCode_postal() + " - " + getPays();
	}
	
	
    
	
    
}