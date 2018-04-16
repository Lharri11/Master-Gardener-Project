package servlet;

import com.google.gson.Gson;
import controller.ChartController;
import model.Garden;
import model.Pollinator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class pollVisitsByPollTypeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ChartController controller = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Initialize a list
        List<Pollinator> pollinators = null;

        controller = new ChartController();
        try {
            pollinators = controller.getAllPollinators();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the object to a JSON string and pass it to the JSP via hidden input
        String json = new Gson().toJson(pollinators);
        req.setAttribute("pollinatorJSON", json);

        req.getRequestDispatcher("/_view/pollVisitsByPollType.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/_view/pollVisitsByPollType.jsp").forward(req, resp);
    }
}