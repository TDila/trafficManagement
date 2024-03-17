package com.vulcan.trafficmanagement.model;

import com.google.gson.JsonObject;
import com.vulcan.trafficmanagement.device.IoTDevice;
import com.vulcan.trafficmanagement.remote.AnalyticalServer;
import com.vulcan.trafficmanagement.remote.DataStorage;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TrafficAnalysis", value = "/trafficAnalysis")
public class TrafficAnalysis extends HttpServlet {
    @EJB
    AnalyticalServer analyticalServer;
    @EJB
    DataStorage dataStorage;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<IoTDevice> deviceList = dataStorage.retriveData();

        double averageSpeed = analyticalServer.calculateAverageSpeed(deviceList);
        String trafficFlow = analyticalServer.trafficFlowAnalysis(averageSpeed);
        double urbanMobilityEfficiency = analyticalServer.calculateUrbanMobilityEfficiency(averageSpeed);

        JsonObject json = new JsonObject();
        json.addProperty("averageSpeed", averageSpeed);
        json.addProperty("trafficFlow", trafficFlow);
        json.addProperty("urbanMobilityEfficiency", urbanMobilityEfficiency);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json.toString());
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double vehicleSpeed = 0.00;
        String trafficLightStatus = "NONE";
        double longitude = 0.00;
        double latitude = 0.00;
        String date_time = "NONE";

        List<IoTDevice> deviceList = dataStorage.retriveData();
        if(deviceList.size() != 0){
            IoTDevice ioTDevice = deviceList.get(deviceList.size() - 1);
            vehicleSpeed = ioTDevice.getVehicleSpeed();
            trafficLightStatus = ioTDevice.getTrafficLightStatus();
            longitude = ioTDevice.getLongitude();
            latitude = ioTDevice.getLatitude();
            date_time = ioTDevice.getDate_time();
        }

        JsonObject json = new JsonObject();
        json.addProperty("vehicleSpeed", vehicleSpeed);
        json.addProperty("trafficLightStatus", trafficLightStatus);
        json.addProperty("longitude", longitude);
        json.addProperty("latitude", latitude);
        json.addProperty("date_time", date_time);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json.toString());
    }
}
