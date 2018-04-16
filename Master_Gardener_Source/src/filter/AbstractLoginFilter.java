/*
This code is not the direct product of any of the software engineers of the Master Gardener team.
The code is modified from David Hovemeyer's Pygmy Marmoset Github.
Related link: https://github.com/daveho/PygmyMarmoset/blob/master/PygmyMarmosetWebapp/src/edu/ycp/cs/pygmymarmoset/app/filter/AbstractLoginFilter.java
Source code includes all licensing and rights of use.
 */

package filter;
import model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AbstractLoginFilter {

    protected boolean checkLogin(ServletRequest req_, ServletResponse resp_) throws IOException {
        HttpServletRequest req = (HttpServletRequest) req_;
        HttpServletResponse resp = (HttpServletResponse) resp_;

        String username = null;
        try {
            username = req.getSession().getAttribute("username").toString();
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        if (username == null) {
            //redirectToLoginPage(req, (HttpServletResponse) resp_);
            // TODO: MAKE SURE THIS WORKS BECAUSE I DON'T KNOW IF IT DOES
            resp.sendRedirect(req.getContextPath() + "/login");
            return false;
        }
        return true; // user is logged in
    }

    /* For now, this is not necessary.
    protected void redirectToLoginPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // User is not logged in: make a note of goal URL
        // so we can redirect there after successful login.
        // By putting a LoginCredentials object in the
        // session, the Login servlet will be able to see it.
        LoginCredentials creds = new LoginCredentials();
        creds.setGoal(req.getRequestURI());
        req.getSession().setAttribute("creds", creds);
        resp.sendRedirect(req.getContextPath() + "/login");
    }*/
}
