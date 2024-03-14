package com.vulcan.trafficmanagement.impl;

import com.vulcan.trafficmanagement.device.IoTDevice;
import com.vulcan.trafficmanagement.remote.DataStorage;
import jakarta.ejb.Singleton;

import java.util.*;

@Singleton
public class DataStorageBean implements DataStorage {
    private List<IoTDevice> receivedData = new ArrayList<>();

    @Override
    public void storeData(IoTDevice device) {
        receivedData.add(device);
    }

    @Override
    public List<IoTDevice> retriveData() {
        return receivedData;
    }
}
