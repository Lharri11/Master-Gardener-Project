package servlet;

import controller.AdminController;
import model.PollinatorDataForm;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        /*String user = (String) req.getSession().getAttribute("username");
        if (user == null) {
            System.out.println("User not logged in or session timed out");
            // User is not logged in, or the session expired
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }*/

        List<User> activeAccounts = new ArrayList<>();
        AdminController controller = new AdminController();
        try {
            activeAccounts = controller.getAllUsernames();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<PollinatorDataForm> unconfirmed = new ArrayList<>();
        AdminController controller1 = new AdminController();
        controller1 = new AdminController();
        try {
            unconfirmed = controller1.getAllUnconfirmedDataForm();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        System.out.println(activeAccounts);

        // Debug output
        for (int i = 0; i < unconfirmed.size(); i++) {
            // Static info
            System.out.println("\nPrinting static info: \n");
            System.out.println("Dataform ID:"+ unconfirmed.get(i).getData_form_id());
            System.out.println("Temp: "+ unconfirmed.get(i).getTemperature());
            System.out.println("D Collected: "+ unconfirmed.get(i).getDate_collected());
            System.out.println("D Generated: "+unconfirmed.get(i).getDate_generated());
            System.out.println("D confirmed: "+unconfirmed.get(i).getDate_confirmed());
            System.out.println("Mon Start: " + unconfirmed.get(i).getMonitor_start());
            System.out.println("Mon Stop: "+unconfirmed.get(i).getMonitor_stop());
            System.out.println("Wind: "+ unconfirmed.get(i).getWind_status());
            System.out.println("Cloud: " +unconfirmed.get(i).getCloud_status());
            System.out.println("Comments: " +unconfirmed.get(i).getComments());
            System.out.println("But-Moth Comments: " +unconfirmed.get(i).getButterflyMothComments());
            System.out.println("Garden: " + unconfirmed.get(i).getGarden_name());
            System.out.println("Done printing static info. IT'S GOOD TO GO! \n\nPrinting variable info: \n");
            // Variable info
            // In theory, should be able to use any of plant_name, poll_name, strain name or visit counts for this loop variable
            // as they should all be 1:1
            for(int j = 0; j < unconfirmed.get(i).getPlant_names().size(); j++) {
                System.out.println("Plant name: "+unconfirmed.get(i).getPlant_names().get(j));
                System.out.println("Poll name: "+unconfirmed.get(i).getPollinator_names().get(j));
                System.out.println("Strain name: " +unconfirmed.get(i).getStrain_names().get(j));
                System.out.println("Visit count: "+unconfirmed.get(i).getVisit_counts().get(j));
                System.out.println("\n/END ONE TUPLE OF VARIABLE DATA/\n");
            }

            System.out.println("/END ONE PDF/");
        }

        System.out.println("Bad debug output is bad");
        req.setAttribute("activeAccounts", activeAccounts);
        req.setAttribute("unconfirmed", unconfirmed);
        req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = null;
        String pass1 = null;
        String pass2 = null;
        String firstname = null;
        String lastname = null;
        String email = null;
        String bio = null;
        String buttonPress1 = null;
        String buttonPress2 = null;
        String errorMessage = null;
        String errorMessage2 = null;

        buttonPress1 = req.getParameter("userCreate");
        buttonPress2 = req.getParameter("userDelete");

        if(buttonPress1 != null){
            username = req.getParameter("userName");
            pass1 = req.getParameter("pass1");
            pass2 = req.getParameter("pass2");
            firstname = req.getParameter("firstname");
            lastname = req.getParameter("lastname");
            email = req.getParameter("email");

            if ("".equals(username) || username == null) {
                errorMessage = "Invalid username, please re-enter";
                System.out.println(errorMessage);
                username = null;
                req.setAttribute("errorMessage", errorMessage);
                resp.sendRedirect(req.getContextPath()+"/admin");
            } else if ("".equals(pass1) || pass1 == null) {
                errorMessage = "Invalid password, please re-enter";
                System.out.println(errorMessage);
                pass1 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
            } else if ("".equals(pass2) || pass2 == null) {
                errorMessage = "Invalid confirmation password, please re-enter";
                System.out.println(errorMessage);
                pass2 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
            } else if (!pass2.equals(pass1)) {
                errorMessage = "Passwords don't match, please re-enter";
                System.out.println(errorMessage);
                pass2 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
            } else if ("".equals(firstname) || firstname == null) {
                errorMessage = "Please re-enter name";
                System.out.println(errorMessage);
                firstname = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
            } else if ("".equals(lastname) || lastname == null) {
                errorMessage = "Please re-enter name";
                System.out.println(errorMessage);
                lastname = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
            } else if ("".equals(email) || email == null) {
                errorMessage = "Please re-enter email";
                System.out.println(errorMessage);
                email = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
            } else {
                User user = new User(username, pass1, -1, firstname, lastname, email,"[Description]");
                AdminController controller = new AdminController();

                if (controller.createUser(user)) {
                    req.setAttribute("account", user);
                    System.out.println("User Created");
                    req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
                } else {
                    errorMessage = "Unexpected Error";
                    req.setAttribute("errorMessage", errorMessage);
                    resp.sendRedirect(req.getContextPath()+"/admin");
                }
            }
        }
        if(buttonPress2 != null){
            AdminController controller = new AdminController();
            username = req.getParameter("username2");
            User user = controller.findUserByUsername(username);

            try {
                if (controller.deleteUserByUsername(user)) {
                    System.out.println("User Deleted");
                    resp.sendRedirect(req.getContextPath()+"/admin");
                    //req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
                } else {
                    errorMessage2 = "Unexpected Error";
                    req.setAttribute("errorMessage", errorMessage2);
                    req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




    }
}