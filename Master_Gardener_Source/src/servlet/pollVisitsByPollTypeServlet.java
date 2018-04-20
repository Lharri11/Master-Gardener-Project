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
        List<Integer> visit_counts = null;

        controller = new ChartController();
        try {
            pollinators = controller.getAllPollinators();
            visit_counts = controller.getVisitCountsByPollinator();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int total = 0;
        for(int i = 0; i < visit_counts.size(); i++) {
            total += visit_counts.get(i);
        }

        // Convert the object to a JSON string and pass it to the JSP as hidden input via request attribute
        String pollinators_json = new Gson().toJson(pollinators);
        String visit_counts_json = new Gson().toJson(visit_counts);
        req.setAttribute("pollinatorsJSON", pollinators_json);
        req.setAttribute("visitCountsJSON", visit_counts_json);
        req.setAttribute("visitCountsTotal", total);


        req.getRequestDispatcher("/_view/pollVisitsByPollType.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/_view/pollVisitsByPollType.jsp").forward(req, resp);
    }
}