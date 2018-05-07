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

public class pollVisitsByPlantCoreopsisServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ChartController controller = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Initialize a list
        List<Pollinator> pollinators = null;
        List<Integer> visit_counts_straight = null;
        List<Integer> visit_counts_moonbeam = null;
        List<Integer> visit_counts_zagreb = null;
        List<Integer> visit_counts_creme = null;
        List<Integer> visit_counts_route = null;

        controller = new ChartController();
        try {
            pollinators = controller.getAllPollinators();
            visit_counts_straight = controller.getVisitCountsByPollinatorAndStrainID(1);
            visit_counts_moonbeam = controller.getVisitCountsByPollinatorAndStrainID(2);
            visit_counts_zagreb = controller.getVisitCountsByPollinatorAndStrainID(3);
            visit_counts_creme = controller.getVisitCountsByPollinatorAndStrainID(4);
            visit_counts_route = controller.getVisitCountsByPollinatorAndStrainID(5);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int total = 0;
        for(int i = 0; i < visit_counts_straight.size(); i++) {
            total += visit_counts_straight.get(i);
            total += visit_counts_moonbeam.get(i);
            total += visit_counts_zagreb.get(i);
            total += visit_counts_creme.get(i);
            total += visit_counts_route.get(i);
        }

        String pollinators_json = new Gson().toJson(pollinators);
        String visit_counts_straight_json = new Gson().toJson(visit_counts_straight);
        String visit_counts_moonbeam_json = new Gson().toJson(visit_counts_moonbeam);
        String visit_counts_zagreb_json = new Gson().toJson(visit_counts_zagreb);
        String visit_counts_creme_json = new Gson().toJson(visit_counts_creme);
        String visit_counts_route_json = new Gson().toJson(visit_counts_route);

        req.setAttribute("pollinatorsJSON", pollinators_json);
        req.setAttribute("visitCountsStraightJSON", visit_counts_straight_json);
        req.setAttribute("visitCountsMoonbeamJSON", visit_counts_moonbeam_json);
        req.setAttribute("visitCountsZagredJSON", visit_counts_zagreb_json);
        req.setAttribute("visitCountsCremeJSON", visit_counts_creme_json);
        req.setAttribute("visitCountsRouteJSON", visit_counts_route_json);
        req.setAttribute("visitCountsTotal", total);
        req.getRequestDispatcher("/_view/pollVisitsByPlantCoreopsis.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/_view/pollVisitsByPlantCoreopsis.jsp").forward(req, resp);
    }
}