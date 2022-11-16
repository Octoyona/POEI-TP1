package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import dao.PaiementDao;
import dao.PanierDao;
import model.Panier;

@WebServlet("/paiement")
public class Paiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
		
		try {
			request.setAttribute("panier", panierDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Paiement paiementDao = DaoFactory.getInstance().getPaiementDao();
		
		try {
			request.setAttribute("paiement", paiementDao.creer());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
