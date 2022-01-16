package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClientService;
import service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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

		} else if (request.getParameter("op").equals("login")) {
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			System.out.println(pass);
			User test = us.login(user, pass, request);
			if (test != null) {
				request.getSession().setAttribute("fname", test.getNom());
				request.getSession().setAttribute("lname", test.getPrenom());
				request.getSession().setAttribute("id", test.getId());

				if (test instanceof Client) {
					request.getSession().setAttribute("usertoken", "authenticated");
					request.getSession().setAttribute("role", "client");

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
