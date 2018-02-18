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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String username = null, old_password = null, old_pass_match = null, new_password = null, new_pass_match = null;
        String button = null;
        Boolean ignore = false;

        button = req.getParameter("EditSubmit");
        if(button != null)
        {
            username = req.getParameter("username");
            old_password = req.getParameter("oldPass1");
            old_pass_match = req.getParameter("oldPass2");
            new_password = req.getParameter("newPass1");
            new_pass_match = req.getParameter("newPass2");

            if(!old_password.equals(old_pass_match))
            {
                System.out.println("Current passwords do not match.");
                ignore = true;
            }
            if(!new_password.equals(new_pass_match))
            {
                System.out.println("New passwords do not match.");
                ignore = true;
            }

            if(!ignore) {
                EditUserController ctrl = new EditUserController();

                try {
                    ctrl.updatePassword(username, old_password, new_password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect(req.getContextPath() + "/user");
            }
            else
            {
                resp.sendRedirect(req.getContextPath() + "/updatePassword");
            }
        }
    }
}
