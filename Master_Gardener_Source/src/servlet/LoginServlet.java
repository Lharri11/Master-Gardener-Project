package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LoginController;
import controller.SignupController;
import model.User;
@SuppressWarnings("Duplicates")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try{
			String username = req.getSession().getAttribute("username").toString();
			if(username != null){
				resp.sendRedirect(req.getContextPath() + "/user");
			}
		}
		catch (NullPointerException e){
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = null;
		String password = null;
		String buttonPress = null;
		boolean loggedin = false;
		String errorMessage = null;
		LoginController login = new LoginController();

		buttonPress = req.getParameter("loginSubmit");

		if(buttonPress != null){
			if(buttonPress.toLowerCase().equals("logout")){
				req.getSession().setAttribute("loggedin",false);
				req.getSession().setAttribute("username", null);
				req.getSession().setAttribute("login_id", -1);
				resp.sendRedirect(req.getContextPath()+"/home");
			}
			else if(buttonPress.toLowerCase().equals("login")){

				username = req.getParameter("username");
				password = req.getParameter("password");

				if("".equals(username) || username == null ||
						"".equals(password) || password == null){
					errorMessage = "Invalid username or password.";
				}
				else {
					int loginId = 0;
					try {
						loginId = login.loginUser(username, password);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if(loginId >= 0){
						req.getSession().setAttribute("username", username);
						req.getSession().setAttribute("login_id", loginId);
						loggedin = true;
						req.setAttribute("loggedin", loggedin);
					}
					else{
						errorMessage = "Invalid username or password, please try again..";
					}
				}

				if(loggedin){
					req.setAttribute("account", login.returnUserForUsername(username));
					//req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);

					resp.sendRedirect(req.getContextPath() + "/user");
				}
				else{
					req.setAttribute("username",username);
					req.setAttribute("errorMessage", errorMessage);
					req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
				}
			}
		}

		/*
		String user = null;
		String pass1 = null;
		String pass2 = null;
		String name = null;
		String email = null;
		String bio = null;
		//String errorMessage = null;

		user = req.getParameter("username");
		pass1 = req.getParameter("pass1");
		pass2 = req.getParameter("pass2");
		name = req.getParameter("name");
		email = req.getParameter("email");
		bio = req.getParameter("bio");

		if("".equals(user) || user == null){
			errorMessage = "Invalid username, please re-enter";
			System.out.println(errorMessage);
			user = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		}
		else if("".equals(pass1) || pass1 == null){
			errorMessage = "Invalid password, please re-enter";
			System.out.println(errorMessage);
			pass1 = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		}
		else if("".equals(pass2) || pass2 == null){
			errorMessage = "Invalid confirmation password, please re-enter";
			System.out.println(errorMessage);
			pass2 = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		}
		else if(!pass2.equals(pass1)){
			errorMessage = "Passwords don't match, please re-enter";
			System.out.println(errorMessage);
			pass2 = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		}
		else if("".equals(name) || name == null){
			errorMessage = "Please re-enter name";
			System.out.println(errorMessage);
			name = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		}
		else if("".equals(email) || email == null){
			errorMessage = "Please re-enter email";
			System.out.println(errorMessage);
			email = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		}
		else if("".equals(bio) || bio == null){
			errorMessage = "Please re-enter description";
			System.out.println(errorMessage);
			bio = null;
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
		}
		else{
			User account = new User(user, pass1,-1,name, email,bio, new byte[1024]);
			SignupController controller = new SignupController();

			if(controller.createUser(account)){
				req.setAttribute("account", account);
				req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
			}
			else {
				errorMessage = "Unexpected Error";
				req.setAttribute("errorMessage", errorMessage);
				req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
			}
		}


*/


	}
}
