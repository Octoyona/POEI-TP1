package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DaoException;
import model.Produit;

public class ProduitDaoImpl implements ProduitDao {

	private static final String SQL_INSERT			= "INSERT INTO produits(nom, description, prix) VALUES(?,?,?)";
	private static final String SQL_SELECT			= "SELECT id,nom,description,prix FROM produits";
	private static final String SQL_SELECT_BY_ID	= "SELECT nom,description,prix FROM produits WHERE id = ?";
	private static final String SQL_DELETE_BY_ID	= "DELETE FROM produits WHERE id = ?";
	private static final String SQL_UPDATE			= "UPDATE produits SET nom=?, description=?, prix=? WHERE id=?";
			
	private DaoFactory factory;
	
	
	protected ProduitDaoImpl(DaoFactory factory) {
		this.factory = factory;
	}
	
	
	//Mapping
	//Vérifier que c'est bien long, float, etc
	private static Produit map(ResultSet resultSet) throws SQLException {
		Produit p = new Produit();
		p.setId(resultSet.getLong("id"));
		p.setNom(resultSet.getString("nom"));
		p.setDescription(resultSet.getString("description"));
		p.setPrix(resultSet.getFloat("prix"));
		return p;
	}
	
	//Override
	@Override
	public void creer(Produit produit) throws DaoException {
		Connection con = null;
		
		try {
			con = factory.getConnection();
			
			PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setFloat(3, produit.getPrix());
			
			int statut = ps.executeUpdate();
			
			if(statut == 0) {
				throw new DaoException("Echec de la création du produit (aucun ajout)");
			}
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				produit.setId(rs.getLong(1)); 
			} else {
				throw new DaoException("Echec de la création du produit (aucun id retourné)");
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			throw new DaoException("Echec de la création du produit", e);
		} finally {
			factory.releaseConnection(con);
		}
	}

	@Override
	public Produit trouver(long id) throws DaoException {
		Produit produit 		= null;
		Connection con 			= null;
		PreparedStatement ps 	= null;
		ResultSet rs 			= null;
		
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT_BY_ID);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				produit = map(rs);
			} else {
				throw new DaoException("Erreur de recherche BDD Produit"); 
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			throw new DaoException("Erreur de recherche BDD Produit", e);
		} finally {
			factory.releaseConnection(con);
		}
		
		return produit;
	}

	@Override
	public List<Produit> lister() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Produit> listeProduits = new ArrayList<Produit>();
		
		try {
			con = factory.getConnection();
			ps = con.prepareStatement(SQL_SELECT);
			rs = ps.executeQuery();
			while(rs.next()) {
				listeProduits.add(map(rs));
			}
			rs.close();
			ps.close();
		} catch(SQLException e) {
			throw new DaoException("Erreur de lecture BDD Produit", e);
		} finally {
			factory.releaseConnection(con);
		}
		
		return listeProduits;
	}

	@Override
	public void supprimer(long id) throws DaoException {
		Connection   con=null;
		try {
			  con = factory.getConnection();
			  PreparedStatement ps = con.prepareStatement( SQL_DELETE_BY_ID );
			  ps.setLong(1, id);
			  int statut = ps.executeUpdate();
			  if ( statut == 0 ) {
				  throw new DaoException("Erreur de suppression du Produit ("+id+")");
			  }
		      ps.close();
	    } catch(SQLException e) {
	    	throw new DaoException("Erreur de suppression BDD Porduit", e);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}

	@Override
	public void update(Produit produit) throws DaoException {
		Connection con=null;
		try {
			con = factory.getConnection();
			
			PreparedStatement ps = con.prepareStatement( SQL_UPDATE );
			ps.setString( 1, produit.getNom() );
			ps.setString( 2, produit.getDescription() );
			ps.setFloat( 3, produit.getPrix() );
			ps.setLong( 4, produit.getId() );

			int statut = ps.executeUpdate();

            if ( statut == 0 ) {
                throw new DaoException( "Echec modification du Produit" );
            }
			ps.close();
			
	    } catch(SQLException e) {
	    	throw new DaoException("Echec modification du Produit",e);
	    } finally {
	    	factory.releaseConnection(con);
		}
	}


	
}
