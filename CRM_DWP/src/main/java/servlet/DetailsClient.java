package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;

/**
 * Servlet implementation class DetailsClient
 */
@WebServlet("/detailsClient")
public class DetailsClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDao clientDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsClient() {
        super();
        clientDao = DaoFactory.getInstance().getClientDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("client", clientDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsClient.jsp").forward(request, response);
	}


}
