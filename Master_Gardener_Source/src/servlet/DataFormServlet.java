package servlet;

import com.sun.deploy.security.WinDeployNTLMAuthCallback;
import controller.AdminController;
import controller.DataFormController;
import controller.SearchController;
import controller.UserController;
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
        int plants_total = 1;
        int strains_total = 9;
        int pollinators_total = 9;
        PollinatorDataForm dataForm = new PollinatorDataForm(-1, -1, null, -1,
                null, null,  null, null, null, null, null,
                0, null, null , 0, null, null, null, null, null);
        ArrayList<User> generators = new ArrayList<User>();
        ArrayList<Plot> plots = new ArrayList<Plot>();
        ArrayList<Plant> plants = new ArrayList<Plant>();
        ArrayList<PlantStrain> strains = new ArrayList<PlantStrain>();
        ArrayList<Pollinator> pollinators = new ArrayList<Pollinator>();
        ArrayList<PollinatorVisitCount> pollinatorVisitCounts = new ArrayList<PollinatorVisitCount>();
        String buttonPress = null;
        String errorMessage = null;

        //DataForm Generators & Garden Information
        String generator_first_name1 = null;
        String generator_first_name2 = null;
        String generator_first_name3 = null;
        String generator_first_name4 = null;
        String generator_last_name1 = null;
        String generator_last_name2 = null;
        String generator_last_name3 = null;
        String generator_last_name4 = null;
        String county = null;
        String garden_name = null;

        //DataForm Date Information
        int week_num = 0;
        LocalDate date_collected = null;
        LocalDate date_generated = null;
        LocalDate date_confirmed = null;
        LocalTime start_time = null;
        LocalTime stop_time = null;

        //DataForm Weather Conditions & Other Information
        int temperature = 0;
        String wind_status = null;
        String cloud_status = null;
        String comments = null;
        String butterfly_moth_comments = null;

        //Dataform Plant, Strain, Plot, & Pollinator Information
        String plant_name = null;
        String strain_name = null;
        String pollinator_name = null;
        double plot_height = 0.0;
        double plot_area_dbl = 0.0;
        double plot_percent_coverage = 0.0;
        String plot_blooms_open_status = null;

        //Visit Count Information
        int visit_count = 0;
        //
        // ------------------------------ End DataForm Initialization ------------------------------ //
        //

        //
        // ------------------------------ Begin DataForm Content ------------------------------ //
        //
        buttonPress = req.getParameter("dataformSubmit");

        if (buttonPress != null)
        {
            System.out.println("DataForm Servlet: DataForm submitted");
            //DataForm Generators & Garden Information
            generator_first_name1 = req.getParameter("generatorFirstName1");
            generator_first_name2 = req.getParameter("generatorFirstName2");
            generator_first_name3 = req.getParameter("generatorFirstName3");
            generator_first_name4 = req.getParameter("generatorFirstName4");
            generator_last_name1 = req.getParameter("generatorLastName1");
            generator_last_name2 = req.getParameter("generatorLastName2");
            generator_last_name3 = req.getParameter("generatorLastName3");
            generator_last_name4 = req.getParameter("generatorLastName4");
            garden_name = req.getParameter("garden_name");

            //DataForm Date & Time Information
            week_num = Integer.parseInt(req.getParameter("weekNumber"));
            date_collected = getDateFromParameter(req.getParameter("dateCollected"));
            date_generated = getDateFromParameter(req.getParameter("dateGenerated"));
            date_confirmed = getDateFromParameter(req.getParameter("dateConfirmed"));
            start_time = getTimeFromParameter(req.getParameter("startTime"));
            stop_time = getTimeFromParameter(req.getParameter("stopTime"));

            //DataForm Weather Conditions & Other Information
            temperature = Integer.parseInt(req.getParameter("temperature"));
            temperature = Integer.parseInt(req.getParameter("temperature"));
            wind_status = req.getParameter("windStatus");
            cloud_status = req.getParameter("cloudStatus");
            comments = req.getParameter("comments");
            butterfly_moth_comments = req.getParameter("butterflyMothComments");

            if ("".equals(week_num) || week_num == 0) {
                errorMessage = "Please enter the Data Collection Cycle week number";
                System.out.println(errorMessage);
                week_num = 0;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(generator_first_name1) || generator_first_name1 == null) {
                errorMessage = "Missing first name. Please enter the first and last name of at least one (1) DataForm Generator";
                System.out.println(errorMessage);
                generator_first_name1 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(generator_last_name1) || generator_last_name1 == null) {
                errorMessage = "Missing last name. Please enter the first and last name of at least one (1) DataForm Generator";
                System.out.println(errorMessage);
                generator_last_name1 = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(garden_name) || garden_name == null) {
                errorMessage = "Please enter the name of the garden";
                System.out.println(errorMessage);
                garden_name = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(date_collected) || date_collected == null) {
                errorMessage = "Please enter the data collection date";
                System.out.println(errorMessage);
                date_collected = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            /*else if ("".equals(date_generated) || date_generated == null) {
                errorMessage = "Please enter the DataForm generation date";
                System.out.println(errorMessage);
                date_generated = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(plot_blooms_open_status) || plot_blooms_open_status == null) {
                errorMessage = "Please enter the bloom status of the plot";
                System.out.println(errorMessage);
                plot_blooms_open_status = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }*/
            else if ("".equals(plot_percent_coverage)) {
                errorMessage = "Please enter flower coverage percentage of the plot";
                System.out.println(errorMessage);
                plot_percent_coverage = 0;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else {

                System.out.println("DataForm Servlet: All *required* parameters accepted successfully");

                //
                // ------------------------------ Set DataForm Fields ------------------------------ //
                //
                try {

                    controller = new DataFormController();
                    //Set Week Number
                    dataForm.setWeek_number(week_num);
                    //Set Garden & County
                    dataForm.setGarden_id(controller.getGardenIDByGardenName(garden_name));
                    dataForm.setCounty_id(controller.getCountyIDByGardenName(garden_name));
                    //Set Generators
                    System.out.println(generator_first_name1);
                    System.out.println(generator_last_name1);
                    System.out.println(controller.getUserIDFromFirstNameAndLastName(generator_first_name1, generator_last_name1));
                    System.out.println(controller.getUserFromUserID(controller.getUserIDFromFirstNameAndLastName(generator_first_name1, generator_last_name1)).getUserId());

                    generators.add(controller.getUserFromUserID(controller.getUserIDFromFirstNameAndLastName(generator_first_name1, generator_last_name1)));

                    if((!"".equals(generator_first_name2) && generator_first_name2 != null) && (!"".equals(generator_last_name2) && generator_last_name2 != null))
                    {
                        int generator_id = -1;
                        generator_id = controller.getUserIDFromFirstNameAndLastName(generator_first_name2, generator_last_name2);
                        if(generator_id > 0){
                            generators.add(controller.getUserFromUserID(generator_id));
                        }
                    }
                    if((!"".equals(generator_first_name3) && generator_first_name3 != null) && (!"".equals(generator_last_name3) && generator_last_name3 != null))
                    {
                        int generator_id = -1;
                        generator_id = controller.getUserIDFromFirstNameAndLastName(generator_first_name3, generator_last_name3);
                        if(generator_id > 0){
                            generators.add(controller.getUserFromUserID(generator_id));
                        }
                    }
                    if((!"".equals(generator_first_name4) && generator_first_name4 != null) && (!"".equals(generator_last_name4) && generator_last_name4 != null))
                    {
                        int generator_id = -1;
                        generator_id = controller.getUserIDFromFirstNameAndLastName(generator_first_name4, generator_last_name4);
                        if(generator_id > 0){
                            generators.add(controller.getUserFromUserID(generator_id));
                        }
                    }

                    // Set Date & Time
                    dataForm.setDate_collected(date_collected);
                    //dataForm.setDate_generated(date_generated);
                    if(!"".equals(date_confirmed) && date_confirmed != null){ dataForm.setDate_confirmed(date_confirmed); }
                    if(!"".equals(start_time) && start_time != null){ dataForm.setMonitor_start(start_time); }
                    if(!"".equals(stop_time) && stop_time != null){ dataForm.setMonitor_stop(stop_time); }
                    // Set Weather Status & Temperature
                    if(!"".equals(wind_status) && wind_status != null){ dataForm.setWind_status(wind_status); }
                    if(!"".equals(cloud_status) && cloud_status != null){ dataForm.setCloud_status(cloud_status); }
                    if(!"".equals(temperature) && temperature != 0){ dataForm.setTemperature(temperature); }
                    // Set Comments
                    if(!"".equals(comments) && comments != null){ dataForm.setComments(comments); }
                    if(!"".equals(butterfly_moth_comments) && butterfly_moth_comments != null){ dataForm.setButterflyMothComments(butterfly_moth_comments); }

                    // Reset JSP DataForm Parameters
                    req.setAttribute("weekNumber", week_num);
                    req.setAttribute("gardenName", garden_name);
                    req.setAttribute("generatorFirstName1", generator_first_name1); req.setAttribute("generatorLastName1", generator_first_name1);
                    req.setAttribute("generatorFirstName2", generator_first_name2); req.setAttribute("generatorLastName2", generator_first_name2);
                    req.setAttribute("generatorFirstName3", generator_first_name3); req.setAttribute("generatorLastName3", generator_first_name3);
                    req.setAttribute("generatorFirstName4", generator_first_name4); req.setAttribute("generatorLastName4", generator_first_name4);
                    req.setAttribute("dateCollected", date_collected);
                    req.setAttribute("dateGenerated", date_generated);
                    req.setAttribute("dateConfirmed", date_confirmed);
                    req.setAttribute("windStatus", wind_status);
                    req.setAttribute("cloudStatus", cloud_status);
                    req.setAttribute("comments", comments);
                    req.setAttribute("butterflyMothComments", butterfly_moth_comments);
                    req.setAttribute("temperature", temperature);
                    req.setAttribute("monitorStart", start_time);
                    req.setAttribute("monitorStop", stop_time);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //
                // ------------------------------ Set DataForm Lists ------------------------------ //
                //
                //
                // ------------------------------ Begin List Population Loop ------------------------------ //
                //
                //Plants Loop
                for(int i = 1; i <= plants_total; i++)
                {
                    //Set Plant
                    plant_name = req.getParameter("plant" + i + "Name");//Variable from Drop-Down
                    if ("".equals(plant_name) || plant_name == null) {
                        errorMessage = "Please enter the genus (plant) name for the plot";
                        System.out.println(errorMessage);
                        plant_name = null;
                        req.setAttribute("errorMessage", errorMessage);
                        req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
                    }
                    try {
                        plants.add(controller.getPlantByPlantID(controller.getPlantIDByPlantName(plant_name)));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // Reset JSP Plant Parameters
                    req.setAttribute("plant" + i + "Name", plant_name);

                    //Strains Loop
                    for(int j = 1; j <= strains_total; j++)
                    {
                        //Set Strain
                        strain_name = req.getParameter("plant" + i + "Strain" + j + "Name");//Variable from Drop-Down
                        if ("".equals(strain_name) || strain_name == null) {
                            errorMessage = "Please enter the species (strain) name for species #" + j;
                            System.out.println(errorMessage);
                            strain_name = null;
                            req.setAttribute("errorMessage", errorMessage);
                            req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
                        }
                        try {
                            strains.add(controller.getStrainByStrainID(controller.getStrainIDByStrainName(strain_name)));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        // Reset JSP Strain Parameters
                        req.setAttribute("plant" + i + "Strain" + j + "Name", strain_name);

                        //Set Plot
                        try {
                            plots.add(controller.getPlotByGardenIDAndStrainID(dataForm.getGarden_id(), strains.get(j-1).getStrainID()));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //plot_height = Double.parseDouble(req.getParameter("plant" + i + "Strain" + j + "Plot" + j + "Height"));
                        plot_area_dbl = Double.parseDouble(req.getParameter("plant" + i + "Strain" + j + "Plot" + j + "AreaDbl"));
                        plot_percent_coverage = Double.parseDouble(req.getParameter("plant" + i + "Strain" + j + "Plot" + j + "PercentCoverage"));
                        //plot_blooms_open_status = req.getParameter("plant" + i + "Strain" + j + "Plot" + j + "BloomsOpen");

                        /*Plot plot = new Plot(dataForm.getGarden_id(), dataForm.getPlants().get(i-1).getPlantID(), dataForm.getPlantStrains().get(j-1).getStrainID(),
                                0, 0, 0, null);
                        if(!"".equals(plot_height) && plot_height != 0){ plot.setPlot_height(plot_height); }
                        if(!"".equals(plot_blooms_open_status) && plot_blooms_open_status != null){ plot.setPlot_blooms_open_status(plot_blooms_open_status); }*/
                        if(!"".equals(plot_area_dbl) && plot_area_dbl != 0){ plots.get(j-1).setPlot_area_dbl(plot_area_dbl); }
                        if(!"".equals(plot_percent_coverage) && plot_percent_coverage != 0){ plots.get(j-1).setPlot_percent_coverage(plot_percent_coverage); }

                        // Reset JSP Plot Parameters
                        //req.setAttribute("plant" + i + "Strain" + j + "Plot" + j + "Height", plot_height);
                        req.setAttribute("plant" + i + "Strain" + j + "Plot" + j + "AreaDbl", plot_area_dbl);
                        req.setAttribute("plant" + i + "Strain" + j + "Plot" + j + "PercentCoverage", plot_percent_coverage);
                        //req.setAttribute("plant" + i + "Strain" + j + "Plot" + j + "BloomsOpen", plot_blooms_open_status);

                        //
                        // ------------------------------ Set Pollinator Visit Count Fields ------------------------------ //
                        //
                        //Pollinators Loop
                        for(int k = 1; k <= pollinators_total; k++)
                        {
                            //Set Pollinator
                            try {
                                pollinators.add(controller.getPollinatorByPollinatorID(k));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            //Jenky DataForm ID Initialization
                            int dataform_id = 0;
                            try {
                                dataform_id = (controller.getAllDataFormIDs().get(0)+2);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            visit_count = Integer.parseInt(req.getParameter("plant" + i + "Strain" + j + "Plot" + j + "Pollinator" + k));//Variable From Input Form

                            //Set Pollinator Visit Counts Record if Visit Count is > 0
                            if(!"".equals(visit_count) && visit_count != 0) {
                                PollinatorVisitCount pollinatorVisitCount = new PollinatorVisitCount(dataform_id, k, plots.get(j-1).getPlot_id(), plants.get(i-1).getPlantID(),
                                        strains.get(j-1).getStrainID(), visit_count);
                                pollinatorVisitCounts.add(pollinatorVisitCount);
                            };
                        }
                    }
                }
                //
                // ------------------------------ End List Population Loop ------------------------------ //
                //
                //
                // ------------------------------ End DataForm Content ------------------------------ //
                //

                // Populate & Generate DataForm
                System.out.println("DataForm Servlet: Generating DataForm...");
                /*PollinatorDataForm pdf = new PollinatorDataForm(dataForm.getGarden_id(), dataForm.getCounty_id(), dataForm.getGenerators(), dataForm.getWeek_number(),
                        dataForm.getDate_collected(), dataForm.getDate_generated(), dataForm.getDate_confirmed(), dataForm.getWind_status(), dataForm.getCloud_status(),
                        dataForm.getComments(),dataForm.getButterflyMothComments(), dataForm.getTemperature(), dataForm.getMonitor_start(), dataForm.getMonitor_stop(),
                        0, dataForm.getPlants(), dataForm.getPlantStrains(), dataForm.getPlots(), dataForm.getPollinators(), dataForm.getPollinatorVisitCounts() );*/
                PollinatorDataForm pdf = new PollinatorDataForm(dataForm.getGarden_id(), dataForm.getCounty_id(), generators, dataForm.getWeek_number(),
                        dataForm.getDate_collected(), null, null, dataForm.getWind_status(), dataForm.getCloud_status(),
                        dataForm.getComments(),dataForm.getButterflyMothComments(), dataForm.getTemperature(), dataForm.getMonitor_start(), dataForm.getMonitor_stop(),
                        0, plants, strains, plots, pollinators, pollinatorVisitCounts);
                try {
                    controller.createDataInput(pdf);
                } catch (SQLException e) {
                    System.out.println("error");
                    errorMessage = "Unexpected Error";
                    req.setAttribute("errorMessage", errorMessage);
                    req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
                    e.printStackTrace();
                }
                System.out.println("DataForm Servlet: DataForm successfully generated! Time to graduate :)");
                resp.sendRedirect(req.getContextPath() + "/home");
            }
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
