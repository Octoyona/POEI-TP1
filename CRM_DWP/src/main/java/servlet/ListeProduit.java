package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import dao.PanierDao;
import dao.ProduitDao;
import model.Contient;
import model.Produit;

@WebServlet("/listeProduit")
public class ListeProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListeProduit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProduitDao produitsDao = DaoFactory.getInstance().getProduitDao();
		
		try {
			request.setAttribute("produits", produitsDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		this.getServletContext().getRequestDispatcher("listeProduits.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Long idProduitChoisi;
		Long idClient;
		int quantite;
		
		try {
		idProduitChoisi = Long.parseLong(request.getParameter("idProduitChoisi"));
		idClient =  Long.parseLong(request.getParameter("idClient"));
		quantite = Integer.parseInt(request.getParameter("quantite"));
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
		Produit produitChoisi = produitDao.trouver(idProduitChoisi);	
		PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
		Long idPanier = panierDao.trouver(idClient).getId();
		ContientDao contientDao = DaoFactory.getInstance().getContientDao();
		Contient contient = new Contient(produitChoisi, idPanier, quantite);
		contientDao.creer(contient);
				
	}

}