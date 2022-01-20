package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.CrenonService;
import service.SalleService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.google.gson.Gson;

import beans.Admin;
import beans.Client;
import beans.Crenon;
import beans.Salle;
import beans.User;

/**
 * Servlet implementation class CrenonController
 */
public class CrenonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CrenonService cs = new CrenonService();
	SalleService ss = new SalleService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrenonController() {
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
		doGet(request, response);

		if (request.getParameter("op").equals("addc")) {
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			System.out.println(start);
			cs.create(new Crenon(LocalTime.parse(start), LocalTime.parse(end)));
			response.setContentType("application/json");
			List<Crenon> crenons = cs.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(crenons));

		} else if (request.getParameter("op").equals("delc")) {
			int id = Integer.parseInt(request.getParameter("id"));
			cs.delete(cs.findById(id));
			response.setContentType("application/json");
			List<Crenon> crenons = cs.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(crenons));
		} else if (request.getParameter("op").equals("load")) {
			response.setContentType("application/json");
			List<Crenon> crenons = cs.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(crenons));

		}
		 else if (request.getParameter("op").equals("filter")) {
				response.setContentType("application/json");
				LocalDate date = LocalDate.parse(request.getParameter("date"));
				int salle = Integer.parseInt(request.getParameter("salle"));
				List<Crenon> crenons = cs.findBySalleAndDate(ss.findById(salle), date);
				Gson json = new Gson();
				response.getWriter().write(json.toJson(crenons));

			}

	}
}
