package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import controller.LoginController;
import com.google.gson.Gson;
import controller.AdminController;
import controller.UserController;
import model.User;
import model.Garden;

@SuppressWarnings("Duplicates")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserController controller = null;

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

		List<Garden> gardens = null;
		controller = new UserController();
		try {
			gardens = controller.getUsersGardens(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String userGardens = new Gson().toJson(gardens);
		req.setAttribute("userGardens", userGardens);

		User user = new User();
		controller = new UserController();
		user = controller.returnUserForUsername(username);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int gardenID = 0;
		int portraitLength = 0;

		String username = req.getSession().getAttribute("username").toString();
		User user = new User();
		controller = new UserController();
		user = controller.returnUserForUsername(username);

		String buttonPress = req.getParameter("Submit");
		String keyword = req.getParameter("keyword");

		if(buttonPress != null){
			try{
				gardenID = controller.getGardenIDbyGardenname(buttonPress);
				req.getSession().setAttribute("GardenID", gardenID);
				resp.sendRedirect(req.getContextPath()+"/garden");
				return;
			}catch (SQLException e){
				e.printStackTrace();
			}
		}

		if(keyword != null){
			req.getSession().setAttribute("keyword", keyword);
			resp.sendRedirect(req.getContextPath()+"/search");
			return;
		}

		req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);
	}
}
