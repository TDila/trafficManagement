package com.vulcan.trafficmanagement.remote;

import com.vulcan.trafficmanagement.device.IoTDevice;

import java.util.List;
import java.util.Queue;

public interface DataStorage {
    public void storeData(IoTDevice device);
    public List<IoTDevice> retriveData();
}
