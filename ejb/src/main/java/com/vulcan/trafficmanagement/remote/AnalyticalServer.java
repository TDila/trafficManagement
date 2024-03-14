package com.vulcan.trafficmanagement.remote;

import com.vulcan.trafficmanagement.device.IoTDevice;

import java.util.List;

public interface AnalyticalServer {
    public double calculateAverageSpeed(List<IoTDevice> devices);
    public void trafficFlowAnalysis();
    public double calculateUrbanMobilityEfficiency();
}
