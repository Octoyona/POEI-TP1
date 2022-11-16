package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDao;
import dao.DaoException;
import dao.DaoFactory;

@WebServlet("/supprimerClient")
public class SupprimerClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientDao clientDao = DaoFactory.getInstance().getClientDao();
		try {
			long id = Long.parseLong(request.getParameter("id"));
			clientDao.supprimer(id);
			
			//Ajout d'un élément dans la session
			request.getSession().setAttribute("confirmMessage", "Votre compte a bien été supprimé !");
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect( request.getContextPath() + "/listeProduits" );
	}
}
