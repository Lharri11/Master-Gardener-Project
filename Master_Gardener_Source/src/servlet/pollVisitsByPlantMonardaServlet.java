package servlet;

import com.google.gson.Gson;
import controller.ChartController;
import model.Pollinator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class pollVisitsByPlantMonardaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ChartController controller = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Initialize a list
        List<Pollinator> pollinators = null;
        List<Integer> visit_counts_didyma_straight = null;
        List<Integer> visit_counts_didyma_petite = null;
        List<Integer> visit_counts_didyma_marshalls = null;
        List<Integer> visit_counts_x_peters = null;

        controller = new ChartController();
        try {
            pollinators = controller.getAllPollinators();
            visit_counts_didyma_straight = controller.getVisitCountsByStrainID(6);
            visit_counts_didyma_petite = controller.getVisitCountsByStrainID(7);
            visit_counts_didyma_marshalls = controller.getVisitCountsByStrainID(8);
            visit_counts_x_peters = controller.getVisitCountsByStrainID(9);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String pollinators_json = new Gson().toJson(pollinators);
        String visit_counts_didyma_straight_json = new Gson().toJson(visit_counts_didyma_straight);
        String visit_counts_didyma_petite_json = new Gson().toJson(visit_counts_didyma_petite);
        String visit_counts_didyma_marshalls_json = new Gson().toJson(visit_counts_didyma_marshalls);
        String visit_counts_x_peters_json = new Gson().toJson(visit_counts_x_peters);

        req.setAttribute("pollinatorsJSON", pollinators_json);
        req.setAttribute("visitCountsDidymaStraightJSON", visit_counts_didyma_straight_json);
        req.setAttribute("visitCountsDidymaPetiteJSON", visit_counts_didyma_petite_json);
        req.setAttribute("visitCountsDidymaMarshallsJSON", visit_counts_didyma_marshalls_json);
        req.setAttribute("visitCountsXPetersJSON", visit_counts_x_peters_json);
        req.getRequestDispatcher("/_view/pollVisitsByPlantMonarda.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/_view/pollVisitsByPlantMonarda.jsp").forward(req, resp);
    }
}