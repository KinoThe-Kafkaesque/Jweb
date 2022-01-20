package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.AdminService;
import service.ClientService;
import service.CrenonService;
import service.OccupationService;
import service.SalleService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;

import beans.Admin;
import beans.Client;
import beans.Occupation;

/**
 * Servlet implementation claos OcuppationController
 */
public class OcuppationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OccupationService os = new OccupationService();
	SalleService ss = new SalleService();
	ClientService us = new ClientService();
	CrenonService cs = new CrenonService();
	AdminService as = new AdminService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OcuppationController() {
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
		if (!request.getSession().getAttribute("role").equals("admin")) {
			int client =  Integer.parseInt(request.getSession().getAttribute("id").toString());
			System.out.println(client);
			if (request.getParameter("op").equals("addo")) {

				int salle = Integer.parseInt(request.getParameter("salle"));
				int crenon = Integer.parseInt(request.getParameter("creneau"));
				LocalDate date = LocalDate.parse(request.getParameter("date"));

				os.create(new Occupation(date, ss.findById(salle), cs.findById(crenon) ,us.findById(client), null));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findByclient(client);

				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));

			} else if (request.getParameter("op").equals("edito")) {
				int salle = Integer.parseInt(request.getParameter("salle"));
				int crenon = Integer.parseInt(request.getParameter("creneau"));
				System.out.println(client);
				LocalDate date = LocalDate.parse(request.getParameter("date"));
				os.update(new Occupation(date, ss.findById(salle), cs.findById(crenon), us.findById(client) , null));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findByclient(client);

				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));

			} else if (request.getParameter("op").equals("delo")) {
				int id = Integer.parseInt(request.getParameter("id"));
				os.delete(os.findById(id));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findByclient(client);

				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));
			} else if (request.getParameter("op").equals("load")) {
				response.setContentType("application/json");
				List<Occupation> occupations = os.findByclient(client);
				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));
			} else if (request.getParameter("op").equals("search")) {

				int salle = Integer.parseInt(request.getParameter("salle"));
				LocalDate date = LocalDate.parse(request.getParameter("date"));

				response.setContentType("application/json");
				List<Occupation> occupations = os.findBySalleAndDate(ss.findById(salle), date);

				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));

			}
		} else {
			int admin =  Integer.parseInt(request.getSession().getAttribute("id").toString());
			if (request.getParameter("op").equals("addo")) {
				int salle = Integer.parseInt(request.getParameter("salle"));
				int crenon = Integer.parseInt(request.getParameter("creneau"));
				int client = (int) request.getSession().getAttribute("id");

				System.out.println(client);
				LocalDate date = LocalDate.parse(request.getParameter("date"));

				os.create(new Occupation(date, ss.findById(salle), cs.findById(crenon), us.findById(client), null));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));

			} else if (request.getParameter("op").equals("edito")) {
				int salle = Integer.parseInt(request.getParameter("salle"));
				int crenon = Integer.parseInt(request.getParameter("creneau"));
				int client = (int) request.getSession().getAttribute("id");
				System.out.println(client);
				LocalDate date = LocalDate.parse(request.getParameter("date"));
				os.update(new Occupation(date, ss.findById(salle), cs.findById(crenon), us.findById(client) , null));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));

			} else if (request.getParameter("op").equals("delo")) {
				int id = Integer.parseInt(request.getParameter("id"));
				os.delete(os.findById(id));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));
			} else if (request.getParameter("op").equals("verify")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Occupation o = os.findById(id);
				os.verify(id, as.findById(admin));
				response.setContentType("application/json");
				List<Occupation> occupations = os.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));
			} else if (request.getParameter("op").equals("unverify")) {
				int id = Integer.parseInt(request.getParameter("id"));
				os.unverify(id);
				response.setContentType("application/json");
				List<Occupation> occupations = os.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));
			} else if (request.getParameter("op").equals("load")) {
				response.setContentType("application/json");
				List<Occupation> occupations = os.findAll();

				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));

			} else if (request.getParameter("op").equals("search")) {

				int salle = Integer.parseInt(request.getParameter("salle"));
				LocalDate date = LocalDate.parse(request.getParameter("date"));

				response.setContentType("application/json");
				List<Occupation> occupations = os.findBySalleAndDate(ss.findById(salle), date);

				Gson json = new Gson();
				response.getWriter().write(json.toJson(occupations));

			}

		}
	}

}
