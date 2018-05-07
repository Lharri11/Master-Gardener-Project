package servlet;

import com.google.gson.Gson;
import controller.ChartController;
import model.County;
import model.Pollinator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class pollVisitsByCountyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ChartController controller = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<County> counties = null;
        List<Pollinator> pollinators = null;
        controller = new ChartController();
        try {
            counties = controller.getAllCounties();
            pollinators = controller.getAllPollinators();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i=0; i < counties.size(); i++){

            List<Integer> visit_counts = null;

            controller = new ChartController();
            try {
                visit_counts = controller.getVisitCountsByPollinatorAndCountyID(i);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            int total = 0;
            for(int j = 0; j < pollinators.size(); j++) {
                total += visit_counts.get(j);
            }

            if(i == 1){
                String pollinators_json = new Gson().toJson(pollinators);
                req.setAttribute("pollinatorsJSON", pollinators_json);
            }

            String visit_counts_json = new Gson().toJson(visit_counts);

            req.setAttribute("visitCountsJSON" + i + "", visit_counts);
            req.setAttribute("visitCountsTotal"  + i + "", total);
        }
        req.getRequestDispatcher("/_view/pollVisitsByCounty.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/_view/pollVisitsByCounty.jsp").forward(req, resp);
    }
}