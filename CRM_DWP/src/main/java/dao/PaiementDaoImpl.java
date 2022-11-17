package dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Paiement;

public class PaiementDaoImpl implements PaiementDao {

	private static final String SQL_INSERT       = "INSERT INTO paiement(numero_carte, code_confidentiel, banque, id_client) VALUES(?,?,?,?)";
	private static final String SQL_SELECT       = "SELECT id, numero_carte, code_confidentiel, banque, id_client FROM paiement";
    private static final String SQL_SELECT_BY_ID = "SELECT id, numero_carte, code_confidentiel, banque, id_client FROM paiement WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM paiement WHERE id = ? ";
	private static final String SQL_UPDATE 		 = "UPDATE paiement SET numero_carte = ?, code_confidentiel = ?, banque = ?, id_client = ?  WHERE id = ?";
	
	private DaoFactory factory;
	
	protected PaiementDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void creer(Paiement paiement) throws DaoException {
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
			pst.setString( 1, paiement.getNumero_carte());
			pst.setString( 2, paiement.getCode_confidentiel());
			pst.setString( 3, paiement.getBanque());
			pst.setLong( 4, paiement.getClient().getId());
						
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec création Paiement (aucun ajout)" );
            }
            ResultSet rsKeys = pst.getGeneratedKeys();
            if ( rsKeys.next() ) {
                paiement.setId( rsKeys.getLong( 1 ) );
            } else {
                throw new DaoException( "Echec création Paiement (ID non retourné)" );
            }
            rsKeys.close();
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec création Paiement",e);
	    } finally {
	    	factory.releaseConnection(con);
		}	
	}

	@Override
	public Paiement trouver(long id) throws DaoException {
		Paiement          paiement=null;
		Connection        con=null;
		PreparedStatement pst=null;
		ResultSet         rs=null;
		try {
			  con = factory.getConnection();
			  pst = con.prepareStatement( SQL_SELECT_BY_ID );
			  pst.setLong(1, id);
		      rs  = pst.executeQuery();
		      if ( rs.next() ) {
		    	  paiement = map(rs);
		      } else {
		    	  throw new DaoException("Erreur de recherche BDD Paiement");
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de recherche BDD Paiement", e);
	    } finally {
	    	factory.releaseConnection(con);
		}
		
		return paiement;
	}

	@Override
	public List<Paiement> lister() throws DaoException {
		List<Paiement> listePaiement = new ArrayList<Paiement>();
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement pst = con.prepareStatement( SQL_SELECT );
		      ResultSet         rs  = pst.executeQuery();
		      while ( rs.next() ) {
		    	  listePaiement.add( map(rs) );
		      }
		      rs.close();
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de lecture BDD Paiement", e);
	    } finally {
	    	factory.releaseConnection(con);
		}		
		return listePaiement;
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
				  throw new DaoException("Erreur de suppression Paiement("+id+")");
			  }
		      pst.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de suppression BDD Paiement", e);
	    } finally {
	    	factory.releaseConnection(con);
		}	
	}

	@Override
	public void update(Paiement paiement) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement pst = con.prepareStatement( SQL_UPDATE );
			pst.setString(1, paiement.getNumero_carte());
			pst.setString(2, paiement.getCode_confidentiel());		
			pst.setString(3, paiement.getBanque());		
			pst.setLong(4, paiement.getClient().getId());		
			pst.setLong(5, paiement.getId());		
			
			int statut = pst.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec mise à jour Paiement" );
            }
			pst.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec mise à jour Paiement",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	private static Paiement map(ResultSet resultSet) throws SQLException {
		Paiement p = new Paiement();
				
		p.setId(resultSet.getLong("id"));
		p.setNumero_carte(resultSet.getString("numero_carte"));
		p.setCode_confidentiel(resultSet.getString("code_confidentiel"));
		p.setBanque(resultSet.getString("banque"));
		
		ClientDao clientDao = DaoFactory.getInstance().getClientDao();
		try {
			p.setClient(clientDao.trouver(resultSet.getLong("id_client")));
		} catch(DaoException e) {
			e.printStackTrace();
		}
	
		return p;
	}
	
	
}
