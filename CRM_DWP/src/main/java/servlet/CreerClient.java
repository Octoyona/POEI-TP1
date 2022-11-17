package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import form.ClientForm;
import model.Client;

@WebServlet("/creerClient")
public class CreerClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/creerClient.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClientForm clientForm = new ClientForm();
		Client client = new Client();
		
		try {
			client = clientForm.saveClient(request, ClientForm.CREATION);
			request.setAttribute("client", client);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("form", clientForm);
		
		if(!clientForm.isValid()) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/creerClient.jsp").forward(request, response);
		} else {
			response.sendRedirect( request.getContextPath() + "/" );	
		}
		
	}
}
