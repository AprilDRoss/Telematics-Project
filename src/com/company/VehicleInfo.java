package com.company;

/**
 * Created by aprilross on 7/20/17.
 */
public class VehicleInfo {
    String VIN;
    double odometer;
    double consumption;
    double oilChangeOdometer;
    double engineSize;

    public VehicleInfo() {
        this.VIN = VIN;
        this.odometer = odometer;
        this.consumption = consumption;
        this.oilChangeOdometer = oilChangeOdometer;
        this.engineSize = engineSize;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public double getOdometer() {

        return odometer;
    }

    public void setOdometer(double odometer) {

        this.odometer = odometer;
    }

    public double getConsumption() {

        return consumption;
    }

    public void setConsumption(double consumption) {

        this.consumption = consumption;
    }

    public double getOilChangeOdometer() {

        return oilChangeOdometer;
    }

    public void setOilChangeOdometer(double oilChangeOdometer) {

        this.oilChangeOdometer = oilChangeOdometer;
    }

    public double getEngineSize() {

        return engineSize;
    }

    public void setEngineSize(double engineSize) {

        this.engineSize = engineSize;
    }

}

