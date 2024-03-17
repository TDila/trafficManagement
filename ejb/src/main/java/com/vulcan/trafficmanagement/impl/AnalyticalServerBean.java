package com.vulcan.trafficmanagement.impl;
import com.vulcan.trafficmanagement.remote.AnalyticalServer;
import jakarta.ejb.Stateless;

import java.util.Random;

@Stateless
public class AnalyticalServerBean implements AnalyticalServer {
    private static final double TRAFFIC_FLOW = 720; //Since the simulator sends IoTDevice objects 1 per 5 seconds, traffic flow is vehicles per hour
    private static final double ROAD_CAPACITY = 800;// maximum number of vehicles the road can handle per hour
    @Override
    public double calculateAverageSpeed(int vehicleCount, double totalSpeed) {
        double average = 0;
        if (vehicleCount != 0) {
            average = (double) Math.round((totalSpeed / vehicleCount) * 100.0D) / 100.0D;
        }
        return average;
    }

    @Override
    public String trafficFlowAnalysis(double averageSpeed) {
        Random random1 = new Random();
        int vehiclesPerUpdate = random1.nextInt(5) + 1;
        int updateFrequencySeconds = random1.nextInt(10) + 1;

        double vehicleDensity = calculateVehicleDensity(vehiclesPerUpdate, updateFrequencySeconds);

        if (averageSpeed < 10 && vehicleDensity > 0.004) {
            return "Very Heavy Traffic";
        } else if (averageSpeed >= 10 && averageSpeed < 20 && vehicleDensity > 0.0025) {
            return "Heavy Traffic";
        } else if (averageSpeed >= 20 && averageSpeed < 40 && vehicleDensity > 0.001) {
            return "Moderate Traffic";
        } else {
            return "Light Traffic";
        }
    }

    @Override
    public double calculateVehicleDensity(int vehiclesPerUpdate, int updateFrequencySeconds) {
        double vehiclesPerSecond = (double) vehiclesPerUpdate / updateFrequencySeconds;
        double roadSegmentSize = 1000;
        double vehicleDensity = vehiclesPerSecond / roadSegmentSize;

        return vehicleDensity;
    }

    @Override
    public double calculateUrbanMobilityEfficiency(double averageSpeed) {
        double roadUtilization = TRAFFIC_FLOW / ROAD_CAPACITY;

        double efficiency = averageSpeed * (1 - roadUtilization);

        efficiency = Math.round(efficiency * 1e6) / 1e6;

        return efficiency;
    }
    @Override
    public String decideLane(double latitude, double longitude) {
        double northLaneMinLatitude = 40.71225;
        double northLaneMaxLatitude = 40.71235;
        double northLaneMinLongitude = -74.00605;
        double northLaneMaxLongitude =-74.00595;

        double southLaneMinLatitude = 40.71205;
        double southLaneMaxLatitude = 40.71215;
        double southLaneMinLongitude = -74.00635;
        double southLaneMaxLongitude = -74.00625;

        double eastLaneMinLatitude = 40.71245;
        double eastLaneMaxLatitude = 40.71255;
        double eastLaneMinLongitude =-74.00625;
        double eastLaneMaxLongitude = -74.00615;

        double westLaneMinLatitude = 40.71215;
        double westLaneMaxLatitude = 40.71225;
        double westLaneMinLongitude = -74.00655;
        double westLaneMaxLongitude = -74.00645;

        if (latitude >= northLaneMinLatitude && latitude <= northLaneMaxLatitude &&
                longitude >= northLaneMinLongitude && longitude <= northLaneMaxLongitude) {
            return "NORTH";
        } else if (latitude >= southLaneMinLatitude && latitude <= southLaneMaxLatitude &&
                longitude >= southLaneMinLongitude && longitude <= southLaneMaxLongitude) {
            return "SOUTH";
        }else if (latitude >= eastLaneMinLatitude && latitude <= eastLaneMaxLatitude &&
                longitude >= eastLaneMinLongitude && longitude <= eastLaneMaxLongitude) {
            return "EAST";
        }else if (latitude >= westLaneMinLatitude && latitude <= westLaneMaxLatitude &&
                longitude >= westLaneMinLongitude && longitude <= westLaneMaxLongitude) {
            return "WEST";
        }else{
            return "UNKNOWN";
        }
    }
}
