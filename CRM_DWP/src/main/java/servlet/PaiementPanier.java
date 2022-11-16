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
import dao.PaiementDao;
import dao.PanierDao;
import model.Client;
import model.Paiement;

@WebServlet("/paiement")
public class PaiementPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PanierDao panierDao = DaoFactory.getInstance().getPanierDao();
		
		try {
			request.setAttribute("panier", panierDao.lister());
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		PaiementDao paiementDao = DaoFactory.getInstance().getPaiementDao();
		ClientDao clientDao =  DaoFactory.getInstance().getClientDao();
		
		try {
			String numero_carte = request.getParameter("numeroCarte");
			String code_confidentiel = request.getParameter("codeConfidentiel");
			String banque = request.getParameter("banque");
			
			
			
			long idClient = Long.parseLong(request.getParameter("idClient"));
			Client client = clientDao.trouver(idClient);
			
			Paiement paiement = new Paiement(numero_carte, code_confidentiel, banque, client);
			request.setAttribute("paiement", paiement);
			
			paiementDao.creer(paiement);
			
		} catch (DaoException | NumberFormatException e) {
			e.printStackTrace();
		}

	}

}
