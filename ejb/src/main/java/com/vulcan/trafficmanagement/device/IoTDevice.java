package com.vulcan.trafficmanagement.device;

public class IoTDevice{
    private double vehicleSpeed;
    private String trafficLightStatus;
    private double gpsCoordinatesX;
    private double gpsCoordinatesY;
    private String date_time;

    public double getVehicleSpeed() {
        return vehicleSpeed;
    }

    public String getTrafficLightStatus() {
        return trafficLightStatus;
    }

    public double getGpsCoordinatesX() {
        return gpsCoordinatesX;
    }

    public double getGpsCoordinatesY() {
        return gpsCoordinatesY;
    }

    public String getDate_time() {
        return date_time;
    }

    @Override
    public String toString() {
        return "IoTDevice{" +
                "vehicleSpeed=" + vehicleSpeed +
                ", trafficLightStatus='" + trafficLightStatus + '\'' +
                ", gpsCoordinatesX=" + gpsCoordinatesX +
                ", gpsCoordinatesY=" + gpsCoordinatesY +
                ", date_time='" + date_time + '\'' +
                '}';
    }
}
