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

import com.google.gson.Gson;
import controller.GardenController;
import controller.SearchController;
import controller.UserController;
import model.User;
import model.Garden;
import org.json.JSONObject;

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


		List<Garden> everyGardens = new ArrayList<>();
		SearchController controller1 = new SearchController();

		try {
			everyGardens = controller1.getAllGardens();

		} catch (SQLException e) {
			e.printStackTrace();
		}



		List<Garden> userGardensAvailable = new ArrayList<>();
		SearchController controller2 = new SearchController();

		try {
			userGardensAvailable = controller2.removeUserGardensFromList(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (everyGardens != null && userGardensAvailable != null) {
			for (int i = 0; i < userGardensAvailable.size(); i++) {
				for (int j = 0; j < everyGardens.size(); j++) {
					if(userGardensAvailable.get(i).getGarden_name().equals(everyGardens.get(j).getGarden_name())) {
						everyGardens.remove(j);
					}
				}
			}
		}




		String allGardens = new Gson().toJson(everyGardens);


		System.out.println(allGardens);


		User user = new User();
		controller = new SearchController();
		user = controller.returnUserForUsername(username);


		req.setAttribute("user", user);


		req.setAttribute("allGardens", allGardens);
		req.getRequestDispatcher("/_view/search.jsp").forward(req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {


		//Grab garden name for search page table
		JSONObject garden = new JSONObject(req.getParameter("garden"));
		String gardenChosen = null;
		gardenChosen = garden.getString("garden_name");

		String user = (String)req.getSession().getAttribute("username");


		System.out.println(gardenChosen);
		System.out.println(user);



		if (gardenChosen != null) {
			User account = new User();
			UserController controllerUser = new UserController();
			account = controllerUser.returnUserForUsername(user);

			SearchController controllerSearch = new SearchController();
			try {
				controllerSearch.addNewUsertoGarden(gardenChosen, account.getUsername());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		req.getRequestDispatcher("/_view/search.jsp").forward(req, resp);	
	}
}
