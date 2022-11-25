package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonSyntaxException;

import services.ServiceAdresse;
import services.ServiceException;
import services.ServiceProduit;
import utils.Utils;


@WebServlet("/adresse")
public class AdresseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override //Récupération adresse
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
				
		try {
			String idAdresse = req.getParameter("id");
			if(idAdresse != null) {
				response = new ServiceAdresse().trouver(Long.parseLong(idAdresse));
			} else {
				response = new ServiceAdresse().lister();
			}
			contentType = "application/json";
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(ServiceException e) {
			response = e.getMessage();
			responseStatus = 404;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}


	@Override //Création adresse
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			new ServiceAdresse().ajouter(Utils.getJsonFromBuffer(req).getAsJsonObject());
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (ServiceException e) {
			response = e.getMessage();
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			new ServiceAdresse().modifier(Utils.getJsonFromBuffer(req).getAsJsonObject());
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (ServiceException e) {
			response = e.getMessage();
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}

	
	@Override //Suppression adresse
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		try {
			String idAdresse = req.getParameter("id");
			new ServiceProduit().supprimer(Long.parseLong(idAdresse));
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(ServiceException e) {
			response = "Erreur : "+e.getMessage();
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}
    
    
    

}
