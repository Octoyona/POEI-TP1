package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contient;

public class ContientDaoImpl implements ContientDao {

	private static final String SQL_INSERT       = "INSERT INTO contient(id_panier, id_produit, quantite) VALUES(?,?,?)";
	private static final String SQL_SELECT       = "SELECT id, id_panier, id_produit, quantite FROM contient";
    private static final String SQL_SELECT_BY_ID = "SELECT id_panier, id_produit, quantite FROM contient WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM contient WHERE id = ? ";
	private static final String SQL_UPDATE 		 = "UPDATE contient SET id_panier = ?, id_produit = ?, quantite = ?  WHERE id = ?";
	
	private DaoFactory factory;
	
	protected ContientDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	
	@Override
	public void creer(Contient contient) throws DaoException {
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setLong( 1, contient.getPanier().getId());
			pst.setLong( 2, contient.getProduit().getId());
			pst.setInt( 3,contient.getQuantité());
						
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec création Contient (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                contient.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec création Contient (ID non retourné)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec création Contient",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public Contient trouver(long id) throws DaoException {
		Contient          contient=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  contient = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Contient");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de recherche BDD Contient", e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
		return contient;
	}

	@Override
	public List<Contient> lister() throws DaoException {
		List<Contient> listeContient = new ArrayList<Contient>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listeContient.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de lecture BDD Contient", e);
	    } finally {
	    	factory.releaseConnection(con);
		}		
		return listeContient;
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
				  throw new DaoException("Erreur de suppression Contient("+id+")");
			  }
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de suppression BDD Contient", e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}

	@Override
	public void update(Contient contient) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setLong(1, contient.getPanier().getId());
			pst.setLong(2, contient.getProduit().getId());
			pst.setInt(3, contient.getQuantité());
			pst.setLong(4, contient.getId());	
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec mise à jour Contient" );
            }
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec mise à jour Contient",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
	}
	
	private static Contient map(ResultSet resultSet) throws SQLException {
		Contient c = new Contient();
				
		c.setId(resultSet.getLong("id"));
		c.setQuantité(resultSet.getInt("quantite"));
		
		PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
		try {
			c.setPanier(panierDao.trouver(resultSet.getLong("id_panier")));
		} catch(DaoException e) {
			e.printStackTrace();
		}
		
		
		ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
		try {
			c.setProduit(produitDao.trouver(resultSet.getLong("id_produit")));
		} catch(DaoException e) {
			e.printStackTrace();
		}
	
		return c;
	}

}
