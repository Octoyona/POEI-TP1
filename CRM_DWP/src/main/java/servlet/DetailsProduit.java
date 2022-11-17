package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import dao.ProduitDao;

/**
 * Servlet implementation class DetailsClient
 */
@WebServlet("/detailsProduit")
public class DetailsProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ProduitDao produitDao = DaoFactory.getInstance().getProduitDao();

		String id = request.getParameter("id");

		try {
			Long num = Long.parseLong(id);
			request.setAttribute("produit", produitDao.trouver(num));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsProduit.jsp").forward(request, response);

	}

}
