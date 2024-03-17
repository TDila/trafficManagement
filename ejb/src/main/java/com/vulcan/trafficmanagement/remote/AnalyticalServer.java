package com.vulcan.trafficmanagement.remote;

public interface AnalyticalServer {
    public double calculateAverageSpeed(int vehicleCount, double totalSpeed);
    public String trafficFlowAnalysis(double averageSpeed);
    public double calculateUrbanMobilityEfficiency(double averageSpeed);
    public double calculateVehicleDensity(int vehiclesPerUpdate, int updateFrequencySeconds);
    public String decideLane(double latitude, double longitude);
}
