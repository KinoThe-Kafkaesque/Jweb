package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.SalleService;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import beans.Salle;

/**
 * Servlet implementation class SalleController
 */
public class SalleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SalleService ss = new SalleService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalleController() {
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
	//	doGet(request, response);
		if (request.getParameter("op").equals("adds")) {
			String code = request.getParameter("code");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			String type = request.getParameter("type");

			ss.create(new Salle(code, capacity, type));
			response.setContentType("application/json");
			List<Salle> salles = ss.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(salles));

		} else if (request.getParameter("op").equals("edits")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String code = request.getParameter("code");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			String type = request.getParameter("type");

			ss.update(new Salle(id,code, capacity, type));
			response.setContentType("application/json");
			List<Salle> salles = ss.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(salles));

		} else if (request.getParameter("op").equals("dels")) {
			int id = Integer.parseInt(request.getParameter("id"));
			ss.delete(ss.findById(id));
			response.setContentType("application/json");
			List<Salle> salles = ss.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(salles));
		}  else if (request.getParameter("op").equals("load")) {
			response.setContentType("application/json");
			List<Salle> salles = ss.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(salles));

		}
	}

}
