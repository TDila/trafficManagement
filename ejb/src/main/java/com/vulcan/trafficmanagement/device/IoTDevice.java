package com.vulcan.trafficmanagement.device;

import java.io.Serializable;

public class IoTDevice {
    private double vehicleSpeed;
    private String trafficLightStatus;
    private double longitude;
    private double latitude;
    private String date_time;

    public double getVehicleSpeed() {
        return vehicleSpeed;
    }

    public String getTrafficLightStatus() {
        return trafficLightStatus;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getDate_time() {
        return date_time;
    }

    @Override
    public String toString() {
        return "IoTDevice{" +
                "vehicleSpeed=" + vehicleSpeed +
                ", trafficLightStatus='" + trafficLightStatus + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", date_time='" + date_time + '\'' +
                '}';
    }
}
