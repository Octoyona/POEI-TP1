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
import form.ClientForm;
import form.ContientForm;
import model.Client;
import model.Contient;

/**
 * Servlet implementation class ModifierProduit
 */
@WebServlet("/modifierProduit")
public class ModifierProduitPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsPanier.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ContientForm contientForm = new ContientForm();
		Contient contient = new Contient();
		
		try {
			contient = contientForm.saveContient(request, ContientForm.MODIFICATION);
			request.setAttribute("client", contient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("form", contientForm);
		
		if(!contientForm.isValid()) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/detailsPanier.jsp").forward(request, response);
		} else {
			response.sendRedirect( request.getContextPath() + "/" );	
		}
}
}
