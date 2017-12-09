package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.GardenController;
import controller.GardenMemberController;
//import controller.SignupController;
import controller.UserController;
import model.*;
import model.User;
@SuppressWarnings("Duplicates")
public class GardenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GardenController controller = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String username = (String)req.getSession().getAttribute("username");
		if (username == null) {
			System.out.println("User not logged in or session timed out");

			// User is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		Garden garden = null;
		List<Garden> user_gardens = null;
		List<Pair<User, Post>> posts = null;
		controller = new GardenController();
		int thisgarden = 0;
		thisgarden = (Integer)req.getSession().getAttribute("GardenID");
		try {
			if(thisgarden == 0){
				System.out.println("No garden found");
			}
			else{
				garden = controller.getGardenbyID(thisgarden);
				posts = controller.getPostsbyGardenID(thisgarden);
				user_gardens = controller.getUsersGardens(username);
			}
		} catch (SQLException e) {
			 e.printStackTrace();
		}
		
		User user = new User();
		controller = new GardenController();
		user = controller.returnUserForUsername(username);
		
		req.setAttribute("garden", garden);
		req.setAttribute("user_gardens", user_gardens);
		req.setAttribute("posts", posts);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/_view/garden.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String button = null;
		String post = null;
		
		button = req.getParameter("postSubmit");
		
		if(button != null){
			GardenController controller = new GardenController();
			post = req.getParameter("newPost");
			String user = (String)req.getSession().getAttribute("username");
			int thisgarden = (int) req.getSession().getAttribute("GardenID");
			controller.addPost(post, thisgarden, user);
			Garden garden = new Garden(null, null);
			try {
				garden = controller.getGardenbyID(thisgarden);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			req.setAttribute("garden", garden);
			List<Pair<User, Post>> posts = controller.getPostsbyGardenID(thisgarden);
			req.setAttribute("posts", posts);
			req.setAttribute("user", req.getAttribute("user"));
			req.getRequestDispatcher("/_view/garden.jsp").forward(req, resp);
		}
		
		String button2 = null;
		
		button2 = req.getParameter("memberSubmit");
		
		if(button2 != null){
			int thisgarden = (int) req.getSession().getAttribute("GardenID");
			String user = (String)req.getSession().getAttribute("username");
		
			User account = new User();
			UserController controller = new UserController();
			account = controller.returnUserForUsername(user);
		
			GardenMember gardenMember = new GardenMember(thisgarden, account.getUserId());
			GardenMemberController controller2 = new GardenMemberController();
		
			if(controller2.addGardenMember(gardenMember)){
				resp.sendRedirect(req.getContextPath()+"/user");
			}
		}
		
		int gardenID = 0;
		UserController controller3 = new UserController();
		String buttonPress = req.getParameter("Submit");
		
		if(buttonPress != null){
			try{
				gardenID = controller3.getGardenIDbyGardenname(buttonPress);
				req.getSession().setAttribute("GardenID", gardenID);
				if(gardenID == 0){
					resp.sendRedirect(req.getContextPath()+"/user");
				}
				else{
					resp.sendRedirect(req.getContextPath()+"/garden");
				}
				return;
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
