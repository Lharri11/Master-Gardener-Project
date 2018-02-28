/*
This code is a modified version of David Hovemeyer's filters for Pygmy Marmoset.
Source code: https://github.com/daveho/PygmyMarmoset/blob/master/PygmyMarmosetWebapp/src/edu/ycp/cs/pygmymarmoset/app/filter/RequireInstructorOrAdmin.java
Source code includes all licensing and rights of use.
 */

package filter;

import controller.UserController;
import model.User;
import utils.ServletUtil;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter that requires any kind of admin to view a specific page.
 * Example of a page would be the Administration page, except since this allows lower-leveled admins,
 * the page will -NOT- let the admin delete users.
 */
public class RequireAdminOrSuperAdmin extends AbstractLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req_, ServletResponse resp_, FilterChain chain)
            throws IOException, ServletException
    {
        if(!checkLogin(req_, resp_))
        {
            return;
        }
        HttpServletRequest req = (HttpServletRequest) req_;
        HttpServletResponse resp = (HttpServletResponse) resp_;

        String username = req.getSession().getAttribute("username").toString();
        // Get username works!
        //System.out.println("Username = " + username);
        User user = new User();
        user.setUsername(username);
        // Set username works!
        //System.out.println("Username = " + user.getUsername());

        UserController ctrl = new UserController();
        try {
            user.setModeratorStatus(ctrl.getUserModeratorStatus(user.getUsername()));
        }
        catch(SQLException e)
        {
            System.out.println("SQL Error when getting moderator status!");
            e.printStackTrace();
            return;
        }

        if(username.isEmpty())
        {
            throw new ServletException("User is not logged in.");
        }

        // Setting moderator status works!
        //System.out.println("Moderator status: "+user.getModeratorStatus());

        //Ascertain what level of user we're dealing with.
        if(user.getModeratorStatus() != 1 && user.getModeratorStatus() != 2)
        {
            ServletUtil.sendForbidden(req, resp, "Admin or superadmin privileges are required");
            return;
        }

        chain.doFilter(req_, resp_);

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException{
        // nothing to do
    }

    @Override
    public void destroy()
    {
        //nothing to do
    }

}
