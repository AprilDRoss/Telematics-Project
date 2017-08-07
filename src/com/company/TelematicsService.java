package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.text.html.HTML;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.company.ReadHTML.getFileContents;

public class TelematicsService {

    static void report(VehicleInfo vehicleInfo) {
        //writes user input to JSON
        try {
            String fileName = vehicleInfo.VIN + ".json";
            System.out.println(fileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), vehicleInfo);
            String json = mapper.writeValueAsString(vehicleInfo);
        } catch (Exception e) {
            System.out.println(e);
        }

        //Writes JSON back to Java object
        VehicleInfo info;
        ArrayList<VehicleInfo> carInfo = new ArrayList<>();
        File file = new File(".");
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    info = mapper.readValue(f, VehicleInfo.class);
                    carInfo.add(info);
                    System.out.println(carInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Find averages
        int noOfVehicles = carInfo.size();
        double averageOdometer = 0;
        double averageConsumption = 0;
        double averageLastOilChange = 0;
        double averageEngineSize = 0;
        for (VehicleInfo vi : carInfo) {
            averageOdometer += vi.getOdometer();
            averageConsumption += vi.getConsumption();
            averageLastOilChange += vi.getOilChangeOdometer();
            averageEngineSize += vi.getEngineSize();

        }
        averageOdometer = averageOdometer / noOfVehicles;
        averageConsumption = averageConsumption / noOfVehicles;
        averageLastOilChange = averageLastOilChange / noOfVehicles;
        averageEngineSize = averageEngineSize / noOfVehicles;

        //need to adjust decimal places on average figures

        System.out.println(averageOdometer);
        System.out.println(averageConsumption);
        System.out.println(averageLastOilChange);
        System.out.println(averageEngineSize);

        //read dashboard html file into an arraylist
//        try {
//            ArrayList<String> CarHTML = new ArrayList<String>();
//            File file1 = new File("dashboard.html");
//            FileReader fr = new FileReader(file1);
//            BufferedReader br = new BufferedReader(fr);
//            String data;
//            while ((data = br.readLine()) != null) {
//                CarHTML.add(data);
//            }
//
//            CarHTML.add("<h1>Hi There</h1>");
//            System.out.println(CarHTML);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

       Map<Integer, String> CarHTML = new HashMap<Integer, String>();
       CarHTML.put(0, "<html>");
       CarHTML.put(1, "<title>Vehicle Telematics Dashboard</title>");
       CarHTML.put(2, "<body>");
       CarHTML.put(3, "<h1 align=\"center\">Averages for # vehicles</h1>");
       CarHTML.put(4, "<table align=\"center\">");
       CarHTML.put(5, "<tr>");
       CarHTML.put(6, "<th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>");
       CarHTML.put(7, "</tr>");
       CarHTML.put(8, "<tr>\n" +
               "            <td align=\"center\">#</td><td align=\"center\">#</td><td align=\"center\">#</td align=\"center\"><td align=\"center\">#</td>\n" +
               "        </tr>");
       CarHTML.put(9, "</table>\n" +
               "    <h1 align=\"center\">History</h1>\n" +
               "    <table align=\"center\" border=\"1\">\n" +
               "        <tr>\n" +
               "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td align=\"center\">#</td><td align=\"center\">#</td><td align=\"center\">#</td><td align=\"center\">#</td align=\"center\"><td align=\"center\">#</td>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td align=\"center\">45435</td><td align=\"center\">123</td><td align=\"center\">234</td><td align=\"center\">345</td align=\"center\"><td align=\"center\">4.5</td>\n" +
               "        </tr>\n" +
               "    </table>");
       CarHTML.put(10, "</body>\n" +
               "</html>");



    }
}

