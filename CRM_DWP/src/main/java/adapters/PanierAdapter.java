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
import daoImpl.DaoClient;
import daoImpl.DaoContient;
import model.Client;
import model.Contient;
import model.Panier;

public class PanierAdapter implements JsonSerializer<Panier>, JsonDeserializer<Panier> {

	@Override
	public JsonElement serialize(Panier panier, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", panier.getId());
		
		JsonObject client = new JsonObject();
		if(panier.getClient() != null) {
			client.addProperty("id", panier.getClient().getId());
		}
		json.add("client", client);
		
		JsonArray contientsArray = new JsonArray();
		JsonObject tmp;
		for(Contient f : panier.getContients()) {
			tmp = new JsonObject();
			tmp.addProperty("id", f.getId());
			contientsArray.add(tmp);
		}
				
		json.add("contients", contientsArray);
		
		
		return json;
	}

	@Override
	public Panier deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Panier p = new Panier();
		
		JsonObject data = json.getAsJsonObject();

		DaoClient daoClient = new DaoClient();
		JsonObject clientJson = data.get("client").getAsJsonObject();
		Long idClient = clientJson.get("id").getAsLong();
		
		try {
			Client client = daoClient.trouver(idClient);
			p.setClient(client);
		} catch (DaoException e) {
			e.printStackTrace();
			System.err.println("erreur dao client");
		}
		
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
