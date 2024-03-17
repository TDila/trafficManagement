package com.vulcan.trafficmanagement.impl;

import com.vulcan.trafficmanagement.device.IoTDevice;
import com.vulcan.trafficmanagement.remote.AnalyticalServer;
import jakarta.ejb.Stateless;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Stateless
public class AnalyticalServerBean implements AnalyticalServer {
    private static final double TRAFFIC_FLOW = 720; //Since the simulator sends IoTDevice objects 1 per 5 seconds, traffic flow is vehicles per hour
    private static final double ROAD_CAPACITY = 800;// maximum number of vehicles the road can handle per hour
    @Override
    public double calculateAverageSpeed(List<IoTDevice> devices) {
        double total = 0;
        double average = 0;
        for (IoTDevice ioTDevice : devices) {
            total += ioTDevice.getVehicleSpeed();
        }
        if (devices.size() != 0) {
            average = (double) Math.round((total / devices.size()) * 100.0D) / 100.0D;
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
}
