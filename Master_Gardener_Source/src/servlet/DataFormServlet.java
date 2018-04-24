package servlet;

import controller.AdminController;
import controller.DataFormController;
import controller.SearchController;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DataFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataFormController controller = null;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Initialization
        int plants_total = 2;
        int strains_total = 9;
        int pollinators_total = 9;
        req.setAttribute("plantsTotal", plants_total);
        req.setAttribute("strainsTotal", strains_total);
        req.setAttribute("pollinatorsTotal", pollinators_total);

        req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //
        // ------------------------------ Begin DataForm Initialization ------------------------------ //
        //

        // Future Data-Cycle Work: Change total # of plants, strains, and pollinators to match the necessary amounts that pertain to each data cycle
        // Current totals:
        // 2 plants (Coreopsis Verticillata & Monarda)
        // 9 Strains (STRAIGHT, MoonBeam, Zagreb, Crème Brulee, Route 66, Didyma Straight, Didyma Petite Delight, Didyma Marshalls Delight, X Peters Fancy Fuschia)
        // 9 Pollinators (Honey Bee, Carpenter Bee, Bumble Bee, Green Metallic Bee, Green Sweat Bee, Dark Sweat Bee, Butterfly and Moth, Other Bees, Other Pollinators)

        // Initialization (Loop Variables, ArrayLists, Servlet Variables)
        int plants_total = 2;
        int strains_total = 9;
        int pollinators_total = 9;
        ArrayList<Plant> plants = new ArrayList<>();
        ArrayList<PlantStrain> strains = new ArrayList<>();
        ArrayList<Pollinator> pollinators = new ArrayList<>();
        ArrayList<PollinatorVisitCount> visitCounts = new ArrayList<>();
        ArrayList<Plot> plotData = new ArrayList<>();
        String buttonPress = null;
        String errorMessage = null;

        //DataForm Generators & Garden Information
        String generator_name1 = null;
        String generator_name2 = null;
        String generator_name3 = null;
        String generator_name4 = null;
        String county = null;
        String garden_name = null;

        //DataForm Date Information
        int week_num = 0;
        LocalDate date_collected = null;
        LocalDate date_generated = null;
        LocalDate date_confirmed = null;
        LocalTime start_time = null;
        LocalTime end_time = null;

        //DataForm Weather Conditions & Other Information
        int temperature = 0;
        String wind_status = null;
        String cloud_status = null;
        String comments = null;
        String butterfly_moth_comments = null;

        //Dataform Plant, Strain, Plot, & Pollinator Information
        String plant_name = null;
        String strain_name = null;
        String plot_blooms_open = null;
        double plot_height = 0.0;
        double plot_area_dbl = 0.0;
        double plot_percent_coverage = 0.0;

        //Visit Count Information
        int visit_count = 0;

        //
        // ------------------------------ End DataForm Initialization ------------------------------ //
        //

        //
        // ------------------------------ Begin DataForm Content ------------------------------ //
        //

        buttonPress = req.getParameter("dataFormSubmit");

        if (buttonPress != null)
        {
            System.out.println("DataForm submitted");
            //DataForm Generators & Garden Information
            generator_name1 = req.getParameter("generatorName1");
            generator_name2 = req.getParameter("generatorName2");
            generator_name3 = req.getParameter("generatorName3");
            generator_name4 = req.getParameter("generatorName4");
            garden_name = req.getParameter("garden_name");

            //DataForm Date & Time Information
            week_num = Integer.parseInt(req.getParameter("garden_name"));
            date_collected = getDateFromParameter(req.getParameter("dateCollected"));
            date_generated = getDateFromParameter(req.getParameter("dateGenerated"));
            date_confirmed = getDateFromParameter(req.getParameter("dateConfirmed"));
            start_time = getTimeFromParameter(req.getParameter("startTime"));
            end_time = getTimeFromParameter(req.getParameter("endTime"));

            //DataForm Weather Conditions & Other Information
            temperature = Integer.parseInt(req.getParameter("temperature"));
            wind_status = req.getParameter("windStatus");
            cloud_status = req.getParameter("cloudStatus");
            comments = req.getParameter("comments");
            butterfly_moth_comments = req.getParameter("butterflyMothComments");

            plot_blooms_open = req.getParameter("plotBloomsOpen");
            plot_height = Double.parseDouble(req.getParameter("plotHeight"));
            plot_area_dbl = Double.parseDouble(req.getParameter("plotAreaDbl"));
            plot_percent_coverage = Double.parseDouble(req.getParameter("plotPercentCoverage"));

            if ("".equals(generator_name1) || generator_name1 == null) {
                errorMessage = "Please enter the name of at least one (1) DataForm Generator";
                System.out.printf("%s", errorMessage);
                generator_name1 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(garden_name) || garden_name == null) {
                errorMessage = "Please enter the name of the garden";
                System.out.printf("%s", errorMessage);
                garden_name = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(date_collected) || date_collected == null) {
                errorMessage = "Please enter the data collection date";
                System.out.printf("%s", errorMessage);
                date_collected = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(date_generated) || date_generated == null) {
                errorMessage = "Please enter the DataForm generation date";
                System.out.printf("%s", errorMessage);
                date_generated = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(start_time) || start_time == null) {
                errorMessage = "Please enter a start time";
                System.out.printf("%s", errorMessage);
                start_time = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(end_time) || end_time == null) {
                errorMessage = "Please enter a end time";
                System.out.printf("%s", errorMessage);
                end_time = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(plot_blooms_open) || plot_blooms_open == null) {
                errorMessage = "Please enter the bloom status of the plot";
                System.out.printf("%s", errorMessage);
                plot_blooms_open = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(plot_percent_coverage) || plot_percent_coverage == 0) {
                errorMessage = "Please enter flower coverage percentage of the plot";
                System.out.printf("%s", errorMessage);
                plot_percent_coverage = 0;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else {

                System.out.println("All parameters accepted successfully");

                for(int i = 1; i <= (plants.size()+1); i++)
                {
                    plant_name = req.getParameter("plantName" + i);
                    if ("".equals(plant_name) || plant_name == null) {
                        errorMessage = "Please enter the genus (plant) name for the plot";
                        System.out.printf("%s", errorMessage);
                        plant_name = null;
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
                    }

                    try {
                        plants.get(i).setPlantID(controller.getPlantIDByPlantName(plant_name));//Variable from Drop-Down
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    for(int j = 1; j <= (strains.size()+1); j++)
                    {
                        strain_name = req.getParameter("strainName" + j);
                        if ("".equals(strain_name) || strain_name == null) {
                            errorMessage = "Please enter the species (strain) name for the first pollinator";
                            System.out.printf("%s", errorMessage);
                            strain_name = null;
                            req.setAttribute("errorMessage", errorMessage);
                            req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
                        }

                        try {
                            strains.get(i).setStrainID(controller.getStrainIDByStrainName(strain_name));//Variable from Drop-Down
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        plotData.get(0).setPlot_height(4.0);
                        plotData.get(0).setPlot_area_dbl(11);
                        plotData.get(0).setBlooms_open_status("11");
                        plotData.get(0).setPercent_coverage(11);

                        for(int k = 1; k <= (pollinators.size()+1); k++)
                        {
                            //Visit Count Information
                            visit_count = Integer.parseInt(req.getParameter("visitCountStrain" + j + "Pollinator" + k));
                            Pollinator poll = new Pollinator(1, "Crub1", "Meme1");
                            PollinatorVisitCount pvc = new PollinatorVisitCount(2, 111, 59, 4, Strain1Count1);
                        }

                    }
                }


                Pollinator poll1 = new Pollinator(2, "Crub1", "Meme1");
                Pollinator poll2 = new Pollinator(13, "Crub2", "Meme1");
                Pollinator poll3 = new Pollinator(14, "Crub3", "Meme1");
                Pollinator poll4 = new Pollinator(15, "Crub4", "Meme1");
                Pollinator poll5 = new Pollinator(16, "Crub5", "Meme1");
                PollinatorVisitCount pvc1 = new PollinatorVisitCount(22, 111, 60, 4, Strain1Count2);
                PollinatorVisitCount pvc2 = new PollinatorVisitCount(23, 111, 61, 4, Strain1Count3);
                PollinatorVisitCount pvc3 = new PollinatorVisitCount(24, 111, 62, 4, Strain1Count4);
                PollinatorVisitCount pvc4 = new PollinatorVisitCount(26, 111, 63, 4, Strain1Count5);
                PollinatorVisitCount pvc5 = new PollinatorVisitCount(27, 111, 64, 4, Strain1Count6);

                polls.add(poll);
                polls.add(poll1);
                polls.add(poll2);
                polls.add(poll3);
                polls.add(poll4);
                polls.add(poll5);
                pvcs.add(pvc);
                pvcs.add(pvc1);
                pvcs.add(pvc2);
                pvcs.add(pvc3);
                pvcs.add(pvc4);
                pvcs.add(pvc5);

                System.out.println("Set pdf");
                PollinatorDataForm pdf = new PollinatorDataForm(1, 1, 1, 1, 1, temperature,
                        dateCreated, dateGenerated, startTime, endTime, comments, cloudCover,
                        wind, plotData, plants, strains, polls, pvcs);
                DataFormController controller = new DataFormController();
                try {

                    System.out.println("try");
                    controller.createDataInput(pdf);
                } catch (SQLException e) {
                    System.out.println("error");
                    errorMessage = "Unexpected Error";
                    req.setAttribute("errorMessage", errorMessage);
                    // req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
                    e.printStackTrace();
                }
                resp.sendRedirect(req.getContextPath() + "/garden");
            }

            req.setAttribute("dateCollected", dateCreated);
            req.setAttribute("temperature", temperature);
            req.setAttribute("wind", wind);
            req.setAttribute("cloudcover", cloudCover);
            req.setAttribute("plantGenus", plantGenus);
            req.setAttribute("Strain1Vigor", Strain1Vigor);
            req.setAttribute("Strain1Height", Strain1Height);
            req.setAttribute("Strain1Blooms", Strain1Blooms);
            req.setAttribute("Strain1Coverage", Strain1Coverage);

            req.setAttribute("Strain1Genus1", Strain1Genus1);
            req.setAttribute("Strain1Species1", Strain1Species1);
            req.setAttribute("Strain1Count1", Strain1Count1);

            req.setAttribute("Strain1Genus2", Strain1Genus2);
            req.setAttribute("Strain1Species2", Strain1Species2);
            req.setAttribute("Strain1Count2", Strain1Count2);

            req.setAttribute("Strain1Genus3", Strain1Genus3);
            req.setAttribute("Strain1Species3", Strain1Species3);
            req.setAttribute("Strain1Count3", Strain1Count3);

            req.setAttribute("Strain1Genus4", Strain1Genus4);
            req.setAttribute("Strain1Species4", Strain1Species4);
            req.setAttribute("Strain1Count4", Strain1Count4);

            req.setAttribute("Strain1Genus5", Strain1Genus5);
            req.setAttribute("Strain1Species5", Strain1Species5);
            req.setAttribute("Strain1Count5", Strain1Count5);

            req.setAttribute("Strain1Genus5", Strain1Genus5);
            req.setAttribute("Strain1Species5", Strain1Species5);
            req.setAttribute("Strain1Count5", Strain1Count5);

            req.setAttribute("Strain1Genus6", Strain1Genus6);
            req.setAttribute("Strain1Species6", Strain1Species6);
            req.setAttribute("Strain1Count6", Strain1Count6);

            req.setAttribute("comments", comments);
        }

    }

    private int getIntFromParameter(String s) {
        if (s == null || s.equals("")) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    private LocalDate getDateFromParameter(String s) {
        if (s == null || s.equals("")) {
            return null;
        } else {
            return LocalDate.parse(s);
        }
    }

    private LocalTime getTimeFromParameter(String s) {
        if (s == null || s.equals("")) {
            return null;
        } else {
            return LocalTime.parse(s);
        }
    }
}
