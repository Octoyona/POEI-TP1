package adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dao.DaoException;
import daoImpl.DaoContient;
import model.Contient;
import model.Produit;

public class ProduitAdapter implements JsonSerializer<Produit>, JsonDeserializer<Produit> {

	@Override
	public JsonElement serialize(Produit produit, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", produit.getId());
		json.addProperty("nom", produit.getNom());
		json.addProperty("description", produit.getDescription());
		json.addProperty("prix", produit.getPrix());
		
		JsonArray contientsArray = new JsonArray();
		JsonObject tmp;
		for(Contient f : produit.getContients()) {
			tmp = new JsonObject();
			tmp.addProperty("id", f.getId());
			contientsArray.add(tmp);
		}
				
		json.add("contients", contientsArray);
		
		return json;
	}

	@Override
	public Produit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Produit p = new Produit();
		
		JsonObject data = json.getAsJsonObject();
		
		String nom = data.get("nom").getAsString();
		p.setNom(nom);

		String description = data.get("description").getAsString();
		p.setDescription(description);
		
		float prix = data.get("prix").getAsFloat();
		p.setPrix(prix);

		DaoContient daoContient = new DaoContient();
		JsonArray contientsArray = data.get("contients").getAsJsonArray();
		for(JsonElement contientElement : contientsArray) {
			JsonObject contientJson = contientElement.getAsJsonObject();
			long idContient = contientJson.get("id").getAsLong();
			Contient c = new Contient();
			try {
				c = daoContient.trouver(idContient);
				p.addContient(c);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("id contient invalide");
			} catch (DaoException e) {
				e.printStackTrace();
				System.err.println("erreur dao contient");
			}
		}
		
		return p;
	}
}
