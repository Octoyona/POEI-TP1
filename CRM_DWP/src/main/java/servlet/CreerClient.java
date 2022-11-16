package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoException;
import dao.DaoFactory;
import model.Adresse;
import model.Client;
import dao.ClientDao;
import dao.AdresseDao;

@WebServlet("/creerClient")
public class CreerClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ClientDao clientDao;
    public CreerClient() {
        super();
        clientDao = DaoFactory.getInstance().getClientDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/creerClient.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> erreurs = new HashMap<String, String>();
		
		AdresseDao adresseDao = DaoFactory.getInstance().getAdresseDao();
		
		String nom = request.getParameter("nomClient");
		String prenom = request.getParameter("prenomClient");
		String telephone = request.getParameter("telephoneClient");
		String nomsociete = request.getParameter("nomSociete");
		String mail = request.getParameter("mailClient");
		Long idAdresse = Long.parseLong(request.getParameter("adresseClient"));
		int genre = Integer.parseInt(request.getParameter("genreClient"));
		int etat = Integer.parseInt(request.getParameter("etatClient"));
		
		
		//Ajout des contrôles
		if(nom != null) {
			if(nom.length() < 2 || nom.length() > 20) {
				erreurs.put("nomClient", "Votre nom doit contenir entre 2 et 20 caractères.");
			}
		} else {
			erreurs.put("nomClient", "Merci d'entrer un nom.");
		}
		
		if(prenom != null) {
			if(prenom.length() > 20) {
				erreurs.put("prenomClient", "Votre prénom doit avoir maximum 20 caractères.");
			} else {
				erreurs.put("prenomClient", "Merci d'entrer un prénom.");
			}
		}
		
		if(telephone != null) {
			if(telephone.length() > 10) {
				erreurs.put("telephoneClient", "Un numéro de téléphone doit avoir maximum 10 caractères.");
			}
			if(!telephone.matches("^\\d+$")) {
				erreurs.put("telephoneClient", "Un numéro de téléphone doit contenir uniquement des chiffres.");
			}
		} else {
			erreurs.put("telephoneClient", "Merci d'entrer un numéro de téléphone.");
		}
		
		if(nomsociete != null) {
			if(nomsociete.length() > 20) {
				erreurs.put("nomSociete", "Le nom de votre société doit avoir maximum 20 caractères.");
			}
		}
			
		if(mail != null) {
			if(mail.length() > 60) {
				erreurs.put("emailClient", "Un email doit avoir maximum 60 caractères.");
			}
			if(!mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				erreurs.put("emailClient", "Merci d'entrer une adresse email valide.");
			}
		}
		
		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setTelephone(telephone);
		client.setMail(mail);
		client.setNom_societe(nomsociete);
		client.setGenre(genre);
		client.setEtat(etat);
		
		try {
			Adresse adresse = adresseDao.trouver(idAdresse);
			client.setAdresse(adresse);
		} catch(DaoException e) {
			e.printStackTrace();
		}
		
		
		
		
		if(erreurs.isEmpty()) {
			try {
				clientDao.creer(client);
				request.getSession().setAttribute("confirmMessage", "Votre compte client a bien été créé !");
				
				response.sendRedirect( request.getContextPath() + "/listeProduits" );
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("client", client);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/creerClient.jsp").forward(request, response);
		}	
	}
}
