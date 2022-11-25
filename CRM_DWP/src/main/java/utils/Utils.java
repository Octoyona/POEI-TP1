package utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import adapters.ActeurAdapter;
import adapters.AdresseAdapter;

import model.Adresse;


public class Utils {
	public static Gson getSuperJson() {
		GsonBuilder gsonBuilder = new GsonBuilder()
				.registerTypeAdapter(Adresse.class, new AdresseAdapter())
				.serializeNulls();
				/*.registerTypeAdapter(Scenario.class, new ScenarioAdapter())
				.registerTypeAdapter(Realisateur.class, new RealisateurAdapter());*/
		Gson gson = gsonBuilder.create();
		return gson;
	}
	
	public static JsonElement getJsonFromBuffer(HttpServletRequest request) throws IOException {
		StringBuffer buffer = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while((line = reader.readLine())!=null) {
			buffer.append(line);
		}
		
		JsonElement json = JsonParser.parseString(buffer.toString());
		return json;
	}
	
	public static String getStringParameter (JsonObject data, String nameField, int minLength, int maxLength) throws ServiceException {
		String parameter = null;
		
		if(data.get(nameField) != null && !data.get(nameField).isJsonNull()) {
			parameter = data.get(nameField).getAsString().trim();
			
			if(parameter.length()<minLength) {
				throw new ServiceException("Le champ " + nameField + "doit contenir au minimum " + minLength + "caractères.");
			}
			
			if(parameter.length()>maxLength) {
				throw new ServiceException("Le champ " + nameField + "doit contenir au maximum " + maxLength + "caractères.");
			}
		}
		
		return parameter;
	}
	
	public static String getStringParameter (JsonObject data, String nameField, int minLength, int maxLength, String regexFormat) throws ServiceException {
		String parameter = getStringParameter(data, nameField, minLength, maxLength);
		
		if(parameter != null) {
			if(!parameter.matches(regexFormat)) {
				throw new ServiceException("Le champ " + nameField + "n'est pas au bon format.");
			}
		}
		
		return parameter;
	}
}
