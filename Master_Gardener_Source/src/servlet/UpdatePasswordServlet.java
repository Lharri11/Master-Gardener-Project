package servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import controller.EditUserController;

public class UpdatePasswordServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String user = (String)req.getSession().getAttribute("username");
        if (user == null) {
            System.out.println("   User: <" + user + "> not logged in or session timed out");

            // User is not logged in, or the session expired
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/_view/updatePassword.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = null, old_password = null, old_pass_match = null, new_password = null, new_pass_match = null;
        String button = null;
        Boolean ignore = false;
        String errorMessage = null;
        button = req.getParameter("EditSubmit");
        if(button != null)
        {
            username = req.getSession().getAttribute("username").toString();
            old_password = req.getParameter("oldPass1");
            old_pass_match = req.getParameter("oldPass2");
            new_password = req.getParameter("newPass1");
            new_pass_match = req.getParameter("newPass2");

            if(!old_password.equals(old_pass_match))
            {
                errorMessage = "Error: Current passwords do not match.";
                ignore = true;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/updatePassword.jsp").forward(req, resp);
            }
            if(!new_password.equals(new_pass_match))
            {
                errorMessage = "Error: New passwords do not match.";
                ignore = true;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/updatePassword.jsp").forward(req, resp);
            }
            if(new_password.equals(old_password))
            {
                errorMessage = "Error: New password is the same as old password.";
                ignore = true;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/updatePassword.jsp").forward(req, resp);
            }
            if(!ignore) {

                EditUserController ctrl = new EditUserController();

                    try {
                        // Try to catch someone using a password tha isn't tied to the username.
                        if(ctrl.checkPasswordByUsername(username, old_password)) {
                            ctrl.updatePassword(username, old_password, new_password);
                            resp.sendRedirect(req.getContextPath() + "/user");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                // Send them an ambiguous error that the current passwords do not match.
                // Hopefully tricks some upstart hacker.
                errorMessage = "Error: Currrent passwords do not match.";
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/updatePassword.jsp").forward(req, resp);
                }
            }
            else
            {
                //resp.sendRedirect(req.getContextPath() + "/updatePassword");
            }
        }
    }

