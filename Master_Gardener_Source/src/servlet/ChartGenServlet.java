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

public class ChartGenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        // Initialize a list of type DataObject
        List<Pollinator> pollinators = new ArrayList<>();
        pollinators.add(new Pollinator("Honey Bee", "Bee"));
        pollinators.add(new Pollinator("Bumblebee", "Bee"));
        pollinators.add(new Pollinator("Monarch", "Butterfly"));

        // Convert the object to a JSON string
        String json = new Gson().toJson(pollinators);
        /*resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);*/
        req.setAttribute("pollinatorJSON", json);
        req.getRequestDispatcher("/_view/chartGen.jsp").forward(req, resp);

        //req.getRequestDispatcher("/_view/chartGen.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/_view/chartGen.jsp").forward(req, resp);
    }
}