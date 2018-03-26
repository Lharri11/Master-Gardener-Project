package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Registration;
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

		//Login
		String username = null;
		String password = null;
		String buttonPress = null;
		boolean loggedin = false;
		String errorMessage = null;
		LoginController login = new LoginController();


		buttonPress = req.getParameter("loginSubmit");


		/*//Registration
		String user = null;
		String pass1 = null;
		String pass2 = null;
		String first_name = null;
		String last_name = null;
		String email = null;
		String errorMessageReg = null;
		String buttonPressReg = null;
		buttonPressReg = req.getParameter("register-submit");*/

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
				}  else {
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




		/*if (buttonPressReg != null) {
			System.out.println("pressed");

			user = req.getParameter("usernameReg");
			pass1 = req.getParameter("pass1");
			pass2 = req.getParameter("pass2");
			// TODO: This is left intentionally red so that I know to ask Nick to fix the html
			first_name = req.getParameter("firstname");
			last_name = req.getParameter("lastname");
			email = req.getParameter("email");


			if ("".equals(user) || user == null) {
			    System.out.println("Check User");
				errorMessageReg = "Invalid username, please re-entera";
				System.out.println(errorMessageReg);
				user = null;
				req.setAttribute("errorMessage", errorMessageReg);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

			} else if ("".equals(pass1) || pass1 == null) {
				errorMessageReg = "Invalid password, please re-enter";
				System.out.println(errorMessageReg);
				pass1 = null;
				req.setAttribute("errorMessage", errorMessageReg);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

			} else if ("".equals(pass2) || pass2 == null) {
				errorMessageReg = "Invalid confirmation password, please re-enter";
				System.out.println(errorMessageReg);
				pass2 = null;
				req.setAttribute("errorMessage", errorMessageReg);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

			} else if (!pass2.equals(pass1)) {
				errorMessageReg = "Passwords don't match, please re-enter";
				System.out.println(errorMessageReg);
				pass2 = null;
				req.setAttribute("errorMessage", errorMessageReg);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

			} else if ("".equals(first_name) || first_name == null) {
				errorMessageReg = "Please re-enter first name";
				System.out.println(errorMessageReg);
				first_name = null;
				req.setAttribute("errorMessage", errorMessageReg);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

			} else if ("".equals(last_name) || last_name == null) {
				errorMessageReg = "Please re-enter last name";
				System.out.println(errorMessageReg);
				last_name = null;
				req.setAttribute("errorMessage", errorMessageReg);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

			} else if ("".equals(email) || email == null) {
				errorMessageReg = "Please re-enter email";
				System.out.println(errorMessageReg);
				email = null;
				req.setAttribute("errorMessage", errorMessageReg);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);

			} else {
				User account = new User(user, pass1, -1, first_name, last_name, email, null, new byte[1024]);
				SignupController controller = new SignupController();

				if (controller.createUser(account)) {
					req.setAttribute("account", account);
					req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
				} else {
					errorMessageReg = "Unexpected Error";
					req.setAttribute("errorMessage", errorMessageReg);
					req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
				}
			}

			req.setAttribute("usernameReg", user);
			req.setAttribute("pass1", pass1);
			req.setAttribute("firstname", first_name);
			req.setAttribute("lastname", first_name);
			req.setAttribute("email", email);
		}*/


	}
}
