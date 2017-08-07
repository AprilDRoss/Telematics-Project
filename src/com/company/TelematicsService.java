package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.text.html.HTML;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static com.company.ReadHTML.getFileContents;

public class TelematicsService {

    static void report(VehicleInfo vehicleInfo){
        //writes user input to JSON
        try{
            String fileName = vehicleInfo.VIN + ".json";
            System.out.println(fileName);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), vehicleInfo);
            String json = mapper.writeValueAsString(vehicleInfo);
        }catch(Exception e){
            System.out.println(e);
        }

        //Writes JSON back to Java object
        VehicleInfo info;
        ArrayList<VehicleInfo> carInfo = new ArrayList<>();
        File file = new File(".");
        for (File f : file.listFiles()){
            if (f.getName().endsWith(".json")){
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    info = mapper.readValue(f, VehicleInfo.class);
                    carInfo.add(info);
                    System.out.println(carInfo);
                }catch  (IOException e){
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
        for (VehicleInfo vi : carInfo){
            averageOdometer += vi.getOdometer();
            averageConsumption += vi.getConsumption();
            averageLastOilChange += vi.getOilChangeOdometer();
            averageEngineSize += vi.getEngineSize();

        }
        averageOdometer = averageOdometer/noOfVehicles;
        averageConsumption = averageConsumption/noOfVehicles;
        averageLastOilChange = averageLastOilChange/noOfVehicles;
        averageEngineSize = averageEngineSize/noOfVehicles;

        System.out.println(averageOdometer);
        System.out.println(averageConsumption);
        System.out.println(averageLastOilChange);
        System.out.println(averageEngineSize);





//        File file1 = new File("dashboard.html");
//        FileReader fr = new FileReader(file1);
//        BufferedReader br = new BufferedReader(fr);
//        String data;
//        while((data = br.readLine())!=null)
//        {
//            System.out.println(data);
//        }








    }
}
