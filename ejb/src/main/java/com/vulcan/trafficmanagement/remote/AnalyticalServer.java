package com.vulcan.trafficmanagement.remote;

import com.vulcan.trafficmanagement.device.IoTDevice;

import java.util.List;

public interface AnalyticalServer {
    public double calculateAverageSpeed(List<IoTDevice> devices);
    public String trafficFlowAnalysis(double averageSpeed);
    public double calculateUrbanMobilityEfficiency(double averageSpeed);
    public double calculateVehicleDensity(int vehiclesPerUpdate, int updateFrequencySeconds);
}
