package servlet;

import com.google.gson.Gson;
import model.Pollinator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pollVisitsByCountyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Initialize a list
        // TODO: Invoke Controller to query database
        List<Pollinator> pollinators = new ArrayList<>();
        pollinators.add(new Pollinator(1,"Honey Bee", "Bee"));
        pollinators.add(new Pollinator(2, "Carpenter Bee", "Bee"));
        pollinators.add(new Pollinator(3, "Monarch", "Butterfly"));

        // Convert the object to a JSON string and pass it to the JSP via hidden input
        String json = new Gson().toJson(pollinators);
        req.setAttribute("pollinatorJSON", json);

        req.getRequestDispatcher("/_view/pollVisitsByCounty.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/_view/pollVisitsByCounty.jsp").forward(req, resp);
    }
}