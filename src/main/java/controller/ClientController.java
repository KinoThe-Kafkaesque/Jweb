package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ClientService;
import service.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;

import beans.Admin;
import beans.Client;
import beans.User;

/**
 * Servlet implementation class ClientController
 */
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientService cs = new ClientService();
	private UserService us = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		if (request.getParameter("op").equals("register")) {
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String recuperation = request.getParameter("recuperation");
			System.out.println(fname);

			cs.create(new Client(fname, lname, email, pass, recuperation, LocalDateTime.now()));
			response.setContentType("application/json");

		} else if (request.getParameter("op").equals("fpass")) {
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			String r = request.getParameter("recuperation");
			String rec = Hashing.sha256().hashString(r, StandardCharsets.UTF_8).toString();
			User c = cs.findByUser(user);
			String message ="";
			if(c != null ) {
				if(c.getRecupertation().equals(rec) ) {
					c.setPassword(pass);
					cs.changePass(c);	
					  message = "mot de passe modifier avec succes";

				}
				else{
					  message = " reponse secrete invalide";
					 
				}
			}
			else{
				  message = " nom d'utilisateur invalide";
	                request.setAttribute("message", message);
			}
			response.setContentType("application/json");
			Gson json = new Gson();
			response.getWriter().write(json.toJson(message));
			

		} else if (request.getParameter("op").equals("login")) {
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			System.out.println(pass);
			User test = us.login(user, pass, request);
			if (test != null) {
				
				request.getSession().setAttribute("fname", test.getNom());
				request.getSession().setAttribute("lname", test.getPrenom());
				request.getSession().setAttribute("id", ""+test.getId());
				System.out.println(test.getId());

				if (test instanceof Client) {
					request.getSession().setAttribute("usertoken", "authenticated");
					request.getSession().setAttribute("role", "client");
					request.getSession().setAttribute("id", ""+test.getId());



				} else if (test instanceof Admin) {
					request.getSession().setAttribute("usertoken", "authenticated");
					request.getSession().setAttribute("role", "admin");

				}
			}

		} else if (request.getParameter("op").equals("load")) {
			response.setContentType("application/json");
			List<User> clients = us.findAllClients();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(clients));
		} else if (request.getParameter("op").equals("search")) {
			response.setContentType("application/json");
			List<Client> clients = us.searchClients(request.getParameter("o"));
			Gson json = new Gson();
			response.getWriter().write(json.toJson(clients));
		}

	}

}
