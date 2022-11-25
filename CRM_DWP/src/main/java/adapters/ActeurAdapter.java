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
import daoImpl.DaoFilm;
import models.Acteur;
import models.Film;

public class ActeurAdapter implements JsonSerializer<Acteur>, JsonDeserializer<Acteur> {

	@Override
	public JsonElement serialize(Acteur acteur, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject json = new JsonObject();
		json.addProperty("id", acteur.getId());
		json.addProperty("nom", acteur.getNom());
		json.addProperty("prenom", acteur.getPrenom());
		json.addProperty("age", acteur.getAge());
		
		JsonArray filmsJson = new JsonArray();
		JsonObject tmp;
		for(Film f : acteur.getFilms()) {
			tmp = new JsonObject();
			tmp.addProperty("id", f.getId());
			tmp.addProperty("titre", f.getTitre());
			filmsJson.add(tmp);
		}
				
		json.add("films", filmsJson);
		
		return json;
	}

	@Override
	public Acteur deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		Acteur a = new Acteur();
		
		JsonObject data = json.getAsJsonObject();
		
		String nom = data.get("nom").getAsString();
		a.setNom(nom);

		String prenom = data.get("prenom").getAsString();
		a.setPrenom(prenom);

		String ageString = data.get("age").getAsString();
		int age;
		try {
			age = Integer.parseInt(ageString);
			a.setAge(age);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		DaoFilm daoFilm = new DaoFilm();
		JsonArray filmsArray = data.get("films").getAsJsonArray();
		for(JsonElement filmArray : filmsArray) {
			JsonObject filmJson = filmArray.getAsJsonObject();
			String idFilmString = filmJson.get("id").getAsString();
			Film f = new Film();
			try {
				f = daoFilm.trouver(Long.parseLong(idFilmString));
				a.addFilm(f);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.err.println("id film invalide");
			} catch (DaoException e) {
				e.printStackTrace();
				System.err.println("erreur dao film");
			}
		}

		return a;
	}
}
