package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonSyntaxException;

import services.ServiceException;
import services.ServiceProduit;
import utils.Utils;


@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override //Récupération auteur
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		try {
			String idAuteur = req.getParameter("id");
			if(idAuteur != null) {
				response = new ServiceProduit().trouver(Long.parseLong(idAuteur));
			} else {
				response = new ServiceProduit().lister();
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


	@Override //Création auteur
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";

		try {
			new ServiceProduit().ajouter(Utils.getJsonFromBuffer(req).getAsJsonObject());
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
			new ServiceProduit().modifier(Utils.getJsonFromBuffer(req).getAsJsonObject());
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
			String idProduit = req.getParameter("id");
			new ServiceProduit().supprimer(Long.parseLong(idProduit));
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
