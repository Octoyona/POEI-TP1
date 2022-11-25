package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import antlr.Utils;
import dao.DaoException;
import daoImpl.DaoAdresse;
import model.Adresse;


@WebServlet("/adresse")
public class AdresseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoAdresse daoAdresse;
	private DaoClient daoClient;
	
	
    public AdresseServlet() {
        super();
        daoAdresse = new DaoAdresse();
        daoClient = new DaoClient();

    }


	@Override //Récupération adresse
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		
		int responseStatus = 200;
		String response = "Ok";
		String contentType = "text";
		
		Gson gson = utils.Utils.getSuperJson();
		
		try {
			String idAdresse = req.getParameter("id");
			if(idAdresse != null) {
				Adresse adresse = DaoAdresse.trouver(Long.parseLong(idAdresse));
				response = gson.toJson(adresse);
			} else {
				List<Adresse> adresses = daoAdresse.lister();
				response = gson.toJson(adresses);
			}
			contentType = "application/json";
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "L'adresse n'a pas été trouvé.";
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
				
			//Récupération du body de la requête sous forme de String
			StringBuffer buffer = new StringBuffer();
			String line = null, body = "";
			BufferedReader reader = req.getReader();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			body = buffer.toString();
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations de adresse depuis l'objet JSON
			String rue = data.get("rue").getAsString();
			String ville = data.get("ville").getAsString();
			String codePostal = data.get("code_postal").getAsString();
			String pays = data.get("pays").getAsString();
			
			Long clientId = data.get("client_id").getAsLong();	
			Client client = daoClient.trouver(clientId);
			
			//Création du adresse
			Adresse adresse = new Adresse();
			adresse.setRue(rue);
			adresse.setVille(ville);
			adresse.setCode_postal(codePostal);
			adresse.setPays(pays);
			adresse.setClient(client);
			
			
			//Sauvegarde de adresse
			daoAdresse.ajouter(adresse);
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (DaoException e) {
			e.printStackTrace();
			response = "Erreur serveur.";
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
				
			//Récupération du body de la requête sous forme de String
			StringBuffer buffer = new StringBuffer();
			String line = null, body = "";
			BufferedReader reader = req.getReader();
			while((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			body = buffer.toString();
			
			//Récupération d'un objet JAVA représentant un JSON
			JsonObject data = JsonParser.parseString(body).getAsJsonObject();
			
			//Récupération des informations de l'client depuis l'objet JSON
			long id = data.get("id").getAsLong();
			String rue = data.get("rue").getAsString();
			String ville = data.get("ville").getAsString();
			String codePostal = data.get("code_postal").getAsString();
			String pays = data.get("pays").getAsString();
			
			Long clientId = data.get("client_id").getAsLong();	
			Client client = daoClient.trouver(clientId);
			
			
			//Modification de l'client
			Adresse adresse = daoAdresse.trouver(id);
			adresse.setRue(rue);
			adresse.setVille(ville);
			adresse.setCode_postal(codePostal);
			adresse.setPays(pays);
			adresse.setClient(client);

			//Sauvegarde de l'client
			daoAdresse.modifier(adresse);
		} catch (JsonSyntaxException e) {
			response = "Erreur : Le format des données n'est pas bon, veuillez utiliser du JSON.";
			responseStatus = 400;
		} catch (DaoException e) {
			e.printStackTrace();
			response = "Erreur serveur.";
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
			daoAdresse.supprimer(Integer.parseInt(idAdresse));
		} catch(NumberFormatException e) {
			response = "Le paramètre id n'est pas bon.";
			responseStatus = 400;
		} catch(DaoException e) {
			response = "Erreur serveur.";
			responseStatus = 500;
		}
		
		resp.setContentType(contentType);
		resp.setStatus(responseStatus);
		resp.getWriter().write(response);
	}
    
    
    

}
