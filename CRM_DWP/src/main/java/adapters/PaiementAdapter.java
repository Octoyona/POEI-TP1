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

import daoImpl.Client;
import dao.DaoException;
import model.Client;
import model.Paiement;



public class PaiementAdapter implements JsonSerializer<Paiement>, JsonDeserializer<Paiement> {

	@Override
	public JsonElement serialize(Paiement paiement, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", paiement.getId());
		json.addProperty("numeroCarte", paiement.getNumeroCarte());
		json.addProperty("codeConfidentiel", paiement.getCodeConfidentiel());
		json.addProperty("banque", paiement.getBanque());
		
		JsonObject client = null;
		if(paiement.getClient() != null) {
			client = new JsonObject();
			client.addProperty("id", paiement.getClient().getId());
			client.addProperty("nom", paiement.getClient().getNom());
			client.addProperty("prenom", paiement.getClient().getPrenom());
		}
		json.add("client", client);
		
		return json;
	}

	@Override
	public Paiement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Paiement p = new Paiement();
		
		JsonObject data = json.getAsJsonObject();
		
		String numeroCarte = data.get("numeroCarte").getAsString();
		p.setNumeroCarte(numeroCarte);

		String codeConfidentiel = data.get("codeConfidentiel").getAsString();
		p.setCodeConfidentiel(codeConfidentiel);

		String Banque = data.get("Banque").getAsString();
		p.setBanque(Banque);
		
			DaoClient daoClient = new DaoClient();
			JsonObject client = client.getAsJsonObject();;
			String idClient = client.get("id").getAsString();
			Client c = new Client();
			try {
				c = daoClient.trouver(Long.parseLong(idClient));
				p.setClient(c);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("id client invalide");
			} catch (DaoException e) {
				e.printStackTrace();
				System.err.println("erreur dao client");
			}
		

		return p;
	}
}
