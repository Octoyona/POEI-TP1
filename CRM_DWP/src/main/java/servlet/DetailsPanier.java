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


@WebServlet("/detailsPanier")
public class DetailsPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PanierDao panierDao;
	

    public DetailsPanier() {
        super();
        panierDao = DaoFactory.getInstance().getPanierDao();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("panier", panierDao.trouver(id));
		} catch(DaoException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsPanier.jsp").forward(request, response);
	}

}
