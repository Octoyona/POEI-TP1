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
import dao.ProduitDao;
import model.Contient;
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/listeProduits.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long idProduitChoisi;
		int quantite;
		long idPanier;
		
		try {
			idProduitChoisi = Long.parseLong(request.getParameter("idProduitChoisi"));
			quantite = Integer.parseInt(request.getParameter("quantite"));
			idPanier = Long.parseLong(request.getParameter("idPanier"));
			ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
			Produit produitChoisi = produitDao.trouver(idProduitChoisi);	
			ContientDao contientDao = DaoFactory.getInstance().getContientDao();
			Contient contient = new Contient(produitChoisi, quantite);
			contientDao.creer(contient, idPanier);
		} catch (DaoException | NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
