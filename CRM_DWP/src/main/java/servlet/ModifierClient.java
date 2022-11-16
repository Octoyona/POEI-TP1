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
import model.Client;

/**
 * Servlet implementation class ModifierClient
 */
@WebServlet("/modifierClient")
public class ModifierClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDao clientDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierClient() {
        super();
        ClientDao = DaoFactory.getInstance().ClientDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			long id = Long.parseLong(request.getParameter("id"));
			request.setAttribute("client", ClientDao.trouver(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierClient.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> erreurs = new HashMap<String, String>();
		
		String nom = request.getParameter("nomClient");
		String prenom = request.getParameter("prenomClient");
		String telephone = request.getParameter("telephoneClient");
		String nomsociete = request.getParameter("nomSociete");
		String email = request.getParameter("emailClient");
		Long idAdresse = Long.parseLong(request.getParameter("AdresseClient"));
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
					
				if(email != null) {
					if(email.length() > 60) {
						erreurs.put("emailClient", "Un email doit avoir maximum 60 caractères.");
					}
					if(!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
						erreurs.put("emailClient", "Merci d'entrer une adresse email valide.");
					}
				}
				
				
		Client client = new Client();	
		try {
			long id = Long.parseLong(request.getParameter("id"));
			client = clientDao.trouver(id);
		}catch (DaoException | NumberFormatException e) {
			e.printStackTrace();
			erreurs.put("client", "Erreur ce client n'existe pas...");
		}
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setTelephone(telephone);
		client.setMail(email);
		client.setNom_societe(nomsociete);
		client.setAdresses(AdresseDao.trouver(idAdresse));
		client.setGenre(genre);
		client.setEtat(etat);
		
		if(erreurs.isEmpty()) {
			try {
				clientDao.update(client);
				
				//Ajout d'un élément dans la session
				request.getSession().setAttribute("confirmMessage", "Vos informations ont bien été modifiées!");
				
				response.sendRedirect( request.getContextPath() + "/detailsClient" );
			} catch (DaoException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("client", client);
			request.setAttribute("erreurs", erreurs);

			this.getServletContext().getRequestDispatcher("/WEB-INF/modifierClient.jsp").forward(request, response);
		}	
	}

}
