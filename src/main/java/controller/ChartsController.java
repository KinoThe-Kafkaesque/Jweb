package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OccupationService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;

import beans.Client;
import beans.Occupation;

/**
 * Servlet implementation class ChartsController
 */
public class ChartsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OccupationService os = new OccupationService();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartsController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("op").equals("salle")) {
			response.setContentType("application/json");
			Gson json = new Gson();
			response.getWriter().write(json.toJson(os.mostReserved()));

		}
		else if (request.getParameter("op").equals("monthly")) {
			response.setContentType("application/json");
			Gson json = new Gson();
			response.getWriter().write(json.toJson(os.monthly()));
		}
	}

}
