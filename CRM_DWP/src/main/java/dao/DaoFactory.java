package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

	private String url;
	private String username;
	private String passwd;
	private Connection con = null;
	
	//Pourquoi static ?
	private static DaoFactory instanceSingleton = null;
	
	//constructeur privé
	private DaoFactory(String url, String username, String passwd) {
		this.url = url;
		this.username = username;
		this.passwd = passwd;
	}
	
	public static DaoFactory getInstance() {
		if(DaoFactory.instanceSingleton == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//Faire attention si on n'a pas les mêmes logins  +  nom de la bdd
				DaoFactory.instanceSingleton = new DaoFactory("jdbc:mysql://localhost/bdd_crm", "root", "");
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return DaoFactory.instanceSingleton;
	}
	
//	//Faire les différents "getter"  
//	public XxxDao getXxxDao() {
//		return new XxxDaoImpl(this);
	
	public PanierDao getPanierDao() {
		return new PanierDaoImpl( this );
	}

	public ProduitDao getProduitDao() {
		return new ProduitDaoImpl( this );
	}

	public ClientDao getClientDao() {
		return new ClientDaoImpl( this );
	}
	
	//Pourquoi on doit faire un throws SQL exception ?
	Connection getConnection() throws SQLException {
		if(this.con == null) {
			this.con = DriverManager.getConnection(url, username, passwd);
		}
		return this.con ;
	}
	
	void releaseConnection(Connection connectionRendue) {
		if (this.con ==null) {
			return;   //pourquoi ?
		}
		try {
			if( ! this.con.isValid(10)) { 
				this.con.close();
				this.con = null;
			}
		}catch(SQLException e) {
			this.con = null;
		}
	}
	
	
}
