package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClientService;
import service.UserService;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import beans.Client;
import beans.User;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClientService cs = new ClientService();
	UserService us = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("pass")) {
			String pass = request.getParameter("pass");
			String user = request.getParameter("user");
			User c = cs.findByUser(user);
			c.setPassword(pass);
			cs.changePass(c);

		} else if (request.getParameter("op").equals("ban")) {
			String id = request.getParameter("user");
			us.ban(cs.findByUser(id));
			response.setContentType("application/json");
			List<User> clients = us.findAllClients();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(clients));
		} else if (request.getParameter("op").equals("unban")) {
			String id = request.getParameter("user");
			us.unban(cs.findByUser(id));
			response.setContentType("application/json");
			List<User> clients = us.findAllClients();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(clients));
		}
		else if (request.getParameter("op").equals("delu")) {
			int id = Integer.parseInt(request.getParameter("id"));
			cs.delete(cs.findById(id));
			response.setContentType("application/json");
			List<User> clients = us.findAllClients();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(clients));
		}
	}

}
