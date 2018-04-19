package servlet;

import java.io.IOException;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SearchController;
//import controller.UserController;
//import model.User;
import model.Garden;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchController controller = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {


		String username = (String) req.getSession().getAttribute("username");
		if (username == null) {
			System.out.println("User not logged in or session timed out");

			// User is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
				
		//controller = new SearchController();

		/*String keyword;
		keyword = (String) req.getSession().getAttribute("keyword");*/


		List<Garden> gardens = new ArrayList<>();
		SearchController controller = new SearchController();

		try {
			gardens = controller.getAllGardens();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(gardens);

		req.setAttribute("gardens", gardens);
		req.getRequestDispatcher("/_view/search.jsp").forward(req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		int gardenID = 0;
		String buttonPress = req.getParameter("Submit");
		
		if(buttonPress != null){
			SearchController controller = new SearchController();
			gardenID = controller.getGardenIDbyGardenname(buttonPress);
			req.getSession().setAttribute("GardenID", gardenID);
			resp.sendRedirect(req.getContextPath()+"/garden");
			return;
			
		}
		req.getRequestDispatcher("/_view/search.jsp").forward(req, resp);	
	}
}
