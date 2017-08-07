package com.company;

import java.net.SocketOption;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        VehicleInfo vehicleInfo = new VehicleInfo();

        System.out.println("Enter the vehicle's VIN number:");
        vehicleInfo.setVIN(scanner.nextLine());


        System.out.println("Enter the vehicle's odometer:");
        vehicleInfo.setOdometer(scanner.nextDouble());

        System.out.println("Enter the gas consumption:");
        vehicleInfo.setConsumption(scanner.nextDouble());


        System.out.println("Enter the mileage logged at the last oil change:");
        vehicleInfo.setOilChangeOdometer(scanner.nextDouble());


        System.out.println("Enter the engine size:");
        vehicleInfo.setEngineSize(scanner.nextDouble());

        TelematicsService myService = new TelematicsService();

        myService.report(vehicleInfo);
    }
}

