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

        // Initialization
        int plants_total = 2;
        int strains_total = 9;
        int pollinators_total = 9;
        ArrayList<Plant> plants = new ArrayList<>();
        ArrayList<PlantStrain> strains = new ArrayList<>();
        ArrayList<Pollinator> pollinators = new ArrayList<>();
        ArrayList<Plot> plotData = new ArrayList<>();

        //DataForm Generators & Garden Information
        String generator1 = null;
        String generator2 = null;
        String generator3 = null;
        String generator4 = null;
        String county = null;
        String garden_name = null;

        //DataForm Date Information
        int week_num = 0;
        LocalDate date_collected = null;
        LocalDate date_generated = null;
        LocalDate date_confirmed = null;
        LocalTime start_time = null;
        LocalTime end_time = null;

        //DataForm Date & Other Information
        int temperature = 0;
        String wind = null;
        String cloud_cover = null;
        String comments = null;
        String butterfly_moth_comments = null;

        //Plant, Strain, Plot, & Pollinator Information
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

        String buttonPress = null;
        buttonPress = req.getParameter("dataFormSubmit");

        if (buttonPress != null)
        {
            System.out.println("DataForm submitted");

            generator1 = req.getParameter("generator_id1");
            generator2 = req.getParameter("generator_id2");
            generator3 = req.getParameter("generator_id3");
            generator4 = req.getParameter("generator_id4");
            garden_name = req.getParameter("garden_name");

            //DataForm Date Information
            week_num = Integer.parseInt(req.getParameter("garden_name"));
            date_collected = getDateFromParameter(req.getParameter("dateCollected"));
            date_generated = null;
            LocalTime start_time = null;
            LocalTime end_time = null;
            ;
            System.out.println("passed this1");

            //  startTime = LocalTime.parse(req.getParameter("startTime"));
            //DataForm Date & Other Information
            int temperature = 0;
            String wind = null;
            String cloud_cover = null;
            String comments = null;
            String butterfly_moth_comments = null;

            //Plant, Strain, Plot, & Pollinator Information
            String plant_name = null;

            comments = req.getParameter("comments");
            butterfly_moth_comments = req.getParameter("butterfly_moth_comments");
            strain_name = req.getParameter("strain_name");


            String strain_name = null;
            String plot_blooms_open = null;
            double plot_height = 0.0;
            double plot_area_dbl = 0.0;
            double plot_percent_coverage = 0.0;

            //Visit Count Information
            int visit_count = 0;

            for(int i = 0; i <= plants.size(); i++)
            {
                plants.get(i).setPlantID();//Variable from Drop-Down

                generator4 = req.getParameter("generator_id4");
                for(int j = 0; j <= strains.size(); i++)
                {
                    try {
                        strains.get(i).setStrainID(controller.getStrainIDByStrainName(strainName));//Variable from Drop-Down
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    for(int k = 0; k <= pollinators.size(); k++)
                    {
                    }

                    plotData.get(0).setPlot_height(4.0);
                    plotData.get(0).setPlot_area_dbl(11);
                    plotData.get(0).setBlooms_open_status("11");
                    plotData.get(0).setPercent_coverage(11);
                }
            }




            PlantStrain strain = new PlantStrain(1, 1, "hello", "maybe");
            strains.add(strain);
            System.out.println("passed this");

            //generator = req.getParameter("genUsers");
            //gardenName = req.getParameter("garden");
            //county = req.getParameter("county");

            //Uncomment     LocalDate.parse

            dateCreated = getDateFromParameter(req.getParameter("dateCollected"));
            System.out.println("passed this1");

            //  startTime = LocalTime.parse(req.getParameter("startTime"));
            //endTime = req.getParameter("endTime");
            temperature = getIntFromParameter(req.getParameter("temperature"));
            wind = req.getParameter("wind");
            cloudCover = req.getParameter("cloudCover");
            plantGenus = req.getParameter("plantGenus");

            //Strain 1
            // strainDiv1 = req.getParameter("");
            // Strain1Name = req.getParameter("Strain1Name");
            Strain1Vigor = req.getParameter("Strain1Vigor");
            Strain1Height = Double.parseDouble(req.getParameter("Strain1Height"));
            Strain1PlotSize = Double.parseDouble(req.getParameter("Strain1PlotSize"));
            Strain1Blooms = req.getParameter("Strain1Blooms");
            Strain1Coverage = Double.parseDouble(req.getParameter("Strain1Coverage"));

            Strain1Genus1 = req.getParameter("Strain1Genus1");
            Strain1Species1 = req.getParameter("Strain1Species1");
            Strain1Count1 = getIntFromParameter(req.getParameter("Strain1Count1"));

            Strain1Genus2 = req.getParameter("Strain1Genus2");
            Strain1Species2 = req.getParameter("Strain1Species2");
            Strain1Count2 = getIntFromParameter(req.getParameter("Strain1Count2"));

            Strain1Genus3 = req.getParameter("Strain1Genus3");
            Strain1Species3 = req.getParameter("Strain1Species3");
            Strain1Count3 = getIntFromParameter(req.getParameter("Strain1Count3"));

            Strain1Genus4 = req.getParameter("Strain1Genus4");
            Strain1Species4 = req.getParameter("Strain1Species4");
            Strain1Count4 = getIntFromParameter(req.getParameter("Strain1Count4"));

            Strain1Genus5 = req.getParameter("Strain1Genus5");
            Strain1Species5 = req.getParameter(" Strain1Species5");
            Strain1Count5 = getIntFromParameter(req.getParameter("Strain1Count5"));

            Strain1Genus6 = req.getParameter("Strain1Genus6");
            Strain1Species6 = req.getParameter("Strain1Species6");
            Strain1Count6 = getIntFromParameter(req.getParameter("Strain1Count6"));



            if ("".equals(dateCreated) || dateCreated == null) {
                errorMessage = "Enter the day of collection in the format YYYY-MM-DD";
                System.out.printf("%s", errorMessage);
                dateCreated = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(generator) || generator == null) {
                errorMessage = "Enter the name of the users colleced";
                System.out.printf("%s", errorMessage);
                generator = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(gardenName) || gardenName == null) {
                errorMessage = "Enter the name of the garden";
                System.out.printf("%s", errorMessage);
                gardenName = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(county) || county == null) {
                errorMessage = "Enter the date";
                System.out.printf("%s", errorMessage);
                county = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(startTime) || startTime == null) {
                errorMessage = "Enter a start time";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(endTime) || endTime == null) {
                errorMessage = "Enter a end time";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
            }
            else if ("".equals(temperature) || temperature == 0) {
                errorMessage = "Enter a temperature";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(wind) || wind == null) {
                errorMessage = "Enter a wind condition";
                System.out.printf("%s", errorMessage);
                wind = null;
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(cloudCover) || cloudCover == null) {
                errorMessage = "Enter a cloud cover condition";
                System.out.printf("%s", errorMessage);
                cloudCover = null;
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(plantGenus) || plantGenus == null) {
                errorMessage = "Enter the plant genus";
                System.out.printf("%s", errorMessage);
                plantGenus = null;
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(Strain1Vigor) || Strain1Vigor == null) {
                errorMessage = "Enter the vigor of the plot";
                System.out.printf("%s", errorMessage);
                Strain1Vigor = null;
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(Strain1Height) || Strain1Height == 0) {
                errorMessage = "Enter the avgerage height of the plot";
                System.out.printf("%s", errorMessage);
                Strain1Height = 0;
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(Strain1PlotSize) || Strain1PlotSize == 0) {
                errorMessage = "Enter the average height of the plot";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(Strain1Blooms) || Strain1Blooms == null) {
                errorMessage = "Enter the blooms of the strain";
                System.out.printf("%s", errorMessage);
                Strain1Blooms = null;
                req.setAttribute("errorMessage", errorMessage);
            }
            else if ("".equals(Strain1Coverage) || Strain1Coverage == 0) {
                errorMessage = "Enter coverage of the plot";
                System.out.printf("%s", errorMessage);
                Strain1Coverage = 0;
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Genus1) || Strain1Genus1 == null) {
                errorMessage = "Enter the genus for the first pollinator";
                System.out.printf("%s", errorMessage);
                Strain1Genus1 = null;
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Species1) || Strain1Species1 == null) {
                errorMessage = "Enter the species for the first pollinator";
                System.out.printf("%s", errorMessage);
                Strain1Species1 = null;
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Count1) || Strain1Count1 == 0) {
                errorMessage = "Enter the count for the first pollinator";
                System.out.printf("%s", errorMessage);
                Strain1Count1 = 0;
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Genus2) || Strain1Genus2 == null) {
                errorMessage = "Enter the 2nd genus for second pollinator, first ";
                System.out.printf("%s", errorMessage);
                Strain1Genus2 = null;
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Species2) || Strain1Species2 == null) {
                errorMessage = "Enter the 2nd speices for the first strain";
                System.out.printf("%s", errorMessage);
                Strain1Species1 = null;
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Genus3) || Strain1Genus3 == null) {
                errorMessage = "Enter the 3st speices";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Species3) || Strain1Species3 == null) {
                errorMessage = "Enter the 33st speices";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Count3) || Strain1Count3 == 0) {
                errorMessage = "3rd Count";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Genus4) || Strain1Genus4 == null) {
                errorMessage = "Enter the 1st speicees";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Species4) || Strain1Species4 == null) {
                errorMessage = "Enter the 1st speiceas";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Count4) || Strain1Count4 == 0) {
                errorMessage = "Enter the 1st speieces";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Genus5) || Strain1Genus5 == null) {
                errorMessage = "Enter the 1st speiwes";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Count5) || Strain1Count5 == 0) {
                errorMessage = "Enter the 1st speiwces";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Genus6) || Strain1Genus6 == null) {
                errorMessage = "Enter the 1st speicegs";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Species6) || Strain1Species6 == null) {
                errorMessage = "Enter the 1st speicbes";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);

            } else if ("".equals(Strain1Count6) || Strain1Count6 == 0) {
                errorMessage = "Enter the 1st speiwces";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);

            } else

            {


                System.out.println("Before Else");
                ArrayList<Pollinator> polls = new ArrayList<Pollinator>();
                ArrayList<PollinatorVisitCount> pvcs = new ArrayList<PollinatorVisitCount>();

                Pollinator poll = new Pollinator(1, "Crub1", "Meme1");
                Pollinator poll1 = new Pollinator(2, "Crub1", "Meme1");
                Pollinator poll2 = new Pollinator(13, "Crub2", "Meme1");
                Pollinator poll3 = new Pollinator(14, "Crub3", "Meme1");
                Pollinator poll4 = new Pollinator(15, "Crub4", "Meme1");
                Pollinator poll5 = new Pollinator(16, "Crub5", "Meme1");
                PollinatorVisitCount pvc = new PollinatorVisitCount(2, 111, 59, 4, Strain1Count1);
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
}
