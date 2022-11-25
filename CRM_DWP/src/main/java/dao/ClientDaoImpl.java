package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Client;

public class ClientDaoImpl implements ClientDao {

	private static final String SQL_INSERT       = "INSERT INTO clients(nom, prenom, id_adresse, nom_societe, mail, telephone, etat, genre) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id,nom, prenom, id_adresse, nom_societe, mail, telephone, etat, genre FROM clients";
    private static final String SQL_SELECT_BY_ID = "SELECT id,nom, prenom, id_adresse, nom_societe, mail, telephone, etat, genre FROM clients WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM clients WHERE id = ? ";

	private static final String SQL_UPDATE = "UPDATE clients SET nom=?, prenom=?, id_adresse=?; nom_societe=?, mail=? telephone=?, etat=?, genre=? WHERE id = ?";

	private DaoFactory factory;

	protected ClientDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Client client) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setString( 1, client.getNom());
			pst.setString( 2, client.getPrenom());
			pst.setLong( 3, client.getAdresse().getId());
			pst.setString( 4, client.getNom_societe() );
			pst.setString( 5, client.getMail() );
			pst.setString( 6, client.getTelephone() );
			pst.setInt( 7, client.getGenre() );
			pst.setInt( 8, client.getEtat() );

			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec création Client (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                client.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec création Client (ID non retourn�)" );
            }
            rsKeys.close();
			pst.close();

	    } catch(SQLException ex) {
	    	throw new DaoException("Echec création Client",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}


	@Override
	public void update(Client client) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setString( 1, client.getNom() );
			pst.setString( 2, client.getPrenom() );
			pst.setLong( 3, client.getAdresse().getId() );
			pst.setString( 4, client.getNom_societe() );
			pst.setString( 5, client.getMail() );
			pst.setString( 6, client.getTelephone() );
			pst.setInt( 7, client.getGenre() );
			pst.setInt( 8, client.getEtat() );

			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec modification Client" );
            }
			pst.close();

	    } catch(SQLException ex) {
	    	throw new DaoException("Echec modification Client",ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}


	@Override
	public Client trouver(long id) throws DaoException {
		Client            client=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  client = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Client");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de recherche BDD Client", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return client;
	}

	@Override
	public List<Client> lister() throws DaoException {
		List<Client> listeClients= new ArrayList<>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeClients.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de lecture BDD Client", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		return listeClients;
	}

	@Override
	public void supprimer(long id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_DELETE_BY_ID );
			  pst.setLong(1, id);
			  int statut = pst.executeUpdate();
			  if ( statut == 0 ) {
				  throw new DaoException("Erreur de suppression Client("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Client", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}

	}
    /*
     * Mapping (correspondance) entre un ResultSet et un JavaBean
     * M�thode utilitaire (interne)
     */
    private static Client map( ResultSet resultSet ) throws SQLException {
        Client c = new Client();
        c.setId( resultSet.getLong( "id" ) );
        c.setNom( resultSet.getString( "nom" ) );
        c.setPrenom( resultSet.getString( "prenom" ) );
        AdresseDao adresseDao = DaoFactory.getInstance().getAdresseDao();

        try {
			c.setAdresse(adresseDao.trouver(resultSet.getLong( "id_adresse" )));
		} catch (DaoException e) {
			e.printStackTrace();
		}
        c.setNom_societe( resultSet.getString( "nom_societe" ) );
        c.setMail( resultSet.getString( "mail" ) );
        c.setTelephone( resultSet.getString( "telephone" ) );
        c.setEtat( resultSet.getInt( "etat" ) );
        c.setGenre( resultSet.getInt( "genre" ) );
        return c;
    }



}
