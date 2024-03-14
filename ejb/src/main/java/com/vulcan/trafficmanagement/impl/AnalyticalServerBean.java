package com.vulcan.trafficmanagement.impl;

import com.vulcan.trafficmanagement.device.IoTDevice;
import com.vulcan.trafficmanagement.remote.AnalyticalServer;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class AnalyticalServerBean implements AnalyticalServer {
    @Override
    public double calculateAverageSpeed(List<IoTDevice> devices) {
        double total = 0;
        double average = 0 ;
        for (IoTDevice ioTDevice : devices){
            total += ioTDevice.getVehicleSpeed();
        }
        if(devices.size() != 0){
            average = (double)Math.round((total / devices.size()) * 100.0D) / 100.0D;
        }
        return average;
    }

    @Override
    public void trafficFlowAnalysis() {

    }

    @Override
    public double calculateUrbanMobilityEfficiency() {
        return 0;
    }
}
