package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContientDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.PanierDao;
import dao.ProduitDao;
import model.Contient;
import model.Panier;
import model.Produit;

@WebServlet("/listeProduits")
public class ListeProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProduitDao produitsDao = DaoFactory.getInstance().getProduitDao();
		
		try {
			request.setAttribute("produits", produitsDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}

		System.err.println("test");
		this.getServletContext().getRequestDispatcher("/WEB-INF/listeProduits.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idProduitChoisi;
		Long idClient;
		int quantite;
		
		try {
			idProduitChoisi = Long.parseLong(request.getParameter("idProduitChoisi"));
			idClient =  Long.parseLong(request.getParameter("idClient"));
			quantite = Integer.parseInt(request.getParameter("quantite"));
			ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
			Produit produitChoisi = produitDao.trouver(idProduitChoisi);	
			PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
			Panier panier = panierDao.trouver(idClient);
			ContientDao contientDao = DaoFactory.getInstance().getContientDao();
			Contient contient = new Contient(panier, produitChoisi, quantite);
			contientDao.creer(contient);
		} catch (DaoException | NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
