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
import model.Panier;

@WebServlet("/paiementPanier")
public class PaiementPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PanierDao panierDao;
	
    public PaiementPanier() {
        super();
        panierDao = DaoFactory.getInstance().getPanierDao();
    }
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		try {
//			request.setAttribute("panier", panierDao.lister());
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
		
		//Je modifie en supposant que lorsqu'on procède au paiement à partir du panier ça renvoie l'id du panier
		try {
			long idPanier = Long.parseLong(request.getParameter("id"));
			request.setAttribute("panier", panierDao.trouver(idPanier));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/paiementPanier.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PaiementDao paiementDao = DaoFactory.getInstance().getPaiementDao();
		ClientDao clientDao =  DaoFactory.getInstance().getClientDao();
		PanierDao panierDao =  DaoFactory.getInstance().getPanierDao();
		
		Panier panier = new Panier();
		Client client = new Client();
		Paiement paiement = new Paiement();
		
		String numero_carte = request.getParameter("numeroCarte");
		String code_confidentiel = request.getParameter("codeConfidentiel");
		String banque = request.getParameter("banque");
		
		try {
			long idPanier = Long.parseLong(request.getParameter("idPanier"));
			panier = panierDao.trouver(idPanier);
			long idClient = panier.getClients().getId();
			client = clientDao.trouver(idClient);
			request.setAttribute("client", client);
			
			paiement.setClient(client);
			paiement.setBanque(banque);
			paiement.setCode_confidentiel(code_confidentiel);
			paiement.setNumero_carte(numero_carte);
			
			paiementDao.creer(paiement);
			
		} catch (DaoException | NumberFormatException e) {
			e.printStackTrace();
		}
		
		//Une fois le paiement effectué on renvoie vers l'accueil ?
		response.sendRedirect(request.getContextPath() + "/");
		
	}

}
