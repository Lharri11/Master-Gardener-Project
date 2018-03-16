package servlet;

import model.EmailUtility;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/_view/EmailForm.jsp").forward(req, resp);
    }






    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        // reads form fields
       //String recipient = req.getParameter("recipient");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");

        String resultMessage = "";
        String to = "nsarzyns@ycp.edu";

        try {
            EmailUtility.sendEmail(host, port, user, pass, to, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            req.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/_view/Result.jsp").forward(
                    req, resp);
        }
    }
}
