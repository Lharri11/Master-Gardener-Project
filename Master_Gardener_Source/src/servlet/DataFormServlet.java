package servlet;

import controller.AdminController;
import controller.DataFormController;
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

//import controller.DataFormController;


public class DataFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        // The servlet will be setup to handle only 10 strains. The check to see if the strain is available,
        // the servlet will check to see if the id for the next div is there. If so, the 'if' block will
        // execute.


        //Main form data, constant for single form submission

        String userCollected = null;
        String gardenName = null;
        String county = null;
        int week = 0;
        LocalDate dateCreated = null;
        LocalDate dateGenerated = LocalDate.parse("2017-03-20");
        LocalTime startTime = LocalTime.parse("04:00");
        LocalTime endTime = LocalTime.parse("14:00");
        // Initialization to -1 indicates a null value
        int temperature = 0;
        String wind = null;
        String cloudCover = null;
        String plantGenus = null;
        ArrayList<Plot> plotData = new ArrayList<>();
        ArrayList<PlantStrain> strains = new ArrayList<>();

        //Strain 1
        String StrainDiv1 = null;
        String Strain1Name = null;
        String Strain1Vigor = null;
        double Strain1Height = 0;
        double Strain1PlotSize = 0;
        String Strain1Blooms = null;
        double Strain1Coverage = 0;
        String Strain1Genus1 = null;
        String Strain1Species1 = null;
        int Strain1Count1 = 0;
        String Strain1Genus2 = null;
        String Strain1Species2 = null;
        int Strain1Count2 = 0;
        String Strain1Genus3 = null;
        String Strain1Species3 = null;
        int Strain1Count3 = 0;
        String Strain1Genus4 = null;
        String Strain1Species4 = null;
        int Strain1Count4 = 0;
        String Strain1Genus5 = null;
        String Strain1Species5 = null;
        int Strain1Count5 = 0;
        String Strain1Genus6 = null;
        String Strain1Species6 = null;
        int Strain1Count6 = 0;


        //Strain 2
        String StrainDiv2 = null;
        String Strain2Name = null;
        String Strain2Vigor = null;
        int Strain2Height = 0;
        int Strain2PlotSize = 0;
        String Strain2Blooms = null;
        int Strain2Coverage = 0;
        String Strain2Genus1 = null;
        String Strain12Species1 = null;
        int Strain2Count1 = 0;
        String Strain2Genus2 = null;
        String Strain2Species2 = null;
        int Strain2Count2 = 0;
        String Strain2Genus3 = null;
        String Strain2Species3 = null;
        int Strain2Count3 = 0;
        String Strain2Genus4 = null;
        String Strain2Species4 = null;
        int Strain2Count4 = 0;
        String Strain2Genus5 = null;
        String Strain2Species5 = null;
        int Strain2Count5 = 0;
        String Strain2Genus6 = null;
        String Strain2Species6 = null;
        int Strain2Count6 = 0;


        //Strain 3
        String StrainDiv3 = null;
        String Strain3Name = null;
        String Strain3Vigor = null;
        int Strain3Height = 0;
        int Strain3PlotSize = 0;
        String Strain3Blooms = null;
        int Strain3Coverage = 0;
        String Strain3Genus1 = null;
        String Strain3Species1 = null;
        int Strain3Count1 = 0;
        String Strain3Genus2 = null;
        String Strain3Species2 = null;
        int Strain3Count2 = 0;
        String Strain3Genus3 = null;
        String Strain3Species3 = null;
        int Strain3Count3 = 0;
        String Strain3Genus4 = null;
        String Strain3Species4 = null;
        int Strain3Count4 = 0;
        String Strain3Genus5 = null;
        String Strain3Species5 = null;
        int Strain3Count5 = 0;
        String Strain3Genus6 = null;
        String Strain3Species6 = null;
        int Strain3Count6 = 0;


        //Strain 4
        String StrainDiv4 = null;
        String Strai4Name = null;
        String Strain4Vigor = null;
        int Strain4Height = 0;
        int Strain4PlotSize = 0;
        String Strain4Blooms = null;
        int Strain4Coverage = 0;
        String Strain4Genus1 = null;
        String Strain4Species1 = null;
        int Strain4Count1 = 0;
        String Strain4Genus2 = null;
        String Strain4Species2 = null;
        int Strain4Count2 = 0;
        String Strain4Genus3 = null;
        String Strain4Species3 = null;
        int Strain4Count3 = 0;
        String Strain4Genus4 = null;
        String Strain4Species4 = null;
        int Strain4Count4 = 0;
        String Strain4Genus5 = null;
        String Strain4Species5 = null;
        int Strain4Count5 = 0;
        String Strain4Genus6 = null;
        String Strain4Species6 = null;
        int Strain4Count6 = 0;


        //Strain 5
        String StrainDiv5 = null;
        String Strain5Name = null;
        String Strain5Vigor = null;
        int Strain5Height = 0;
        int Strain5PlotSize = 0;
        String Strain5Blooms = null;
        int Strain5Coverage = 0;
        String Strain5Genus1 = null;
        String Strain5Species1 = null;
        int Strain5Count1 = 0;
        String Strain5Genus2 = null;
        String Strain5Species2 = null;
        int Strain5Count2 = 0;
        String Strain5Genus3 = null;
        String Strain5Species3 = null;
        int Strain5Count3 = 0;
        String Strain5Genus4 = null;
        String Strain5Species4 = null;
        int Strain5Count4 = 0;
        String Strain5Genus5 = null;
        String Strain5Species5 = null;
        int Strain5Count5 = 0;
        String Strain5Genus6 = null;
        String Strain5Species6 = null;
        int Strain5Count6 = 0;


        //Strain 6
        String StrainDiv6 = null;
        String Strain6Name = null;
        String Strain6Vigor = null;
        int Strain6Height = 0;
        int Strain6PlotSize = 0;
        String Strain6Blooms = null;
        int Strain6Coverage = 0;
        String Strain6Genus1 = null;
        String Strain6Species1 = null;
        int Strain6Count1 = 0;
        String Strain6Genus2 = null;
        String Strain6Species2 = null;
        int Strain6Count2 = 0;
        String Strain6Genus3 = null;
        String Strain6Species3 = null;
        int Strain6Count3 = 0;
        String Strain6Genus4 = null;
        String Strain6Species4 = null;
        int Strain6Count4 = 0;
        String Strain6Genus5 = null;
        String Strain6Species5 = null;
        int Strain6Count5 = 0;
        String Strain6Genus6 = null;
        String Strain6Species6 = null;
        int Strain6Count6 = 0;


        //Strain 7
        String StrainDiv7 = null;
        String Strain7Name = null;
        String Strain7Vigor = null;
        int Strain7Height = 0;
        int Strain7PlotSize = 0;
        String Strain7Blooms = null;
        int Strain7Coverage = 0;
        String Strain7Genus1 = null;
        String Strain7Species1 = null;
        int Strain7Count1 = 0;
        String Strain7Genus2 = null;
        String Strain7Species2 = null;
        int Strain7Count2 = 0;
        String Strain7Genus3 = null;
        String Strain7Species3 = null;
        int Strain7Count3 = 0;
        String Strain7Genus4 = null;
        String Strain7Species4 = null;
        int Strain7Count4 = 0;
        String Strain7Genus5 = null;
        String Strain7Species5 = null;
        int Strain7Count5 = 0;
        String Strain7Genus6 = null;
        String Strain7Species6 = null;
        int Strain7Count6 = 0;


        //Strain 8
        String StrainDiv8 = null;
        String Strain8Name = null;
        String Strain8Vigor = null;
        int Strain8Height = 0;
        int Strain8PlotSize = 0;
        String Strain8Blooms = null;
        int Strain8Coverage = 0;
        String Strain8Genus1 = null;
        String Strain8Species1 = null;
        int Strain8Count1 = 0;
        String Strain8Genus2 = null;
        String Strain8Species2 = null;
        int Strain8Count2 = 0;
        String Strain8Genus3 = null;
        String Strain8Species3 = null;
        int Strain8Count3 = 0;
        String Strain8Genus4 = null;
        String Strain8Species4 = null;
        int Strain8Count4 = 0;
        String Strain8Genus5 = null;
        String Strain8Species5 = null;
        int Strain8Count5 = 0;
        String Strain8Genus6 = null;
        String Strain8Species6 = null;
        int Strain8Count6 = 0;


        //Strain 9
        String StrainDiv9 = null;
        String Strain9Name = null;
        String Strain9Vigor = null;
        int Strain9Height = 0;
        int Strain9PlotSize = 0;
        String Strain9Blooms = null;
        int Strain9Coverage = 0;
        String Strain9Genus1 = null;
        String Strain9Species1 = null;
        int Strain9Count1 = 0;
        String Strain9Genus2 = null;
        String Strain9Species2 = null;
        int Strain9Count2 = 0;
        String Strain9Genus3 = null;
        String Strain9Species3 = null;
        int Strain9Count3 = 0;
        String Strain9Genus4 = null;
        String Strain9Species4 = null;
        int Strain9Count4 = 0;
        String Strain9Genus5 = null;
        String Strain9Species5 = null;
        int Strain9Count5 = 0;
        String Strain9Genus6 = null;
        String Strain9Species6 = null;
        int Strain9Count6 = 0;


        //Strain 10
        String StrainDiv10 = null;
        String Strain10Name = null;
        String Strain10Vigor = null;
        int Strain10Height = 0;
        int Strain10PlotSize = 0;
        String Strain10Blooms = null;
        int Strain10Coverage = 0;
        String Strain10Genus1 = null;
        String Strain10Species1 = null;
        int Strain10Count1 = 0;
        String Strain10Genus2 = null;
        String Strain10Species2 = null;
        int Strain10Count2 = 0;
        String Strain10Genus3 = null;
        String Strain10Species3 = null;
        int Strain10Count3 = 0;
        String Strain10Genus4 = null;
        String Strain10Species4 = null;
        int Strain10Count4 = 0;
        String Strain10Genus5 = null;
        String Strain10Species5 = null;
        int Strain10Count5 = 0;
        String Strain10Genus6 = null;
        String Strain10Species6 = null;
        int Strain10Count6 = 0;
        String comments = null;
        String errorMessage = null;
        String buttonPress = null;


        buttonPress = req.getParameter("dataFormSubmit");


        if (buttonPress != null) {
            System.out.println("pressed");


            PlantStrain strain = new PlantStrain(1, 1, "hello", "maybe");
            strains.add(strain);
            System.out.println("passed this");

            //userCollected = req.getParameter("genUsers");
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


            comments = req.getParameter("comments");





            /*
            if (dateCreated == null || dateCreated.equals("")) {
                errorMessage = "Enter the day of collection in the format YYYY-MM-DD";
                System.out.printf("%s", errorMessage);
               dateCreated = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);

            } else {
                dateCreated = LocalDate.parse(req.getParameter("dateCollected"));
            } */


            if ("".equals(dateCreated) || dateCreated == null) {
                errorMessage = "Enter the day of collection in the format YYYY-MM-DD";
                System.out.printf("%s", errorMessage);
                dateCreated = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);

                /*


            if ("".equals(userCollected) || userCollected == null) {
                errorMessage = "Enter the name of the users colleced";
                System.out.printf("%s", errorMessage);
                userCollected = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);

            } else if ("".equals(gardenName) || gardenName == null) {
                errorMessage = "Enter the name of the garden";
                System.out.printf("%s", errorMessage);
                gardenName = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);

            } else if ("".equals(county) || county == null) {
                errorMessage = "Enter the date";
                System.out.printf("%s", errorMessage);
                county = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);

            } else if ("".equals(startTime) || startTime == null) {
                errorMessage = "Enter a start time";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);

            } else if ("".equals(endTime) || endTime == null) {
                errorMessage = "Enter a end time";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
*/

            } else if ("".equals(temperature) || temperature == 0) {
                errorMessage = "Enter a temperature";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(wind) || wind == null) {
                errorMessage = "Enter a wind condition";
                System.out.printf("%s", errorMessage);
                wind = null;
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(cloudCover) || cloudCover == null) {
                errorMessage = "Enter a cloud cover condition";
                System.out.printf("%s", errorMessage);
                cloudCover = null;
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(plantGenus) || plantGenus == null) {
                errorMessage = "Enter the plant genus";
                System.out.printf("%s", errorMessage);
                plantGenus = null;
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Vigor) || Strain1Vigor == null) {
                errorMessage = "Enter the vigor of the plot";
                System.out.printf("%s", errorMessage);
                Strain1Vigor = null;
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Height) || Strain1Height == 0) {
                errorMessage = "Enter the avgerage height of the plot";
                System.out.printf("%s", errorMessage);
                Strain1Height = 0;
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1PlotSize) || Strain1PlotSize == 0) {
                errorMessage = "Enter the average height of the plot";
                System.out.printf("%s", errorMessage);
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Blooms) || Strain1Blooms == null) {
                errorMessage = "Enter the blooms of the strain";
                System.out.printf("%s", errorMessage);
                Strain1Blooms = null;
                req.setAttribute("errorMessage", errorMessage);


            } else if ("".equals(Strain1Coverage) || Strain1Coverage == 0) {
                errorMessage = "Enter coverage of the plot";
                System.out.printf("%s", errorMessage);
                Strain1Coverage = 0;
                req.setAttribute("errorMessage", errorMessage);

           /* } else if ("".equals(Strain1Name) || Strain1Name == null) {
                errorMessage = "Enter the the name of the strain";
                System.out.printf("%s", errorMessage);
                Strain1Name = null;
                req.setAttribute("errorMessage", errorMessage);
                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp); */

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


//            } else if ("".equals(Strain1Count2) || Strain1Count2 == 0) {
//                errorMessage = "Enter count for 2nd pollinator";
//                System.out.printf("%s", errorMessage);
//                Strain1Count2 = 0;
//                req.setAttribute("errorMessage", errorMessage);
//                req.getRequestDispatcher("/_view/dataForm.jsp").forward(req, resp);
//
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

                Plot plot = new Plot(1, 1, 1, 0.0, 0.0, null, null, null);
                plotData.add(plot);

                /*
                plotData.get(0).setPlant_vigor_status(Strain1Vigor);
                plotData.get(0).setPlot_height(Strain1Height);
                plotData.get(0).setPlot_area_dbl(Strain1PlotSize);
                plotData.get(0).setBlooms_open_status(Strain1Blooms);
                plotData.get(0).setPercent_coverage(Strain1Coverage); */
                plotData.get(0).setPlant_vigor_status("11");
                plotData.get(0).setPlot_height(4.0);
                plotData.get(0).setPlot_area_dbl(11);
                plotData.get(0).setBlooms_open_status("11");
                plotData.get(0).setPercent_coverage(11);

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
                        wind, plotData, new Plant(1, "yes"), strains, polls, pvcs);
                DataFormController controller = null;
                try {
                    controller = new DataFormController();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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

        /*
        req.setAttribute("gardenname", gardenName);
        req.setAttribute("date", date);
        req.setAttribute("starttime", startTime);
        req.setAttribute("endtime", endTime);



        req.setAttribute("planttype", plantType);
        req.setAttribute("pollinatortype", pollinatorType);
        req.setAttribute("pollinatorcount", pollinatorCount);
        req.setAttribute("avgheightofplot", avgHeightofPlot);
        req.setAttribute("sizeplot", sizePlot);
        req.setAttribute("bloomsopen", bloomsOpen);
        req.setAttribute("flowercoverage", flowerCoverage);
        req.setAttribute("plantvigor", plantVigor);
        req.setAttribute("comments", comments); */


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
