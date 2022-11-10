package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChoixProduit
 */
@WebServlet("/ChoixProduit")
public class ChoixProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoixProduit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idProduitChoisi;
		int idClient;
		int quantite;
		
		try {
		idProduitChoisi = Integer.parseInt(request.getParameter("idProduitChoisi"));
		idClient =  Integer.parseInt(request.getParameter("idClient"));
		quantite = Integer.parseInt(request.getParameter("quantite"));
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();
		Produit produitChoisi = produitDao.trouver(idProduitChoisi);	
		PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
		int idPanier = panierDao.trouver(idClient).getId();
		ContientDao contientDao = DaoFactory.getInstance().getContientDao();
		Contient contient = new Contient(idProduitChoisi, idPanier, quantite);
		contientDao.creer(contient);
				
	}

}
