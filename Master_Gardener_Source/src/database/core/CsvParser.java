package database.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CsvParser {



    public static List<List<String>>ParseCSV (String resourceName) throws IOException {

        //String fileName= "C:\\Users\\Chase\\Documents\\GitHub\\Master-Gardener-Pollinator-Monitoring\\Master_Gardener_Source\\src\\database\\persist\\res\\"+resourceName ;

        //String fileName= "C:\\Users\\Chase Wells\\Documents\\GitHub\\Master-Gardener-Pollinator-Monitoring\\Master_Gardener_Source\\src\\database\\persist\\res\\adamsCounty.csv";
        //String fileName = this.getClass().getClassLoader().getResource("database/persist/res/" + resourceName);
        //InputStream in = this.getClass().getClassLoader().getResourceAsStream("database/persist/res/" + resourceName);
//        if (fileName == null) {
//            throw new IOException("Couldn't open " + resourceName);
//        }
        //this.reader = new BufferedReader(new InputStreamReader(in));



        //   File file= new File(fileName);

        // this gives you a 2-dimensional array of strings
        List<List<String>> wholeSheet = new ArrayList<>();
        //Scanner inputStream;
        //inputStream = new Scanner(file);



        try{
            InputStream is = CsvParser.class.getClassLoader().getResourceAsStream("database/persist/res/" + resourceName);
            if (is == null) {
                throw new FileNotFoundException("Couldn't find resource: " + resourceName);
            }
            Scanner inputStream = new Scanner(is);

            while(inputStream.hasNextLine()){
                String line= inputStream.nextLine();
                String[] values = line.split(",", -1);
                // this adds the currently parsed line to the 2-dimensional string array
                wholeSheet.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  wholeSheet;
    }


    public static void main(String[] args) {

//        String resourceName = "adamsCounty.csv";
//        // home pc path
//        String fileName= "C:\\Users\\Chase\\Documents\\GitHub\\Master-Gardener-Pollinator-Monitoring\\Master_Gardener_Source\\src\\database\\persist\\res\\"+resourceName;
//        // school path
//        //String fileName= "C:\\Users\\Chase Wells\\Documents\\GitHub\\Master-Gardener-Pollinator-Monitoring\\Master_Gardener_Source\\src\\database\\persist\\res\\adamsCounty.csv";
//        File file= new File(fileName);
//
//        // this gives you a 2-dimensional array of strings
//        List<List<String>> wholeSheet = new ArrayList<>();
//        Scanner inputStream;
//
//        try{
//
//            inputStream = new Scanner(file);
//
//            while(inputStream.hasNextLine()){
//                String line= inputStream.nextLine();
//                String[] values = line.split(",", -1);
//                // this adds the currently parsed line to the 2-dimensional string array
//                wholeSheet.add(Arrays.asList(values));
//            }
//
//            inputStream.close();
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
////        // the following code lets you iterate through the 2-dimensional array
////        int lineNo = 1;
////        for(List<String> line: wholeSheet) {
////            int columnNo = 1;
////            for (String value: line) {
////                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
////                columnNo++;
////            }
////            lineNo++;
////        }
////        lineNo = 1;
//
//
//
//        // make the object and fill it with data
//        PollinatorData TestPdata = new PollinatorData();
//        int lineNo = 1;
//        // go through sheet and fill object
//        for(List<String> line: wholeSheet) {
//            int columnNo = 1;
//            for (String value: line) {
//                //if (value == "TIME START" || value == "TIME STOP" ){
//                if (lineNo == 3){
//                    //TestPdata.setWeekID(weekidCount);
//                    TestPdata.setTimeStart(line.get(8));
//
////                    int weekidCount=1;
////                    while(columnNo <28){
////                        TestPdata.setWeekID(weekidCount);
////                        TestPdata.setTimeStart(line.get(8));
////                        //columnNo++;
////                    }
//                }
//                if (lineNo == 4){
//                    TestPdata.setTimeStop(line.get(8));
//                }
//                columnNo++;
//            }
//            lineNo++;
//        }
//        System.out.println("Time Start : " + TestPdata.getTimeStart());
//        System.out.println("Time Start : " + TestPdata.getTimeStop());
//
//
////
////        // testing the object I make
////
////        int testlineNo = 1;
////        for(List<String> testline: wholeSheet) {
////            int testcolumnNo = 1;
////            for (String value: testline) {
////                System.out.println("Line " + testlineNo + " Column " + testcolumnNo + ": " + value);
////                testcolumnNo++;
////            }
////            lineNo++;
////        }
    }

}