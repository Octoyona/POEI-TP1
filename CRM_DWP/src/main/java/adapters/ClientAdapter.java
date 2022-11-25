package adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dao.DaoException;
import model.Adresse;
import model.Client;
import model.Paiement;

public class ClientAdapter implements JsonSerializer<Client>{

	@Override
	public JsonElement serialize(Client client, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		
		json.addProperty("id", client.getId());
		json.addProperty("nom", client.getNom());
		json.addProperty("prenom", client.getPrenom());
		json.addProperty("nomSociete", client.getNomSociete());
		json.addProperty("mail", client.getMail());
		json.addProperty("telephone", client.getTelephone());
		json.addProperty("etat", client.getEtat());
		json.addProperty("genre", client.getGenre());
		
		JsonObject adresse = null;
		if(client.getAdresse() != null) {
			adresse = new JsonObject();
			adresse.addProperty("id", client.getAdresse().getId());
			adresse.addProperty("rue", client.getAdresse().getRue());
			adresse.addProperty("ville", client.getAdresse().getVille());
			adresse.addProperty("codePostal", client.getAdresse().getCode_postal());
			adresse.addProperty("pays", client.getAdresse().getPays());
		}
		json.add("adresse", adresse);
		
		JsonObject panier = null;
		if(client.getPanier() != null) {
			panier = new JsonObject();
			panier.addProperty("id", client.getPanier().getId());
			panier.addProperty("rue", client.getPanier().getPrixTotal());
		}
		json.add("panier", panier);
		
		JsonArray paiements = new JsonArray();
		JsonObject tmp;
		for(Paiement p : client.getPaiements()) {
			tmp = new JsonObject();
			tmp.addProperty("id", p.getId());
			tmp.addProperty("banque", p.getBanque());
		}
		json.add("paiements", paiements);

		return json;
	}

	@Override
	public Client deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		
		Client c = new Client();
		
		JsonObject data = json.getAsJsonObject();
		
		String nom = data.get("nom").getAsString;
		c.setNom(nom);
		
		String prenom = data.get("prenom").getAsString;
		c.setNom(prenom);
		
		String nomSociete = data.get("nomSociete").getAsString;
		c.setNom(nomSociete);
		
		String mail = data.get("mail").getAsString;
		c.setNom(mail);
		
		String telephone = data.get("telephone").getAsString;
		c.setNom(telephone);
		
		String etatString = data.get("etat").getAsString;
		int etat;
		try {
			etat = Integer.parseInt(etatString);
			c.setEtat(etat);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String genreString = data.get("genre").getAsString;
		int genre;
		try {
			genre = Integer.parseInt(genreString);
			c.setEtat(genre);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String idAdresseString = data.get("idAdresse").getAsString;
		Long idAdresse;
		try {
			idAdresse = Long.parseLong(idAdresseString);
		
			DaoAdresse daoAdresse = new DaoAdresse();
			Adresse adresse = daoAdresse.trouver(idAdresse);
			c.setAdresse(adresse);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.err.println("id adresse invalide");
		}catch(DaoException e) {
			e.printStackTrace();
			System.err.println("erreur dao adresse");
		}
		
		
		
		return c;
	}
	
	
}
