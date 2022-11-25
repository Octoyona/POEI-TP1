package adapters;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import dao.DaoException;
import daoImpl.DaoClient;
import model.Adresse;
import model.Client;

public class AdresseAdapter implements JsonSerializer<Adresse>, JsonDeserializer<Adresse> {

	@Override
	public JsonElement serialize(Adresse adresse, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", adresse.getId());
		json.addProperty("rue", adresse.getRue());
		json.addProperty("ville", adresse.getVille());
		json.addProperty("code_postal", adresse.getCode_postal());
		json.addProperty("pays", adresse.getPays());
		
		JsonObject client = new JsonObject();
		if(adresse.getClient() != null) {
			client.addProperty("id", adresse.getClient().getId());
			client.addProperty("nom", adresse.getClient().getNom());
			client.addProperty("prenom", adresse.getClient().getPrenom());
		}
		json.add("client", client);
		
		return json;
	}

	@Override
	public Adresse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Adresse a = new Adresse();
		
		JsonObject data = json.getAsJsonObject();
		
		String rue = data.get("rue").getAsString();
		a.setRue(rue);

		String ville = data.get("ville").getAsString();
		a.setVille(ville);
		
		String codePostal = data.get("code_postal").getAsString();
		a.setCode_postal(codePostal);
		
		String pays = data.get("pays").getAsString();
		a.setPays(pays);

		
		DaoClient daoClient = new DaoClient();
		JsonObject clientJson = data.get("client").getAsJsonObject();
		Long idClient = clientJson.get("id").getAsLong();
		
			try {
				Client client = daoClient.trouver(idClient);
				a.setClient(client);
			} catch (DaoException e) {
				e.printStackTrace();
				System.err.println("erreur dao client");
			}
		
		return a;
	}
}
