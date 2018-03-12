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
        String first_name = null;
        String last_name = null;
        String email = null;
        String bio = null;
        String errorMessage = null;
        String buttonPress = null;
        buttonPress = req.getParameter("createUserSubmitOld");


        if (buttonPress != null) {
            System.out.println("pressed");

            user = req.getParameter("username");
            pass1 = req.getParameter("pass1");
            pass2 = req.getParameter("pass2");
            // TODO: This is left intentionally red so that I know to ask Nick to fix the html
            first_name = req.getParameter("firstname");
            last_name = req.getParameter("lastname");
            email = req.getParameter("email");
            bio = req.getParameter("bio");


            if ("".equals(user) || user == null) {
                errorMessage = "Invalid username, please re-enter";
                System.out.println(errorMessage);
                user = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else if ("".equals(pass1) || pass1 == null) {
                errorMessage = "Invalid password, please re-enter";
                System.out.println(errorMessage);
                pass1 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else if ("".equals(pass2) || pass2 == null) {
                errorMessage = "Invalid confirmation password, please re-enter";
                System.out.println(errorMessage);
                pass2 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else if (!pass2.equals(pass1)) {
                errorMessage = "Passwords don't match, please re-enter";
                System.out.println(errorMessage);
                pass2 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else if ("".equals(first_name) || first_name == null) {
                errorMessage = "Please re-enter first name";
                System.out.println(errorMessage);
                first_name = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else if ("".equals(last_name) || last_name == null) {
                errorMessage = "Please re-enter last name";
                System.out.println(errorMessage);
                last_name = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else if ("".equals(email) || email == null) {
                errorMessage = "Please re-enter email";
                System.out.println(errorMessage);
                email = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else if ("".equals(bio) || bio == null) {
                errorMessage = "Please re-enter description";
                System.out.println(errorMessage);
                bio = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
            } else {
                User account = new User(user, pass1, -1, first_name, last_name, email, bio, new byte[1024]);
                SignupController controller = new SignupController();

                if (controller.createUser(account)) {
                    req.setAttribute("account", account);
                    req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
                } else {
                    errorMessage = "Unexpected Error";
                    req.setAttribute("errorMessage", errorMessage);
                    req.getRequestDispatcher("/_view/signup.jsp").forward(req, resp);
                }
            }

            req.setAttribute("username", user);
            req.setAttribute("password", pass1);
            req.setAttribute("firstname", first_name);
            req.setAttribute("lastname", first_name);
            req.setAttribute("email", email);
            req.setAttribute("bio", bio);
        }
    }
}