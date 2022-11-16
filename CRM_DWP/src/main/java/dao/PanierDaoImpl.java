package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Panier;

public class PanierDaoImpl implements PanierDao{

	private static final String SQL_INSERT       = "INSERT INTO panier(id_client) VALUES(?)";
	private static final String SQL_SELECT       = "SELECT id, id_client FROM panier";
    private static final String SQL_SELECT_BY_ID = "SELECT id_client FROM panier WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM panier WHERE id = ? ";
	private static final String SQL_UPDATE 		 = "UPDATE panier SET id_client = ? WHERE id = ?";
	
	private DaoFactory factory;
	
	protected PanierDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void creer(Panier panier) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setLong( 1, panier.getClients().getId() );
						
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec création Panier (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                panier.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec création Panier (ID non retourné)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec création Panier",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	@Override
	public Panier trouver(long id) throws DaoException {
		Panier            panier=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  panier = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Panier");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de recherche BDD Panier", e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
		return panier;
	}

	@Override
	public List<Panier> lister() throws DaoException {
		List<Panier> listePaniers = new ArrayList<Panier>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listePaniers.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de lecture BDD Panier", e);
	    } finally {
	    	factory.releaseConnection(con);
		}		
		return listePaniers;
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
				  throw new DaoException("Erreur de suppression Panier("+id+")");
			  }
		      pst.close();
	    } catch(SQLException ex) {
	    	throw new DaoException("Erreur de suppression BDD Panier", ex);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public void update(Panier panier) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setLong(1, panier.getClients().getId());
			pst.setLong(2, panier.getId());			
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec mise à jour Panier" );
            }
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec mise à jour Panier",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}
	
	
	private static Panier map(ResultSet resultSet) throws SQLException {
		Panier p = new Panier();
		p.setId(resultSet.getLong("id"));
		
		//Vérifier que ça a bien été créé
		ClientDao clientDao = DaoFactory.getInstance().getClientDao();
		try {
			p.setClients(clientDao.trouver(resultSet.getLong("id_client")));
		}catch(DaoException e) {
			e.printStackTrace();
		}
	
		return p;
	}
	
	
	
}
