package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.text.html.HTML;
import java.io.*;
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
                    System.out.println(info);
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
        String avOdometer = String.format("%.1f", averageOdometer);
        String avConsumption = String.format("%.1f", averageConsumption);
        String avLastOilChange = String.format("%.1f", averageLastOilChange);
        String avEngineSize = String.format("%.1f", averageEngineSize);

        String s1 = "<h1 align=\"center\">Averages for # vehicles</h1>";
        String replace_s1 = s1.replace("#", ""+carInfo.size()+"");
        System.out.println(replace_s1);

       Map<Integer, String> CarHTML = new HashMap<Integer,String>();
       CarHTML.put(0, "<html>");
       CarHTML.put(1, "<title>Vehicle Telematics Dashboard</title>");
       CarHTML.put(2, "<body>");
       CarHTML.put(3, replace_s1);
       CarHTML.put(4, "<table align=\"center\">");
       CarHTML.put(5, "<tr>");
       CarHTML.put(6, "<th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>");
       CarHTML.put(7, "</tr>");
       CarHTML.put(8, "<tr>\n" +
               "            <td align=\"center\">"+ avOdometer + "</td><td align=\"center\">"+avConsumption + "</td><td align=\"center\">"+ avLastOilChange + "</td align=\"center\"><td align=\"center\">" +avEngineSize +"</td>\n" +
               "        </tr>");
       CarHTML.put(9, "</table>\n" +
               "    <h1 align=\"center\">History</h1>\n" +
               "    <table align=\"center\" border=\"1\">\n" +
               "        <tr>\n" +
               "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
               "        </tr>\n" +
               "        <tr>\n" +
               "            <td align=\"center\">" + vehicleInfo.getVIN() + "</td><td align=\"center\">" +vehicleInfo.getOdometer() +"</td><td align=\"center\">" + vehicleInfo.getConsumption() + "</td><td align=\"center\">" + vehicleInfo.getOilChangeOdometer() + "</td align=\"center\"><td align=\"center\">" + vehicleInfo.getEngineSize() +"</td>\n" +
               "        </tr>\n" +
               "    </table>");
       CarHTML.put(10, "</body>\n" +
               "</html>");

        try {
            File file2 = new File("newdashboard.html");
            file2.createNewFile();
            FileWriter writer = new FileWriter(file2);
            for (Integer name : CarHTML.keySet()){
                String value = CarHTML.get(name).toString();
                writer.write(value);
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

