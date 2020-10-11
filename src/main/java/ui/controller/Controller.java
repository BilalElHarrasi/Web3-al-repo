package ui.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import domain.model.Person;
import domain.db.PersonService;
import java.io.IOException;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L; //GI - !
	private PersonService db = new PersonService();

	/**
	// * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//verwerkRequest(request, response);
		verwerkRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		verwerkRequest(request, response);
	}

	protected void verwerkRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command"); //is er een prmter command? A: ja in de url
		String doel;// doel?????????????????????

		if (command == null || command == "") {
			doel = "index.jsp";
		}

		switch (command) {
		case "Index":
//			doel = toIndex(request, response);
			doel = "index.jsp";
			break;
		case "Overview":
//			doel = toPersonOverview(request, response);
			doel = "personoverview.jsp";
			break;
		case "Register":
//			doel = toRegister(request, response);
			doel = "register.jsp";
			break;
		case "voegToe":
			doel = addPersoon(request, response);
			break;
		default:
//			doel = toIndex(request, response);
			doel = "index.jsp";
		}

		RequestDispatcher view = request.getRequestDispatcher(doel);
		view.forward(request, response);
	}

	//____________________index____________________//

	private String toIndex(HttpServletRequest request, HttpServletResponse response){
		return "index.jsp";
	}

	//____________________TOON Persons____________________//

	private String toPersonOverview(HttpServletRequest request, HttpServletResponse response) {
		return "personoverview.jsp";
	}

	//____________________TOON Register____________________//

	private String toRegister(HttpServletRequest request, HttpServletResponse response) {
		return "register.jsp";
	}

	//____________________TOON Register____________________//

	private String addPersoon(HttpServletRequest request, HttpServletResponse response) {
		Person s = new Person();
		ArrayList<String> errors = new ArrayList<>();

		setUserid(s, errors, request);
		setFirstName(s, errors, request);
		setLastName	(s, errors, request);
		setEmail(s, errors, request);
		setPassword(s, errors, request);

		if(errors.isEmpty()) {
			db.add(s);
			return "index.jsp";
		}
		else {
			request.setAttribute("errors", errors);
			return "index.jsp";
		}
	}

	private void setUserid(Person p, ArrayList<String> errors, HttpServletRequest request) {

		try {
			p.setUserid(request.getParameter("userid"));
			request.setAttribute("userid", request.getParameter("userid"));

		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
	}


	private void setFirstName(Person p, ArrayList<String> errors, HttpServletRequest request) {
		//String serie = request.getParameter("serie");

		try {
			p.setFirstName(request.getParameter("firstName"));
			request.setAttribute("firstName", request.getParameter("firstName"));

		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
	}

	private void setLastName(Person p, ArrayList<String> errors, HttpServletRequest request) {
		try {
			p.setLastName(request.getParameter("lastName"));
			request.setAttribute("lastName", request.getParameter("lastName"));

		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
	}

	private void setEmail(Person p, ArrayList<String> errors, HttpServletRequest request) {
		try {
			p.setEmail(request.getParameter("email"));
			request.setAttribute("email", request.getParameter("email"));

		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}
	}

		private void setPassword(Person p, ArrayList<String> errors, HttpServletRequest request) {
			try {
				p.setPassword(request.getParameter("password"));
				request.setAttribute("password", request.getParameter("password"));

			} catch (IllegalArgumentException e) {
				errors.add(e.getMessage());
			}
		}
}