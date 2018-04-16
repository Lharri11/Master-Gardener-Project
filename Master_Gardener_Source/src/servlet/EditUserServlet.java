package servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.EditUserController;

//@MultipartConfig
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = (String)req.getSession().getAttribute("username");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");

			// User is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// TODO: fix to go to edit account tab on user page
		req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = (String)req.getSession().getAttribute("username");
		String newusername = null;
		String newlastname = null;
		String bio = null;
		String button = null;
		button = req.getParameter("EditSubmit");

		if(button != null){
			newusername = req.getParameter("editName");
			newlastname = req.getParameter("lastname");
			bio = req.getParameter("editBio");

			EditUserController controller = new EditUserController();
			//controller.updateUserbyUsername(username , newusername, bio, inputStream);
			controller.updateUserCredentialsbyUsername(username, newusername, newlastname, bio);
			req.getSession().setAttribute("username", newusername);

			//resp.sendRedirect(req.getContextPath() + "/user");
			//req.getRequestDispatcher("/_view/user.jsp").forward(req, resp);
			req.getSession().setAttribute("loggedin", false);
			req.getSession().setAttribute("username", null);
			req.getSession().setAttribute("login_id", -1);
			resp.sendRedirect(req.getContextPath()+"/login");

		}
	}
}


