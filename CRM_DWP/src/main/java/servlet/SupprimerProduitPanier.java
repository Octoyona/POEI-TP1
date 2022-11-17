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

@WebServlet("/supprimerProduit")
public class SupprimerProduitPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ContientDao contientDao;
	
    public SupprimerProduitPanier() {
        super();
        contientDao = DaoFactory.getInstance().getContientDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			long id = Long.parseLong(request.getParameter("idClient"));
			contientDao.supprimer(id);
			
			//Ajout d'un élément dans la session
			request.getSession().setAttribute("confirmMessage", "Le produit a bien été supprimé de votre panier.");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/DetailsPanier" );
	}
}
