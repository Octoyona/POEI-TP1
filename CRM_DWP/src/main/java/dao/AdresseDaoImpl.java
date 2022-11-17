package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Adresse;

public class AdresseDaoImpl implements AdresseDao{
	private static final String SQL_INSERT       = "INSERT INTO adresses(rue, ville, code_postal, pays) VALUES(?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id, rue, ville, code_postal, pays FROM adresses";
    private static final String SQL_SELECT_BY_ID = "SELECT id, rue, ville, code_postal, pays FROM adresses WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM adresses WHERE id = ? ";
	private static final String SQL_UPDATE 		 = "UPDATE adresses SET rue = ?, ville = ?, code_postal = ?, pays = ?  WHERE id = ?";
	
	private DaoFactory factory;
	
	protected AdresseDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}

	@Override
	public void creer(Adresse adresse) throws DaoException {
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setString( 1, adresse.getRue());
			pst.setString( 2, adresse.getVille());
			pst.setString( 3, adresse.getCode_postal());
			pst.setString( 4, adresse.getPays());
						
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec création Adresse (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                adresse.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec création Adresse (ID non retourné)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec création Adresse",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	@Override
	public Adresse trouver(long id) throws DaoException {
		Adresse          	adresse=null;
		Connection        	con=null;
		PreparedStatement 	pst=null;
		ResultSet         	rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  adresse = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Adresse");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de recherche BDD Adresse", e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
		return adresse;
	}

	@Override
	public List<Adresse> lister() throws DaoException {
		List<Adresse> listeAdresses = new ArrayList<Adresse>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeAdresses.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de lecture BDD Adresse", e);
	    } finally {
	    	factory.releaseConnection(con);
		}		
		return listeAdresses;
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
				  throw new DaoException("Erreur de suppression Adresse("+id+")");
			  }
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de suppression BDD Adresse", e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public void update(Adresse adresse) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setString(1, adresse.getRue());
			pst.setString(2, adresse.getVille());		
			pst.setString(3, adresse.getCode_postal());		
			pst.setString(4, adresse.getPays());		
			pst.setLong(5, adresse.getId());		
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec mise à jour Adresse" );
            }
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec mise à jour Adresse",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}
	
	private static Adresse map(ResultSet resultSet) throws SQLException {
		Adresse a = new Adresse();
				
		a.setId(resultSet.getLong("id"));
		a.setRue(resultSet.getString("rue"));
		a.setVille(resultSet.getString("ville"));
		a.setCode_postal(resultSet.getString("code_postal"));
		a.setPays(resultSet.getString("pays"));
	
		return a;
	}
	
}
