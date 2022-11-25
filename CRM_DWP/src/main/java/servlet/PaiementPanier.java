package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import dao.ClientDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.PaiementDao;
import dao.PanierDao;
import model.Client;
import model.Paiement;
import model.Panier;
import utils.Utils;

@WebServlet("/paiementPanier")
public class PaiementPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    public PaiementPanier() {
        super();
    }
       	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;
//		try {
//			request.setAttribute("panier", panierDao.lister());
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
		
		//Je modifie en supposant que lorsqu'on procède au paiement à partir du panier ça renvoie l'id du panier
		try {
			String idPanier = request.getParameter("idLivre");
			if(idPanier != null) {		
				Long id = Long.parseLong(idPanier);
				if(id > 0) {
					responseContent = new ServicePanier().trouver(id);
					contentType = "application/json";
				} else {
					responseStatus = 400;
					responseContent = "Erreur : L'idPanier doit etre strictement superieur à 0.";
				}
			} else {
				responseContent = new ServicePanier().lister();
				contentType = "application/json";
			}
		} catch(NumberFormatException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format du param�tre idPanier n'est pas bon.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding("UTF-8");
		String responseContent="Ok", contentType = "text";
		int responseStatus = 200;

		try {
			JsonObject data = Utils.getJsonFromBuffer(request);

			new ServicePanier().creer(data);
			new ServicePaiement().creer(data);

		} catch(JsonSyntaxException e) {
			responseStatus = 400;
			responseContent = "Erreur : Le format des donnees n'est pas bon, veuillez utiliser du JSON.";
		} catch(ServiceException e) {
			responseStatus = 400;
			responseContent = "Erreur : " +e.getMessage();
		} catch(Exception e) {
			e.printStackTrace();
			responseStatus = 500;
			responseContent = "Erreur : Erreur serveur.";
		}

		response.setContentType(contentType);
		response.setStatus(responseStatus);
		response.getWriter().write(responseContent);
		
		
		//Une fois le paiement effectué on renvoie vers l'accueil ?
		response.sendRedirect(request.getContextPath() + "/");
		
	}

}
