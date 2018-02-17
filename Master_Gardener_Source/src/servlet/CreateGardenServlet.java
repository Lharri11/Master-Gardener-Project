package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CreateGardenController;
import model.Garden;
//import controller.LoginController;
import model.Garden;

public class CreateGardenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			req.getRequestDispatcher("/_view/createGarden.jsp").forward(req, resp);	
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name = null;
		String description = null;
		String errorMessage = null;
		int rating = 0;

		name = req.getParameter("gardenname");
		description = req.getParameter("description");
		rating = getIntFromParameter(req.getParameter("rating"));

		if("".equals(name) || name == null){
			errorMessage = "Invalid Garden name, please re-enter";
			System.out.printf("%s", errorMessage);
			name = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/createGarden.jsp").forward(req, resp);
		}
		else if("".equals(description) || description == null){
			errorMessage = "Invalid Description, please re-enter";
			System.out.printf("%s", errorMessage);
			description = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/createGarden.jsp").forward(req, resp);
		}

		else if("".equals(rating) || rating == 0){
			errorMessage = "Invalid Rating, please re-enter";
			System.out.printf("%s", errorMessage);
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/createGarden.jsp").forward(req, resp);
		}
		else{
			Garden garden = new Garden(name, description);
			CreateGardenController controller = null;
			try {
				controller = new CreateGardenController();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(controller.createGarden(garden)){
				req.setAttribute("garden", garden);
				resp.sendRedirect(req.getContextPath() + "/user");
			}
			else{
				errorMessage = "Unexpected Error";
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/_view/createGarden.jsp").forward(req, resp);
			}
		}
		req.setAttribute("gardenname", name);
		req.setAttribute("description", description);
	}
	
	private int getIntFromParameter(String s){
		if(s == null || s.equals("")) {
			return 0;
		} else {
			return Integer.parseInt(s);
		}
	}
}