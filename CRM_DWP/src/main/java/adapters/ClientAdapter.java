package adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
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


}
