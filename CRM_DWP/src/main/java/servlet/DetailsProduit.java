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
	
	private ProduitDao produitDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsProduit() {
        super();
        produitDao = DaoFactory.getInstance().getProduitDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("client", produitDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsProduit.jsp").forward(request, response);
	}


}
