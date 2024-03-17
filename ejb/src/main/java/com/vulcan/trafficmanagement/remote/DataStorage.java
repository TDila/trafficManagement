package com.vulcan.trafficmanagement.remote;

import com.vulcan.trafficmanagement.model.IoTDevice;

import java.util.List;

public interface DataStorage {
    public void storeData(IoTDevice device);
    public List<IoTDevice> retriveData();
}
