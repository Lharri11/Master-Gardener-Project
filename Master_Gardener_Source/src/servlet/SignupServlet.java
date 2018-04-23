package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.SignupController;
import model.User;


public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String user = null;
		String pass1 = null;
		String pass2 = null;
		String name = null;
		String email = null;
		String bio = null;
		String errorMessage = null;

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
			User account = new User(user, pass1,-1,name, email, bio,0);
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

		req.setAttribute("username", user);
		req.setAttribute("password", pass1);
		req.setAttribute("name", name);
		req.setAttribute("email", email);
		req.setAttribute("bio", bio);
	}
}