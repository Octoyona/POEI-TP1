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
import daoImpl.DaoPanier;
import daoImpl.DaoProduit;
import model.Contient;
import model.Panier;
import model.Produit;

public class ContientAdapter implements JsonSerializer<Contient>, JsonDeserializer<Contient> {

	@Override
	public JsonElement serialize(Contient contient, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", contient.getId());
		
		JsonObject produitJson = new JsonObject();
		if(contient.getProduit() != null) {
			produitJson.addProperty("id", contient.getProduit().getId());
		}
		json.add("produit", produitJson);
		
		JsonObject panierJson = new JsonObject();
		if(contient.getProduit() != null) {
			panierJson.addProperty("id", contient.getProduit().getId());
		}
		json.add("panier", panierJson);
		
		return json;
	}

	@Override
	public Contient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Contient c = new Contient();
		
		JsonObject data = json.getAsJsonObject();
		
		

		
		DaoProduit daoProduit = new DaoProduit();
		JsonObject produitJson = data.get("produit").getAsJsonObject();
		Long idProduit = produitJson.get("id").getAsLong();
		
		try {
			Produit produit = daoProduit.trouver(idProduit);
			c.setProduit(produit);
		} catch (DaoException e) {
			e.printStackTrace();
			System.err.println("erreur dao produit");
		}
		
		DaoPanier daoPanier = new DaoPanier();
		JsonObject panierJson = data.get("panier").getAsJsonObject();
		Long idPanier = panierJson.get("id").getAsLong();
		
			try {
				Panier panier = daoPanier.trouver(idPanier);
				c.setPanier(panier);
			} catch (DaoException e) {
				e.printStackTrace();
				System.err.println("erreur dao panier");
			}
		
		return c;
	}
}
