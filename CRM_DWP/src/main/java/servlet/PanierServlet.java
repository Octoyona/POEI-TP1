package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonSyntaxException;

import services.ServiceException;
import services.ServicePanier;
import utils.Utils;


@WebServlet("/panier")
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override //Récupération produit
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		try {
			String idPanier = req.getParameter("id");
			if(idPanier != null) {
				response = new ServicePanier().trouver(Long.parseLong(idPanier));
			} else {
				response = new ServicePanier().lister();
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


	@Override //Création produit
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		try {
			new ServicePanier().ajouter(Utils.getJsonFromBuffer(req).getAsJsonObject());
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


	@Override //Modification auteur
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		try {
			new ServicePanier().modifier(Utils.getJsonFromBuffer(req).getAsJsonObject());
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


	@Override //Suppression auteur
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		try {
			String idPanier = req.getParameter("id");
			new ServicePanier().supprimer(Long.parseLong(idPanier));
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
