package com.vulcan.trafficmanagement.impl;

import com.vulcan.trafficmanagement.model.IoTDevice;
import com.vulcan.trafficmanagement.remote.DataStorage;
import jakarta.ejb.Singleton;

import java.util.*;

@Singleton
public class DataStorageBean implements DataStorage {
    private List<IoTDevice> vehicleRecords = new ArrayList<>();

    @Override
    public void storeData(IoTDevice device) {
        vehicleRecords.add(device);
    }

    @Override
    public List<IoTDevice> retriveData() {
        return vehicleRecords;
    }
}
